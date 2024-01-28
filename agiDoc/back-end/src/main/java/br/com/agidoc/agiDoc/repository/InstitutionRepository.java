package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import br.com.agidoc.agiDoc.model.institution.Institution;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@NoArgsConstructor
public class InstitutionRepository implements IRepository<Integer, Institution> {
    @Override
    public Integer getNextId(Connection con) throws SQLException {
        String sql = "SELECT SEQ_INSTITUTIONS.nextval mysequence from DUAL";

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Institution create(Institution institution) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            Integer nextId = this.getNextId(con);
            institution.setId(nextId);

            String sql = "INSERT INTO INSTITUTIONS\n" +
                    "(ID_INSTITUTION, CNPJ, COMPANY_NAME)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, institution.getId());
            stmt.setString(2, institution.getCnpj());
            stmt.setString(3, institution.getCompanyName());

            int res = stmt.executeUpdate();
            System.out.println("adicionarInstitutions.res= " + res);
            return institution;
        } catch (SQLException e) {
            throw new DatabaseException(e);
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
    public Institution update(Integer id, Institution institution) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE INSTITUTIONS SET ");
            sql.append(" CNPJ = ?, ");
            sql.append(" COMPANY_NAME = ? ");
            sql.append(" WHERE ID_INSTITUTION = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, institution.getCnpj());
            stmt.setString(2, institution.getCompanyName());
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();
            System.out.println("editarInstitutions.res=" + res);

            return institution;
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
    public void delete(Integer id) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql = "DELETE FROM INSTITUTIONS WHERE ID_INSTITUTION = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerInstitutionPorId.res=" + res);
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
    public ArrayList<Institution> list() throws DatabaseException {
        ArrayList<Institution> institutions = new ArrayList<>();
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            Statement stmtTwo = con.createStatement();

            String sql = "SELECT * FROM INSTITUTIONS";

            ResultSet resSimples = stmt.executeQuery(sql);

            while (resSimples.next()) {
                Institution institution = new Institution();

                institution.setId(resSimples.getInt("ID_INSTITUTION"));
                institution.setCnpj(resSimples.getString("CNPJ"));
                institution.setCompanyName(resSimples.getString("COMPANY_NAME"));

                institutions.add(institution);
            }
            sql = """
                    SELECT * FROM INSTITUTIONS I
                    JOIN CONTACTS_ASSOCIATIONS CA ON I.ID_INSTITUTION  = CA.ID_INSTITUTION 
                    JOIN CONTACTS CO ON CA.ID_CONTACT = CO.ID_CONTACT
                    JOIN ADDRESSES_ASSOCIATIONS AA ON I.ID_INSTITUTION  = AA.ID_INSTITUTION 
                    JOIN ADDRESSES A ON AA.ID_ADDRESS = A.ID_ADDRESS
                   """;
            ResultSet resComposto = stmtTwo.executeQuery(sql);
            while(resComposto.next()){
                Institution institutionReference = null;
                try{
                    institutionReference = institutions.get(resComposto.getInt("ID_INSTITUTION") - 1);
                    Address address = new Address();
                    Contact contact = new Contact();

                    address.setId(resComposto.getInt("ID_ADDRESS"));
                    address.setStreet(resComposto.getString("STREET"));
                    address.setDistrict(resComposto.getString("DISTRICT"));
                    address.setNumber(resComposto.getInt("NUMBER"));
                    address.setComplement(resComposto.getString("COMPLEMENT"));
                    address.setCity(resComposto.getString("CITY"));
                    address.setState(resComposto.getString("STATE"));
                    address.setZipCode(resComposto.getString("ZIP_CODE"));

                    institutionReference.setAddress(address);

                    contact.setId(resComposto.getInt("ID_CONTACT"));
                    contact.setName(resComposto.getString("NAME"));
                    contact.setEmail(resComposto.getString("EMAIL"));
                    contact.setPhone(resComposto.getString("PHONE"));
                    contact.setPhoneType(ContactPhoneType.ofType(resComposto.getInt("PHONE_TYPE")));

                    institutionReference.setContact(contact);
                }catch (IndexOutOfBoundsException erro){
                    erro.printStackTrace();
                }
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

        return institutions;
    }
}