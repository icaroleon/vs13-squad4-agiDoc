package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.address.Address;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;

@RequiredArgsConstructor
public class AddressRepository implements IRepository <Integer, Address> {
    private final DBConnection dbConnection;

    public Integer getNextId(Connection connection) throws DatabaseException {
        try {
            String sql = "SELECT SEQ_ADDRESSES.NEXTVAL MY_SEQUENCE FROM DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) return res.getInt("MY_SEQUENCE");

            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        }
    }

    public Integer getAssociationNextId(Connection con) throws DatabaseException {
        try {
            String sql = "SELECT SEQ_ADDRESSES_ASSOCIATIONS.NEXTVAL MY_SEQUENCE FROM DUAL";

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) return res.getInt("MY_SEQUENCE");

            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        }
    }

    @Override
    public Address create(Address address) throws DatabaseException {
        Connection con = null;
        try {
            con = dbConnection.getConnection();

            Integer nextId = this.getNextId(con);
            Integer associationNextId = this.getAssociationNextId(con);
            address.setId(nextId);

            String sql1 = """
                    INSERT INTO ADDRESSES (ID_ADDRESS, STREET, DISTRICT, "NUMBER", COMPLEMENT, CITY, STATE, ZIP_CODE)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmt1 = con.prepareStatement(sql1);

            stmt1.setInt(1, address.getId());
            stmt1.setString(2, address.getStreet());
            stmt1.setString(3, address.getDistrict());
            stmt1.setInt(4, address.getNumber());
            stmt1.setString(5, address.getComplement());
            stmt1.setString(6, address.getCity());
            stmt1.setString(7, address.getState());
            stmt1.setString(8, address.getZipCode());

            int res1 = stmt1.executeUpdate();

            String sql2 = """
                    INSERT INTO ADDRESSES_ASSOCIATIONS (ID_ADDRESS_ASSOCIATION, ID_ADDRESS, ID_COMPETITOR, ID_INSTITUTION)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement stmt2 = con.prepareStatement(sql2);

            stmt2.setInt(1, associationNextId);
            stmt2.setInt(2, address.getId());
            if (address.getAssociated().getType().equals(1)) {
                stmt2.setInt(3, address.getAssociatedId());
                stmt2.setNull(4, Types.INTEGER);
            }
            if (address.getAssociated().getType().equals(2)) {
                stmt2.setNull(3, Types.INTEGER);
                stmt2.setInt(4, address.getAssociatedId());
            }

            int res2 = stmt2.executeUpdate();

            return address;
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
    public Address update(Integer id, Address address) throws DatabaseException {
        Connection con = null;
        try {
            con = dbConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ADDRESSES SET");

            if (address.getStreet() != null) sql.append(" STREET = ?,");
            if (address.getDistrict() != null) sql.append(" DISTRICT = ?,");
            if (address.getNumber() != null) sql.append(" \"NUMBER\" = ?,");
            if (address.getComplement() != null) sql.append(" COMPLEMENT = ?,");
            if (address.getCity() != null) sql.append(" CITY = ?,");
            if (address.getState() != null) sql.append(" STATE = ?,");
            if (address.getZipCode() != null) sql.append(" ZIP_CODE = ?,");

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE ID_ADDRESS = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;

            if (address.getStreet() != null) stmt.setString(index++, address.getStreet());
            if (address.getDistrict() != null) stmt.setString(index++, address.getDistrict());
            if (address.getNumber() != null) stmt.setInt(index++, address.getNumber());
            if (address.getComplement() != null) stmt.setString(index++, address.getComplement());
            if (address.getCity() != null) stmt.setString(index++, address.getCity());
            if (address.getState() != null) stmt.setString(index++, address.getState());
            if (address.getZipCode() != null) stmt.setString(index++, address.getZipCode());

            stmt.setInt(index++, id);

            int res = stmt.executeUpdate();

            return address;
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
            con = dbConnection.getConnection();

            String sql1 = "DELETE FROM ADDRESSES_ASSOCIATIONS WHERE ID_ADDRESS = ?";

            PreparedStatement stmt1 = con.prepareStatement(sql1);

            stmt1.setInt(1, id);

            int res1 = stmt1.executeUpdate();

            String sql2 = "DELETE FROM ADDRESSES WHERE ID_ADDRESS = ?";

            PreparedStatement stmt2 = con.prepareStatement(sql2);

            stmt2.setInt(1, id);

            int res2 = stmt2.executeUpdate();
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
    public ArrayList<Address> list() throws DatabaseException {
        ArrayList<Address> addresses = new ArrayList<>();
        Connection con = null;
        try {
            con = dbConnection.getConnection();

            String sql1 = "SELECT * FROM ADDRESSES";

            Statement stmt1 = con.createStatement();
            ResultSet res1 = stmt1.executeQuery(sql1);

            while (res1.next()) {
                Address address = new Address();
                address.setId(res1.getInt("ID_ADDRESS"));
                address.setStreet(res1.getString("STREET"));
                address.setDistrict(res1.getString("DISTRICT"));
                address.setNumber(res1.getInt("NUMBER"));
                address.setComplement(res1.getString("COMPLEMENT"));
                address.setCity(res1.getString("CITY"));
                address.setState(res1.getString("STATE"));
                address.setZipCode(res1.getString("ZIP_CODE"));

                String sql2 = "SELECT * FROM ADDRESSES_ASSOCIATIONS WHERE ID_ADDRESS = ?";

                PreparedStatement stmt2 = con.prepareStatement(sql2);

                stmt2.setInt(1, address.getId());

                ResultSet res2 = stmt2.executeQuery();

                if (res2.next()) {
                    if (res2.getInt("ID_COMPETITOR") != 0) {
                        address.setAssociated(Associated.ofType(1));
                        address.setAssociatedId(res2.getInt("ID_COMPETITOR"));
                    }
                    if (res2.getInt("ID_INSTITUTION") != 0) {
                        address.setAssociated(Associated.ofType(2));
                        address.setAssociatedId(res2.getInt("ID_INSTITUTION"));
                    }
                }

                addresses.add(address);
            }

            return addresses;
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
