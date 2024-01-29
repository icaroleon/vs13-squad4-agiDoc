package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.dto.institution.InstitutionDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import br.com.agidoc.agiDoc.model.institution.Institution;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class InstitutionRepository implements IRepository<Integer, Institution> {
    private final DBConnection dbConnection;

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

    public Integer getNextIdContactAssociation(Connection con) throws SQLException{
        String sql = "SELECT SEQ_CONTACTS_ASSOCIATIONS.nextval mysequence from DUAL";

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if(res.next()){
            return res.getInt("mysequence");
        }
        return null;
    }
    public Integer getNextIdAddress(Connection con) throws SQLException{
        String sql = "SELECT SEQ_ADDRESSES.nextval mysequence from DUAL";

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if(res.next()){
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Institution create(Institution institution) throws DatabaseException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            Integer nextId = this.getNextId(con);
            institution.setId(nextId);



            String sql = "INSERT INTO INSTITUTIONS\n" +
                    "(ID_INSTITUTION, COMPANY_NAME, CNPJ)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmtInstitution = con.prepareStatement(sql);

            stmtInstitution.setInt(1, institution.getId());
            stmtInstitution.setString(2, institution.getCompanyName());
            stmtInstitution.setString(3, institution.getCnpj());

            stmtInstitution.executeUpdate();

            String sqlAddress = "INSERT INTO ADDRESSES\n" +
                    "(ID_ADDRESS, STREET, DISTRICT, NUM, COMPLEMENT, CITY, STATE, ZIP_CODE, ID_INSTITUTION)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmtAddress = con.prepareStatement(sqlAddress);

            Address address = institution.getAddress();

            address.setId(getNextIdAddress(con));

            stmtAddress.setInt(1, address.getId());
            stmtAddress.setString(2, address.getStreet());
            stmtAddress.setString(3, address.getDistrict());
            stmtAddress.setInt(4, address.getNumber());
            stmtAddress.setString(5, address.getComplement());
            stmtAddress.setString(6, address.getCity());
            stmtAddress.setString(7, address.getState());
            stmtAddress.setString(8, address.getZipCode());
            stmtAddress.setInt(9, institution.getId());

            stmtAddress.executeQuery();

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
            con = this.dbConnection.getConnection();

            String sql = "UPDATE INSTITUTIONS SET CNPJ = ?, COMPANY_NAME = ? WHERE ID_INSTITUTION = ? ";


            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, institution.getCnpj());
            stmt.setString(2, institution.getCompanyName());
            stmt.setInt(3, id);

            stmt.executeUpdate();

            Address address = institution.getAddress();

            String sqlAddress = "SELECT ID_ADDRESS FROM ADDRESSES WHERE ID_INSTITUTION = " + id;

            Statement stmtAddress = con.createStatement();
            ResultSet resIdAddress = stmtAddress.executeQuery(sqlAddress);
            while(resIdAddress.next()){
                String sqlUpdate = "UPDATE ADDRESSES SET STREET = ?, DISTRICT = ?, NUM = ?, COMPLEMENT = ?, CITY = ?, STATE = ?, ZIP_CODE = ? WHERE ID_ADDRESS = " + resIdAddress.getInt("ID_ADDRESS");

                PreparedStatement stmtAddressUpdate = con.prepareStatement(sqlUpdate);
                stmtAddressUpdate.setString(1, address.getStreet());
                stmtAddressUpdate.setString(2, address.getDistrict());
                stmtAddressUpdate.setInt(3, address.getNumber());
                stmtAddressUpdate.setString(4, address.getComplement());
                stmtAddressUpdate.setString(5, address.getCity());
                stmtAddressUpdate.setString(6, address.getState());
                stmtAddressUpdate.setString(7, address.getZipCode());
                stmtAddressUpdate.executeQuery();
                institution.getAddress().setId(resIdAddress.getInt("ID_ADDRESS"));
            }


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

            con = this.dbConnection.getConnection();

            String sqlAddress = "DELETE FROM ADDRESSES WHERE ID_INSTITUTION = " + id;
            Statement stmt = con.createStatement();
            int resTwo = stmt.executeUpdate(sqlAddress);

            String sqlInstitution = "DELETE FROM INSTITUTIONS WHERE ID_INSTITUTION = " + id;

            Statement stmtTwo = con.createStatement();

            int res = stmtTwo.executeUpdate(sqlInstitution);




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
            con = this.dbConnection.getConnection();
            Statement stmt = con.createStatement();
            Statement stmtTwo = con.createStatement();
            Statement stmtThree = con.createStatement();

            String sql = "SELECT * FROM INSTITUTIONS";

            ResultSet resSimples = stmt.executeQuery(sql);

            while (resSimples.next()) {
                Institution institution = new Institution();

                institution.setId(resSimples.getInt("ID_INSTITUTION"));
                institution.setCnpj(resSimples.getString("CNPJ"));
                institution.setCompanyName(resSimples.getString("COMPANY_NAME"));

                sql =
                    "SELECT * FROM ADDRESSES WHERE ID_INSTITUTION = "
                    + resSimples.getInt("ID_INSTITUTION");

                ResultSet resComposto = stmtTwo.executeQuery(sql);

                while(resComposto.next()){
                    Address address = new Address();
                    Contact contact = new Contact();

                    address.setId(resComposto.getInt("ID_ADDRESS"));
                    address.setStreet(resComposto.getString("STREET"));
                    address.setDistrict(resComposto.getString("DISTRICT"));
                    address.setNumber(resComposto.getInt("NUM"));
                    address.setComplement(resComposto.getString("COMPLEMENT"));
                    address.setCity(resComposto.getString("CITY"));
                    address.setState(resComposto.getString("STATE"));
                    address.setZipCode(resComposto.getString("ZIP_CODE"));

                    institution.setAddress(address);

                    sql = "SELECT * FROM CONTACTS_ASSOCIATIONS CA JOIN CONTACTS c ON c.ID_CONTACT = CA.ID_CONTACT AND CA.ID_INSTITUTION = "
                    + resSimples.getInt("ID_INSTITUTION");

                    ResultSet restThree = stmtThree.executeQuery(sql);

                    while(restThree.next()){
                        contact.setId(restThree.getInt("ID_CONTACT"));
                        contact.setName(restThree.getString("NAME"));
                        contact.setEmail(restThree.getString("EMAIL"));
                        contact.setPhone(restThree.getString("PHONE"));
                        contact.setPhoneType(ContactPhoneType.ofType(restThree.getInt("PHONE_TYPE")));

                        institution.setContact(contact);
                    }
                }
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

    public Institution get(Integer idInstitution) throws Exception{
        ArrayList<Institution> list = list();
        for(Institution institution : list){
            if(institution.getId().equals(idInstitution)){
                return institution;
            }
        }
        return null;
    }
}