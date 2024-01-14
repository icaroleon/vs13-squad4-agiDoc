package service;

import model.address.Address;
import model.contact.Contact;
import model.contact.ContactPhoneType;
import service.IService;
import exception.DatabaseException;
import model.competitor.Competitor;
import database.DBConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompetitorService implements IService<Integer, Competitor> {
    @Override
    public Integer getNextId(Connection con) throws SQLException {
        String sql = "SELECT SEQ_COMPETITORS.nextval mysequence from DUAL";

        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Competitor create(Competitor competitor) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            Integer nextId = this.getNextId(con);
            competitor.setId(nextId);

            String sqlInsert = """
                        INSERT INTO COMPETITORS (ID_COMPETITOR, COMPANY_NAME, CNPJ)
                        VALUES (?, ?, ?)
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlInsert);

            stmt.setInt(1, competitor.getId());
            stmt.setString(2, competitor.getCompanyName());
            stmt.setString(3, competitor.getCnpj());

            int res = stmt.executeUpdate();

            System.out.println(res + "competitor adicionado");
            return competitor;
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
    public ArrayList<Competitor> list() throws DatabaseException {
        ArrayList<Competitor> competitorList = new ArrayList<>();
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sqlSelect = """
                    SELECT * FROM COMPETITORS C
                    JOIN PROCESSES_COMPETITORS PC ON PC.ID_COMPETITOR = C.IN_COMPETITOR
                    JOIN CONTACTS_ASSOCIATIONS CA ON CA.ID_COMPETITOR = C.ID_COMPETITOR
                    JOIN CONTACTS CO ON CO.ID_CONTACT = CA.ID_CONTACT
                    JOIN ADDRESSES_ASSOCIATIONS AA ON AA.ID_COMPETITOR = C.ID_COMPETITOR
                    JOIN ADDRESSES A ON A.ID_ADDRESS = AA.ID_ADDRESS
                    """;

            ResultSet res = stmt.executeQuery(sqlSelect);

            while (res.next()) {
                Competitor competitor = new Competitor();
                Address address = new Address();
                Contact contact = new Contact();

                competitor.setId(res.getInt("ID_COMPETITOR"));
                competitor.setCompanyName(res.getString("COMPANY_NAME"));
                competitor.setCnpj(res.getString("CNPJ"));
                competitor.setProcessId(res.getInt("ID_PROCESS"));
                competitor.setIsContracted(res.getInt("IS_ENABLED"));

                address.setId(res.getInt("ID_ADDRESS"));
                address.setStreet(res.getString("STREET"));
                address.setDistrict(res.getString("DISTRICT"));
                address.setNumber(res.getInt("NUMBER"));
                address.setComplement(res.getString("COMPLEMENT"));
                address.setCity(res.getString("CITY"));
                address.setState(res.getString("STATE"));
                address.setZipCode(res.getString("ZIP_CODE"));


                contact.setId(res.getInt("ID_CONTACT"));
                contact.setName(res.getString("NAME"));
                contact.setEmail(res.getString("EMAIL"));
                contact.setPhone(res.getString("PHONE"));
                contact.setPhoneType(ContactPhoneType.ofType(res.getInt("TYPE")));

                competitor.setAddress(address);
                competitor.setContact(contact);

                competitorList.add(competitor);
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
        return competitorList;
    }

    @Override
    public boolean delete(Integer id) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql = "DELETE FROM COMPETITOR WHERE ID_COMPETITOR = ?";

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

    @Override
    public boolean update(Integer id, Competitor competitor) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlUpdate = """
                UPDATE COMPETITORS SET
                    COMPANY_NAME = ?
                    CNPJ = ?
                WHERE ID_COMPETITOR = ?                            
                """;

            PreparedStatement stmt = con.prepareStatement(sqlUpdate);

            stmt.setString(1, competitor.getCompanyName());
            stmt.setString(2, competitor.getCnpj());
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();

            return res > 0;
        } catch(SQLException e) {
            throw new DatabaseException(e.getCause());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // PEGA UM COMPETIDOR
    public Competitor get(int id) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            String sqlSelect = """
                    SELECT * FROM COMPETITORS C
                    JOIN CONTACTS_ASSOCIATIONS CA ON CA.ID_COMPETITOR = C.ID_COMPETITOR
                    JOIN CONTACTS CO ON CO.ID_CONTACT = CA.ID_CONTACT
                    JOIN ADDRESSES_ASSOCIATIONS AA ON AA.ID_COMPETITOR = C.ID_COMPETITOR
                    JOIN ADDRESSES A ON A.ID_ADDRESS = AA.ID_ADDRESS
                    WHERE ID_COMPETITOR = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            Competitor competitor = new Competitor();
            Address address = new Address();
            Contact contact = new Contact();

            competitor.setId(res.getInt("ID_COMPETITOR"));
            competitor.setCompanyName(res.getString("COMPANY_NAME"));
            competitor.setCnpj(res.getString("CNPJ"));

            address.setId(res.getInt("ID_ADDRESS"));
            address.setStreet(res.getString("STREET"));
            address.setDistrict(res.getString("DISTRICT"));
            address.setNumber(res.getInt("NUMBER"));
            address.setComplement(res.getString("COMPLEMENT"));
            address.setCity(res.getString("CITY"));
            address.setState(res.getString("STATE"));
            address.setZipCode(res.getString("ZIP_CODE"));


            contact.setId(res.getInt("ID_CONTACT"));
            contact.setName(res.getString("NAME"));
            contact.setEmail(res.getString("EMAIL"));
            contact.setPhone(res.getString("PHONE"));
            contact.setPhoneType(ContactPhoneType.ofType(res.getInt("TYPE")));

            competitor.setAddress(address);
            competitor.setContact(contact);

            return competitor;
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

    public boolean addCompetitorToProcess(int competitorId, int processId) throws DatabaseException {
        Connection con = null;

        try {
            String sqlInsert = """
                        INSERT INTO PROCESSES_COMPETITORS (ID_PROCESS, ID_COMPETITOR, IS_ENABLED)
                        VALUES (?, ?, ?)
                    """;

            PreparedStatement pstmt = con.prepareStatement(sqlInsert);

            pstmt.setInt(1, processId);
            pstmt.setInt(2, competitorId);
            pstmt.setInt(3, 0);

            int res = pstmt.executeUpdate();

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
}
