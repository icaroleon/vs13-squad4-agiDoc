package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.document.Document;

import javax.print.Doc;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DocumentRepository implements IRepository<Integer, Document> {
    public Integer getNextId(Connection con) throws SQLException {
        String sql = "SELECT SEQ_DOCUMENTS.nextval mysequence from DUAL";

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    public Integer getAssociationNextId(Connection con) throws DatabaseException {
        try {
            String sql = "SELECT SEQ_DOCUMENTS_ASSOCIATIONS.NEXTVAL MY_SEQUENCE FROM DUAL";

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next())
                return res.getInt("MY_SEQUENCE");

            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        }
    }

    @Override
    public Document create(Document document) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            Integer nextId = this.getNextId(con);
            Integer associationNextId = this.getAssociationNextId(con);
            document.setId(nextId);

            String sqlInsert = """
                        INSERT INTO DOCUMENTS (ID_DOCUMENT, PROTOCOL, EXPIRATION_DATE, IS_SIGNED)
                        VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlInsert);

            stmt.setInt(1, document.getId());
            stmt.setString(2, document.getProtocol());
            stmt.setString(3, document.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            stmt.setInt(4, 0);

            int res1 = stmt.executeUpdate();

            System.out.println("createDocument.res=" + res1);

            String sql2 = """
                    INSERT INTO DOCUMENTS_ASSOCIATIONS (ID_DOCUMENT_ASSOCIATION, ID_DOCUMENT, ID_PROCESS)
                    VALUES (?, ?, ?)
                    """;

            PreparedStatement stmt2 = con.prepareStatement(sql2);

            stmt2.setInt(1, associationNextId);
            stmt2.setInt(2, document.getId());
            stmt2.setInt(3, document.getAssociatedId());

            int res2 = stmt2.executeUpdate();
            System.out.println("createAddressAssociation.res=" + res2);

            return document;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Document> list() throws DatabaseException {
        ArrayList<Document> docs = new ArrayList<>();
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sqlSelect = "SELECT * FROM DOCUMENTS";

            ResultSet res = stmt.executeQuery(sqlSelect);

            while (res.next()) {
                Document document = new Document();

                document.setId(res.getInt("ID_DOCUMENT"));
                document.setProtocol(res.getString("PROTOCOL"));
                document.setExpirationDate(res.getDate("EXPIRATION_DATE").toLocalDate());

                boolean isSigned = res.getInt("IS_SIGNED") == 1;
                document.setSigned(isSigned);

                String sql2 = "SELECT * FROM DOCUMENTS_ASSOCIATIONS WHERE ID_DOCUMENT = ?";

                PreparedStatement stmt2 = con.prepareStatement(sql2);

                stmt2.setInt(1, document.getId());

                ResultSet res2 = stmt2.executeQuery();

                if (res2.next()) {
                    document.setAssociated(Associated.PROCESS);
                    document.setAssociatedId(res2.getInt("ID_PROCESS"));
                }

                docs.add(document);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return docs;
    }

    @Override
    public Document update(Integer id, Document document) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlUpdate = "UPDATE DOCUMENTS SET PROTOCOL = ?, EXPIRATION_DATE = ? WHERE ID_DOCUMENT = ?";

            PreparedStatement stmt = con.prepareStatement(sqlUpdate);

            stmt.setString(1, document.getProtocol());
            stmt.setString(2, document.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();
            System.out.println("updateDocument.res=" + res);

            return document;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql1 = "DELETE FROM DOCUMENTS_ASSOCIATIONS WHERE ID_DOCUMENT = ?";

            PreparedStatement stmt1 = con.prepareStatement(sql1);

            stmt1.setInt(1, id);

            int res1 = stmt1.executeUpdate();
            System.out.println("deleteDocumentAssociation.res=" + res1);

            String sql2 = "DELETE FROM DOCUMENTS WHERE ID_DOCUMENT = ?";

            PreparedStatement stmt = con.prepareStatement(sql2);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("deleteDocument.res=" + res);
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Document get(Integer id) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlSelect = "SELECT * FROM DOCUMENTS WHERE ID_DOCUMENT = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Document document = new Document();

            if (res.next()) {
                document.setId(res.getInt("ID_DOCUMENT"));
                document.setProtocol(res.getString("PROTOCOL"));
                document.setExpirationDate(res.getDate("EXPIRATION_DATE").toLocalDate());

                boolean isSigned = res.getInt("IS_SIGNED") == 1;
                document.setSigned(isSigned);
            }

            return document;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sign(Integer id, Integer signatureId) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlUpdate = "UPDATE DOCUMENTS SET IS_SIGNED = ?, ID_SIGNATURE = ? WHERE ID_DOCUMENT = ?";

            PreparedStatement stmt = con.prepareStatement(sqlUpdate);

            stmt.setInt(1, 1);
            stmt.setInt(2, signatureId);
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
