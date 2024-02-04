//package br.com.agidoc.agiDoc.repository;
//
//import br.com.agidoc.agiDoc.config.OpenApiConfig;
//import br.com.agidoc.agiDoc.exception.DatabaseException;
//import br.com.agidoc.agiDoc.model.Associated;
//import br.com.agidoc.agiDoc.model.contact.Contact;
//import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//@Repository
//@NoArgsConstructor
//public class ContactRepository implements IRepository<Integer, Contact> {
//    public Integer getNextId(Connection connection) throws DatabaseException {
//        try {
//            String sql = "SELECT SEQ_CONTACTS.NEXTVAL MY_SEQUENCE FROM DUAL";
//            Statement stmt = connection.createStatement();
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
//    public Integer getAssociationNextId(Connection con) throws DatabaseException {
//        try {
//            String sql = "SELECT SEQ_CONTACTS_ASSOCIATIONS.NEXTVAL MY_SEQUENCE FROM DUAL";
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
//    public Contact create(Contact contact) throws DatabaseException {
//        Connection con = null;
//        try {
//            con = OpenApiConfig.getConnection();
//
//            Integer nextId = this.getNextId(con);
//            Integer associationNextId = this.getAssociationNextId(con);
//            contact.setId(nextId);
//
//            String sql1 = """
//                    INSERT INTO CONTACTS (ID_CONTACT, NAME, EMAIL, PHONE, PHONE_TYPE)
//                    VALUES (?, ?, ?, ?, ?)
//                    """;
//
//            PreparedStatement stmt1 = con.prepareStatement(sql1);
//
//            stmt1.setInt(1, contact.getId());
//            stmt1.setString(2, contact.getName());
//            if (contact.getEmail() != null) stmt1.setString(3, contact.getEmail());
//            else stmt1.setNull(3, Types.VARCHAR);
//            stmt1.setString(4, contact.getPhone());
//            stmt1.setInt(5, contact.getPhoneType().getType());
//
//            int res1 = stmt1.executeUpdate();
//
//            String sql2 = """
//                    INSERT INTO CONTACTS_ASSOCIATIONS (ID_CONTACT_ASSOCIATION, ID_CONTACT, ID_COMPETITOR, ID_INSTITUTION)
//                    VALUES (?, ?, ?, ?)
//                    """;
//
//            PreparedStatement stmt2 = con.prepareStatement(sql2);
//
//            stmt2.setInt(1, associationNextId);
//            stmt2.setInt(2, contact.getId());
//            if (contact.getAssociated().getType().equals(1)) {
//                stmt2.setInt(3, contact.getAssociatedId());
//                stmt2.setNull(4, Types.INTEGER);
//            }
//            if (contact.getAssociated().getType().equals(2)) {
//                stmt2.setNull(3, Types.INTEGER);
//                stmt2.setInt(4, contact.getAssociatedId());
//            }
//
//            int res2 = stmt2.executeUpdate();
//
//            return contact;
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
//    @Override
//    public Contact update(Integer id, Contact contact) throws DatabaseException {
//        Connection con = null;
//        try {
//            con = OpenApiConfig.getConnection();
//
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE CONTACTS SET");
//
//            if (contact.getName() != null) sql.append(" NAME = ?,");
//            if (contact.getEmail() != null) sql.append(" EMAIL = ?,");
//            if (contact.getPhone() != null) sql.append(" PHONE = ?,");
//            if (contact.getPhoneType() != null) sql.append(" PHONE_TYPE = ?,");
//
//            sql.deleteCharAt(sql.length() - 1);
//            sql.append(" WHERE ID_CONTACT = ?");
//
//            PreparedStatement stmt = con.prepareStatement(sql.toString());
//
//            int index = 1;
//
//            if (contact.getName() != null) stmt.setString(index++, contact.getName());
//            if (contact.getEmail() != null) stmt.setString(index++, contact.getEmail());
//            if (contact.getPhone() != null) stmt.setString(index++, contact.getPhone());
//            if (contact.getPhoneType() != null) stmt.setInt(index++, contact.getPhoneType().getType());
//
//            stmt.setInt(index++, id);
//
//            int res = stmt.executeUpdate();
//
//            return contact;
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
//    @Override
//    public void delete(Integer id) throws DatabaseException {
//        Connection con = null;
//        try {
//            con = OpenApiConfig.getConnection();
//
//            String sql1 = "DELETE FROM CONTACTS_ASSOCIATIONS WHERE ID_CONTACT = ?";
//
//            PreparedStatement stmt1 = con.prepareStatement(sql1);
//
//            stmt1.setInt(1, id);
//
//            int res1 = stmt1.executeUpdate();
//
//            String sql2 = "DELETE FROM CONTACTS WHERE ID_CONTACT = ?";
//
//            PreparedStatement stmt2 = con.prepareStatement(sql2);
//
//            stmt2.setInt(1, id);
//
//            int res2 = stmt2.executeUpdate();
//
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
//    @Override
//    public ArrayList<Contact> list() throws DatabaseException {
//        ArrayList<Contact> contacts = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = OpenApiConfig.getConnection();
//
//            String sql1 = "SELECT * FROM CONTACTS";
//
//            Statement stmt1 = con.createStatement();
//            ResultSet res1 = stmt1.executeQuery(sql1);
//
//            while (res1.next()) {
//                Contact contact = new Contact();
//                contact.setId(res1.getInt("ID_CONTACT"));
//                contact.setName(res1.getString("NAME"));
//                contact.setEmail(res1.getString("EMAIL"));
//                contact.setPhone(res1.getString("PHONE"));
//                contact.setPhoneType(ContactPhoneType.ofType(res1.getInt("PHONE_TYPE")));
//
//                String sql2 = "SELECT * FROM CONTACTS_ASSOCIATIONS WHERE ID_CONTACT = ?";
//
//                PreparedStatement stmt2 = con.prepareStatement(sql2);
//
//                stmt2.setInt(1, contact.getId());
//
//                ResultSet res2 = stmt2.executeQuery();
//
//                if (res2.next()) {
//                    if (res2.getInt("ID_COMPETITOR") != 0) {
//                        contact.setAssociated(Associated.ofType(1));
//                        contact.setAssociatedId(res2.getInt("ID_COMPETITOR"));
//                    }
//                    if (res2.getInt("ID_INSTITUTION") != 0) {
//                        contact.setAssociated(Associated.ofType(2));
//                        contact.setAssociatedId(res2.getInt("ID_INSTITUTION"));
//                    }
//                }
//
//                contacts.add(contact);
//            }
//
//            return contacts;
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
//    public Contact get(Integer idContact) throws Exception{
//        ArrayList<Contact> list = list();
//        for(Contact contact : list){
//            if(contact.getId().equals(idContact)){
//                return contact;
//            }
//        }
//        return null;
//    }
//
//    public Integer getByIdInstitution(Integer idInstitution) throws Exception{
//        Connection con = null;
//        try{
//            con = OpenApiConfig.getConnection();
//            String sqlContactAssociation = "SELECT ID_CONTACT FROM CONTACTS_ASSOCIATIONS WHERE ID_INSTITUTION = " + idInstitution;
//            Statement stmt = con.createStatement();
//            ResultSet res = stmt.executeQuery(sqlContactAssociation);
//            while(res.next()){
//                return res.getInt("ID_CONTACT");
//            }
//            return null;
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
//    }
//}
