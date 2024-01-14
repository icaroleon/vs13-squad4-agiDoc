package service;

import model.document.Document;

import java.sql.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.plaf.nimbus.State;

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

    @Override
    public Document create(Document document) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            Integer nextId = this.getNextId(con);
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

            int res = stmt.executeUpdate();

            System.out.println(res + "documento adicionado");
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

            String sql = "DELETE FROM DOCUMENTS WHERE ID_COMPETITOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
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

    public Document get(Integer id) {
        return new Document();
    }

}
