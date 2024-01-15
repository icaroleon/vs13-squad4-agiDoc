package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import exception.DatabaseException;
import model.department.Department;
import model.user.User;
import exception.DatabaseException;

public class UserService implements IService<Integer, User> {

    @Override
    public Integer getNextId(Connection con) throws SQLException {
        try {
            String sql = "SELECT SEQ_USERS.nextval mysequence from DUAL";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        }
    }

    @Override
    public User create(User user) throws DatabaseException {
        Connection con = null;
        try {
            if (user.getRegistration().isEmpty() || user.getName().isEmpty() || user.getUser().isEmpty() || user.getPassword().isEmpty() || user.getRole().isEmpty() || user.getPosition().isEmpty()) {
                System.out.println("Nenhum campo pode estar em branco. Tente novamente.");
                return null;
            }
            con = DBConnection.getConnection();
            Integer nextId = this.getNextId(con);
            user.setIdUser(nextId);

            String sql = """
                    INSERT INTO USERS
                    (ID_USER, REGISTRATION, NAME, "USER", PASSWORD, "ROLE", "POSITION", ID_DEPARTMENT)
                    VALUES(?,?,?,?,?,?,?,?)
                    """;
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, user.getIdUser());
            stmt.setString(2, user.getRegistration());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getUser());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getRole());
            stmt.setString(7, user.getPosition());
            stmt.setInt(8, user.getDepartment().getIdDepartment());

            int res = stmt.executeUpdate();
            System.out.println("adicionarContato.res" + res);
            return user;
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
    public boolean update(Integer id, User user) throws DatabaseException {
        Connection con = null;

        if (id == null) {
            System.out.println("Deve-se ser fornecido um id.");
            return false;
        }

        try {
            con = DBConnection.getConnection();
            ArrayList<User> ls = listUser(id);

            String checkSql = "SELECT COUNT(*) FROM USERS WHERE ID_USER = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Nenhum usuário encontrado com o id fornecido.");
                return false;
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USERS SET ");
            sql.append(" REGISTRATION = ?, ");
            sql.append(" NAME = ?, ");
            sql.append(" \"USER\" = ?, ");
            sql.append(" PASSWORD = ?, ");
            sql.append(" \"ROLE\" = ?, ");
            sql.append(" \"POSITION\" = ?, ");
            sql.append(" ID_DEPARTMENT = ? ");
            sql.append(" WHERE ID_USER = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, user.getRegistration().isEmpty() ? ls.get(0).getRegistration() : user.getRegistration());
            stmt.setString(2, user.getName().isEmpty() ? ls.get(0).getName() : user.getName());
            stmt.setString(3, user.getUser().isEmpty() ? ls.get(0).getUser() : user.getUser());
            stmt.setString(4, user.getPassword().isEmpty() ? ls.get(0).getPassword() : user.getPassword());
            stmt.setString(5, user.getRole().isEmpty() ? ls.get(0).getRole() : user.getRole());
            stmt.setString(6, user.getPosition().isEmpty() ? ls.get(0).getPosition() : user.getPosition());
            stmt.setInt(7, user.getDepartment().getIdDepartment() == 0 ? ls.get(0).getDepartment().getIdDepartment() : user.getDepartment().getIdDepartment());
            stmt.setInt(8, id);


            int res = stmt.executeUpdate();
            System.out.println("editarUser.res=" + res);

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
    public boolean delete(Integer id) throws DatabaseException {
        Connection con = null;
        if (id == null) {
            System.out.println("Deve-se ser fornecido um id.");
            return false;
        }

        try {
            con = DBConnection.getConnection();

            String checkSql = "SELECT COUNT(*) FROM USERS WHERE ID_USER = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                System.out.println("Nenhum usuário encontrado com o id fornecido.");
                return false;
            }

            String sql = "DELETE FROM USERS WHERE ID_USER = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } catch (NumberFormatException e) {
                System.out.println("O id fornecido não é um número.");
                return false;
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
    public ArrayList<User> list() throws DatabaseException {
        ArrayList<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT U.*, D.NAME AS NAME_DEPARTMENT " +
                    "FROM USERS U " +
                    "LEFT JOIN DEPARTMENTS D ON (D.ID_DEPARTMENT = U.ID_DEPARTMENT)";


            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                User user = getUserFromResultSet(res);
                users.add(user);
            }
            return users;
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

    public ArrayList<User> listUser (Integer id) throws  DatabaseException {
        ArrayList<User> users = new ArrayList<>();
        Connection con = null;

        if (id == null) {
            System.out.println("Deve-se ser fornecido um id.");
            return null;
        }
        try {
            con = DBConnection.getConnection();

            String sql =  "SELECT U.*, D.NAME AS NAME_DEPARTMENT " +
                    "FROM USERS U " +
                    "INNER JOIN DEPARTMENTS D ON (D.ID_DEPARTMENT = U.ID_DEPARTMENT) " +
                    "WHERE U.ID_USER = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                User user = getUserFromResultSet(res);
                users.add(user);
            }

            if (users.isEmpty()) {
                System.out.println("Usuário inexistente");
            }

            return users;
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

    private User getUserFromResultSet(ResultSet res) throws  SQLException {
        User user = new User();
        user.setIdUser(res.getInt("ID_USER"));
        user.setName(res.getString("NAME"));
        user.setUser(res.getString("USER"));
        user.setPassword(res.getString("PASSWORD"));
        user.setRegistration(res.getString("REGISTRATION"));
        user.setPosition(res.getString("POSITION"));

        Department department = new Department();
        department.setName(res.getString("NAME_DEPARTMENT"));
        department.setIdDepartment(res.getInt("ID_DEPARTMENT"));
        user.setDepartment(department);
        return user;
    }
}
