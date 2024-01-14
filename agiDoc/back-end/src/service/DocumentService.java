package service;

import model.document.Document;

import java.sql.*;
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
    public boolean update(Integer id, Document object) throws DatabaseException {
        return true;
    }

    @Override
    public boolean delete(Integer id) throws DatabaseException {
        return true;
    }

    public Document get(Integer id) {
        return new Document();
    }

    @Override
    public ArrayList<Document> list() throws DatabaseException {
        ArrayList<Document> docs = new ArrayList<>();
        return docs;
    }

}
