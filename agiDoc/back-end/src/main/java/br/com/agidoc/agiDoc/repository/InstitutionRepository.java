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

            String sql = """
                    SELECT * FROM INSTITUTIONS I
                    JOIN CONTACTS_ASSOCIATIONS CA ON I.ID_INSTITUTION  = CA.ID_INSTITUTION
                    JOIN CONTACTS CO ON CA.ID_CONTACT = CO.ID_CONTACT
                    JOIN ADDRESSES_ASSOCIATIONS AA ON I.ID_INSTITUTION  = AA.ID_INSTITUTION
                    JOIN ADDRESSES A ON AA.ID_ADDRESS = A.ID_ADDRESS
                    """;

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Institution institution = new Institution();

                Address address = new Address();
                Contact contact = new Contact();

                institution.setId(res.getInt("ID_INSTITUTION"));
                institution.setCnpj(res.getString("CNPJ"));
                institution.setCompanyName(res.getString("COMPANY_NAME"));


                //TODO: implementar a classe address

                address.setId(res.getInt("ID_ADDRESS"));
                address.setStreet(res.getString("STREET"));
                address.setDistrict(res.getString("DISTRICT"));
                address.setNumber(res.getInt("NUMBER"));
                address.setComplement(res.getString("COMPLEMENT"));
                address.setCity(res.getString("CITY"));
                address.setState(res.getString("STATE"));
                address.setZipCode(res.getString("ZIP_CODE"));

                institution.setAddress(address);

                contact.setId(res.getInt("ID_CONTACT"));
                contact.setName(res.getString("NAME"));
                contact.setEmail(res.getString("EMAIL"));
                contact.setPhone(res.getString("PHONE"));
                contact.setPhoneType(ContactPhoneType.ofType(res.getInt("PHONE_TYPE")));

                institution.setContact(contact);

                institutions.add(institution);
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

    public Institution get(int id) throws SQLException {
        Connection con = null;
        try{
            con = DBConnection.getConnection();

            String sql = """
                    SELECT * FROM INSTITUTIONS I
                    JOIN CONTACTS_ASSOCIATIONS CA ON I.ID_INSTITUTION  = CA.ID_INSTITUTION
                    JOIN CONTACTS CO ON CA.ID_CONTACT = CO.ID_CONTACT
                    JOIN ADDRESSES_ASSOCIATIONS AA ON I.ID_INSTITUTION  = AA.ID_INSTITUTION
                    JOIN ADDRESSES A ON AA.ID_ADDRESS = A.ID_ADDRESS
                    WHERE ID_INSTITUTION = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();
            Institution institution = new Institution();

            Address address = new Address();

            Contact contact = new Contact();
            if (res.next()){


                institution.setId(res.getInt("ID_INSTITUTION"));
                institution.setCnpj(res.getString("CNPJ"));
                institution.setCompanyName(res.getString("COMPANY_NAME"));

                address.setId(res.getInt("ID_ADDRESS"));
                address.setStreet(res.getString("STREET"));
                address.setDistrict(res.getString("DISTRICT"));
                address.setNumber(res.getInt("NUMBER"));
                address.setComplement(res.getString("COMPLEMENT"));
                address.setCity(res.getString("CITY"));
                address.setState(res.getString("STATE"));
                address.setZipCode(res.getString("ZIP_CODE"));

                institution.setAddress(address);

                contact.setId(res.getInt("ID_CONTACT"));
                contact.setName(res.getString("NAME"));
                contact.setEmail(res.getString("EMAIL"));
                contact.setPhone(res.getString("PHONE"));
                contact.setPhoneType(ContactPhoneType.ofType(res.getInt("PHONE_TYPE")));

                institution.setContact(contact);

            }

            return institution;


        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
