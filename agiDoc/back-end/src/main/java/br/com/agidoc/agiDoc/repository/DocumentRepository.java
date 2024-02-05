package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.dto.document.DocumentListDTO;
import br.com.agidoc.agiDoc.model.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Query(value = """
                    SELECT * FROM DOCUMENTS d
                    JOIN DOCUMENTS_ASSOCIATIONS da ON d.ID_DOCUMENT = da.ID_DOCUMENT WHERE da.ID_PROCESS = :idProcess
            """, nativeQuery = true)
        //d.ID_DOCUMENT, d.PROTOCOL, d.EXPIRATION_DATE, d.IS_SIGNED, d."FILE", d.ID_SIGNATURE TODO mudar para
    List<Document> findAllDocumentsByProcessId(@Param("idProcess") Integer idProcess);


    @Query(value = """
                SELECT d.ID_DOCUMENT, d.PROTOCOL, d.EXPIRATION_DATE, d.IS_SIGNED, p.ID_PROCESS, p.PROCESS_NUMBER,
                    p.TITLE, p.DESCRIPTION, p.STATUS, p.ID_COMPANY
                FROM DOCUMENTS d
                JOIN DOCUMENTS_ASSOCIATIONS da ON d.ID_DOCUMENT = da.ID_DOCUMENT
                JOIN PROCESSES p ON da.id_process = p.id_process
                WHERE da.ID_DOCUMENT = :idDocument
            """, nativeQuery = true)
    Optional<Document> returnAllInfosByDocumentId(@Param("idDocument") Integer idDocument);


//    public Integer getNextId(Connection con) throws SQLException {
//        String sql = "SELECT SEQ_DOCUMENTS.nextval mysequence from DUAL";
//
//        Statement stmt = con.createStatement();
//        ResultSet res = stmt.executeQuery(sql);
//
//        if (res.next()) {
//            return res.getInt("mysequence");
//        }
//
//        return null;
//    }
//
//    public Integer getAssociationNextId(Connection con) throws DatabaseException {
//        try {
//            String sql = "SELECT SEQ_DOCUMENTS_ASSOCIATIONS.NEXTVAL MY_SEQUENCE FROM DUAL";
//
//            Statement stmt = con.createStatement();
//            ResultSet res = stmt.executeQuery(sql);
//
//            if (res.next()) return res.getInt("MY_SEQUENCE");
//
//            return null;
//        } catch (SQLException e) {
//            throw new DatabaseException(e.getCause());
//        }
//    }
//
//    @Override
//    public Document create(Document document) throws DatabaseException {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//
//            Integer nextId = this.getNextId(con);
//            Integer associationNextId = this.getAssociationNextId(con);
//            document.setId(nextId);
//
//            String sqlInsert = """
//                        INSERT INTO DOCUMENTS (ID_DOCUMENT, PROTOCOL, EXPIRATION_DATE, IS_SIGNED)
//                        VALUES (?, ?, ?, ?)
//                    """;
//
//            PreparedStatement stmt = con.prepareStatement(sqlInsert);
//
//            stmt.setInt(1, document.getId());
//            stmt.setString(2, document.getProtocol());
//            stmt.setObject(3, document.getExpirationDate());
//            stmt.setInt(4, 0);
//
//            int res1 = stmt.executeUpdate();
//
//            System.out.println("createDocument.res=" + res1);
//
//            String sql2 = """
//                    INSERT INTO DOCUMENTS_ASSOCIATIONS (ID_DOCUMENT_ASSOCIATION, ID_DOCUMENT, ID_PROCESS)
//                    VALUES (?, ?, ?)
//                    """;
//
//            PreparedStatement stmt2 = con.prepareStatement(sql2);
//
//            stmt2.setInt(1, associationNextId);
//            stmt2.setInt(2, document.getId());
//            stmt2.setInt(3, 1);
//
//            int res2 = stmt2.executeUpdate();
//            System.out.println("createAddressAssociation.res=" + res2);
//
//            return document;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            throw new DatabaseException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public ArrayList<Document> list() throws DatabaseException {
//        ArrayList<Document> docs = new ArrayList<>();
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//            Statement stmt = con.createStatement();
//
//            String sqlSelect = "SELECT * FROM DOCUMENTS";
//
//            ResultSet res = stmt.executeQuery(sqlSelect);
//
//            while (res.next()) {
//                Document document = new Document();
//
//                document.setId(res.getInt("ID_DOCUMENT"));
//                document.setProtocol(res.getString("PROTOCOL"));
//                document.setExpirationDate(res.getDate("EXPIRATION_DATE").toLocalDate());
//
//                boolean isSigned = res.getInt("IS_SIGNED") == 1;
//                document.setSigned(isSigned);
//
//                String sql2 = "SELECT * FROM DOCUMENTS_ASSOCIATIONS WHERE ID_DOCUMENT = ?";
//
//                PreparedStatement stmt2 = con.prepareStatement(sql2);
//
//                stmt2.setInt(1, document.getId());
//
//                ResultSet res2 = stmt2.executeQuery();
//
//                if (res2.next()) {
//                    document.setAssociated(Associated.PROCESS);
//                    document.setProcessId(res2.getInt("ID_PROCESS"));
//                }
//
//                docs.add(document);
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return docs;
//    }
//
//    @Override
//    public Document update(Integer idDocument, Document document) throws Exception {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//
//            String sqlUpdate = "UPDATE DOCUMENTS SET EXPIRATION_DATE = ? WHERE ID_DOCUMENT = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sqlUpdate);
//
//            stmt.setObject(1, document.getExpirationDate());
//            stmt.setInt(2, idDocument);
//
//            int rowsAffected = stmt.executeUpdate();
//            if (rowsAffected > 0) {
//                return document;
//            } else {
//                throw new RegraDeNegocioException("No document found with ID = " + idDocument);
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException(e.getCause());
//        }
//    }
//
//    @Override
//    public void delete(Integer idDocument) throws Exception {
//        Connection con = null;
//        try {
//            con = DBConnection.getConnection();
//
//            String sql1 = "DELETE FROM DOCUMENTS_ASSOCIATIONS WHERE ID_DOCUMENT = ?";
//
//            PreparedStatement stmt1 = con.prepareStatement(sql1);
//
//            stmt1.setInt(1, idDocument);
//
//            int res1 = stmt1.executeUpdate();
//
//            String sql2 = "DELETE FROM DOCUMENTS WHERE ID_DOCUMENT = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql2);
//            stmt.setInt(1, idDocument);
//
//            int rowsAffected  = stmt.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Document removido com SUCESSO");
//            } else {
//                throw new RegraDeNegocioException("No document found with ID = " + idDocument);
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Document get(Integer id) throws DatabaseException {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//
//            String sqlSelect = "SELECT * FROM DOCUMENTS WHERE ID_DOCUMENT = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sqlSelect);
//            stmt.setInt(1, id);
//
//            ResultSet res = stmt.executeQuery();
//
//            Document document = new Document();
//
//            if (res.next()) {
//                document.setId(res.getInt("ID_DOCUMENT"));
//                document.setProtocol(res.getString("PROTOCOL"));
//                document.setExpirationDate(res.getDate("EXPIRATION_DATE").toLocalDate());
//
//                boolean isSigned = res.getInt("IS_SIGNED") == 1;
//                document.setSigned(isSigned);
//            }
//
//            return document;
//        } catch (SQLException e) {
//            throw new DatabaseException(e.getCause());
//        } finally {
//            try {
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public boolean sign(Integer idDocument, Integer userId) throws Exception {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//
//            String sqlUpdate = "UPDATE DOCUMENTS SET IS_SIGNED = ?, ID_SIGNATURE = ? WHERE ID_DOCUMENT = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sqlUpdate);
//
//            stmt.setInt(1, 1);
//            stmt.setInt(2, userId);
//            stmt.setInt(3, idDocument);
//
//            int res = stmt.executeUpdate();
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new RegraDeNegocioException("Failed to sign the document:" + idDocument);
//        } finally {
//            try {
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Document findById(Integer idDocument) throws Exception {
//        Connection con = null;
//
//        try {
//            con = DBConnection.getConnection();
//
//            String sqlFindById = """
//                    SELECT *
//                    FROM DOCUMENTS D
//                    JOIN DOCUMENTS_ASSOCIATIONS DA ON (DA.ID_DOCUMENT = D.ID_DOCUMENT)
//                    JOIN PROCESSES P ON (P.ID_PROCESS = DA.ID_PROCESS)
//                    WHERE D.ID_DOCUMENT = ?
//                    """;
//
//            PreparedStatement stmt = con.prepareStatement(sqlFindById);
//
//            stmt.setInt(1, idDocument);
//
//            ResultSet resultSet = stmt.executeQuery();
//
//            if (resultSet.next()) {
//                return this.getDocumentFromResultSet(resultSet);
//            } else {
//                throw new RegraDeNegocioException("No document found with ID = " + idDocument);
//            }
//        } catch (DatabaseException e) {
//            throw new DatabaseException(e.getCause());
//        }
//    }
//
//    public Document getDocumentFromResultSet(ResultSet resultSet) throws SQLException {
//        Document document = new Document();
//
//        document.setId(resultSet.getInt("ID_DOCUMENT"));
//        document.setProcessId(resultSet.getInt("ID_PROCESS"));
//        document.setProtocol(resultSet.getString("PROTOCOL"));
//        document.setExpirationDate(resultSet.getDate("EXPIRATION_DATE").toLocalDate());
//        document.setSigned(resultSet.getBoolean("IS_SIGNED"));
//        document.setFile(resultSet.getBlob("FILE") != null ? resultSet.getBlob("FILE").toString() : "Sem arquivo anexado");
//        document.setAssociated(resultSet.wasNull() ? Associated.ofType(4) : Associated.ofType(resultSet.getInt("ID_DOCUMENT_ASSOCIATION")));
//
//        return document;
//    }
}