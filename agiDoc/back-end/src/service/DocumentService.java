package service;

import model.document.Document;

import java.sql.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.DBConnection;
import exception.DatabaseException;

public class DocumentService implements IService<Integer, Document> {
    public DocumentService() {
    }

    @Override
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
                        INSERT INTO DOCUMENTS (ID_DOCUMENT, PROTOCOL, EXPIRATION_DATE, IS_SIGNED, FILE, ID_SIGNATURE)
                        VALUES (?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlInsert);

            stmt.setInt(1, document.getId());
            stmt.setString(2, document.getProtocol());
            stmt.setString(3, document.getExpirationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            if (document.getSigned() == true) {
                stmt.setString(4, "1");
            } else {
                stmt.setString(4, "0");
            }
            stmt.setString(5, document.getFile());
            // TODO: Deve ser implementado ID_SIGNATURE OU NAO?
            stmt.setString(6, "IMPLEMENTAR?");

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

            String sqlSelect = """
                    SELECT * FROM VS_13_EQUIPE_4.DOCUMENTS C
                    JOIN VS_13_EQUIPE_4.DOCUMENTS_ASSOCIATIONS DA ON DA.ID_DOCUMENT = C.ID_DOCUMENT
                    """;

            ResultSet res = stmt.executeQuery(sqlSelect);

            while (res.next()) {
                Document document = new Document();

                document.setId(res.getInt("ID_DOCUMENT"));
                document.setProtocol(res.getString("PROTOCOL"));
                document.setExpirationDate(
                        res.getDate("EXPIRATION_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                boolean isSigned = false;

                if (res.getInt("IS_SIGNED") == 1)
                    isSigned = true;

                document.setSigned(isSigned);

                String sql2 = "SELECT * FROM DOCUMENTS_ASSOCIATIONS WHERE ID_DOCUMENT = ?";

                PreparedStatement stmt2 = con.prepareStatement(sql2);

                stmt2.setInt(1, document.getId());

                ResultSet res2 = stmt2.executeQuery();

                if (res2.next()) {
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
    public boolean update(Integer id, Document document) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlUpdate = """
                    UPDATE DOCUMENT SET
                        PROTOCOL = ?
                        EXPIRATION_DATE = ?
                        IS_SIGNED = ?
                        FILE = ?
                    WHERE ID_DOCUMENT = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlUpdate);

            stmt.setString(1, document.getProtocol());
            stmt.setString(2, document.getExpirationDate().toString());

            int isSigned = 0;
            if (document.getSigned()) {
                isSigned = 1;
            }

            stmt.setInt(3, isSigned);
            stmt.setString(4, document.getFile());
            stmt.setInt(5, id);

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

    @Override
    public boolean delete(Integer id) throws DatabaseException {
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

            return res > 0;
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

            String sqlSelect = """
                    SELECT * FROM VS_13_EQUIPE_4.DOCUMENTS C
                    JOIN VS_13_EQUIPE_4.DOCUMENTS_ASSOCIATIONS DA ON DA.ID_DOCUMENT = C.ID_DOCUMENT
                    WHERE ID_COMPETITOR = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery(sqlSelect);

            Document document = new Document();

            document.setId(res.getInt("ID_DOCUMENT"));
            document.setProtocol(res.getString("PROTOCOL"));
            document.setExpirationDate(
                    res.getDate("EXPIRATION_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            boolean isSigned = false;

            if (res.getInt("IS_SIGNED") == 1)
                isSigned = true;

            document.setSigned(isSigned);

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

}