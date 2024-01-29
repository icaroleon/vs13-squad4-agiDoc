package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;

@RequiredArgsConstructor
public class CompetitorRepository implements IRepository<Integer, Competitor> {
    private final DBConnection dbConnection;

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
            con = this.dbConnection.getConnection();

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
            con = this.dbConnection.getConnection();
            Statement stmt = con.createStatement();

            String sqlSelect = """
                    SELECT * FROM COMPETITORS C
                    JOIN PROCESSES_COMPETITORS PC ON PC.ID_COMPETITOR = C.ID_COMPETITOR
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
                contact.setPhoneType(ContactPhoneType.ofType(res.getInt("PHONE_TYPE")));

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
    public void delete(Integer id) throws DatabaseException {
        Connection con = null;
        try {
            con = this.dbConnection.getConnection();

            String sql = "DELETE FROM COMPETITORS WHERE ID_COMPETITOR = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
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
    public Competitor update(Integer id, Competitor competitor) throws DatabaseException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            String sqlUpdate = """
                UPDATE COMPETITORS SET
                    COMPANY_NAME = ?,
                    CNPJ = ?
                WHERE ID_COMPETITOR = ?                            
                """;

            PreparedStatement stmt = con.prepareStatement(sqlUpdate);

            stmt.setString(1, competitor.getCompanyName());
            stmt.setString(2, competitor.getCnpj());
            stmt.setInt(3, id);

            int res = stmt.executeUpdate();

            return competitor;
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
            con = this.dbConnection.getConnection();

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

            if (res.next()) {
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
                contact.setPhoneType(ContactPhoneType.ofType(res.getInt("PHONE_TYPE")));

                competitor.setAddress(address);
                competitor.setContact(contact);
            }

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
            con = this.dbConnection.getConnection();

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

    public boolean removeCompetitorToProcess(int competitorId, int processId) throws DatabaseException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            String sqlDelete = "DELETE FROM PROCESSES_COMPETITORS WHERE ID_COMPETITOR = ? AND ID_PROCESS = ?";

            PreparedStatement pstmt = con.prepareStatement(sqlDelete);

            pstmt.setInt(1, competitorId);
            pstmt.setInt(2, processId);

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
