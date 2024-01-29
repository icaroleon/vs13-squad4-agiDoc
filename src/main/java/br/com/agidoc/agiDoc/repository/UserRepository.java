package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.department.Department;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.user.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserRepository implements IRepository<Integer, User> {
    private final DBConnection dbConnection;

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
    public User create(User user) throws Exception {
        Connection con = null;
        try {
            con = this.dbConnection.getConnection();
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
            if(e.getErrorCode() == 1)
                throw new RegraDeNegocioException("Usuário já existente no banco!");

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
    public User update(Integer id, User user) throws DatabaseException, RegraDeNegocioException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();
            User userToUpdate = getUserById(id);

            String checkSql = "SELECT COUNT(*) FROM USERS WHERE ID_USER = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next() || rs.getInt(1) == 0)
                throw new RegraDeNegocioException("Nenhum usuario com o id fornecido foi encontrado.");



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

            stmt.setString(1, user.getRegistration().isEmpty() ? userToUpdate.getRegistration() : user.getRegistration());
            stmt.setString(2, user.getName().isEmpty() ? userToUpdate.getName() : user.getName());
            stmt.setString(3, user.getUser().isEmpty() ? userToUpdate.getUser() : user.getUser());
            stmt.setString(4, user.getPassword().isEmpty() ? userToUpdate.getPassword() : user.getPassword());
            stmt.setString(5, user.getRole().isEmpty() ? userToUpdate.getRole() : user.getRole());
            stmt.setString(6, user.getPosition().isEmpty() ? userToUpdate.getPosition() : user.getPosition());
            stmt.setInt(7, user.getDepartment().getIdDepartment() == 0 ? userToUpdate.getDepartment().getIdDepartment() : user.getDepartment().getIdDepartment());
            stmt.setInt(8, id);


            int res = stmt.executeUpdate();
            System.out.println("editarUser.res=" + res);

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
    public void delete(Integer id) throws DatabaseException, RegraDeNegocioException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            String checkSql = "SELECT COUNT(*) FROM USERS WHERE ID_USER = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next() || rs.getInt(1) == 0)
                throw new RegraDeNegocioException("Nenhum usuario com o id fornecido foi encontrado.");

            String sql = "DELETE FROM USERS WHERE ID_USER = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);
        } catch (SQLException e) {
            throw new DatabaseException(e.getCause());
        } catch (NumberFormatException e) {
            throw new RegraDeNegocioException("Id fornecido não é um numero.");
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
            con = this.dbConnection.getConnection();
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

    public User getUserById (Integer id) throws DatabaseException, RegraDeNegocioException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            String sql =  "SELECT U.*, D.NAME AS NAME_DEPARTMENT " +
                    "FROM USERS U " +
                    "INNER JOIN DEPARTMENTS D ON (D.ID_DEPARTMENT = U.ID_DEPARTMENT) " +
                    "WHERE U.ID_USER = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if(!res.next())
                throw new RegraDeNegocioException("Nenhum usuario com o id fornecido foi encontrado.");

            User user = new User();

            user.setIdUser(res.getInt("ID_USER"));
            user.setRegistration(res.getString("REGISTRATION"));
            user.setName(res.getString("NAME"));
            user.setUser(res.getString("USER"));
            user.setPassword(res.getString("PASSWORD"));
            user.setRole(res.getString("ROLE"));
            user.setPosition(res.getString("POSITION"));

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

    public User getUserByUsername (String username) throws DatabaseException, RegraDeNegocioException {
        Connection con = null;

        try {
            con = this.dbConnection.getConnection();

            String sql =  """
                    SELECT U.*, D.NAME AS NAME_DEPARTMENT
                    FROM USERS U 
                    INNER JOIN DEPARTMENTS D ON (D.ID_DEPARTMENT = U.ID_DEPARTMENT) 
                    WHERE U."USER" = ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet res = stmt.executeQuery();

            if(!res.next())
                throw new RegraDeNegocioException("Nenhum usuario com o username fornecido foi encontrado.");

            User user = new User();

            user.setIdUser(res.getInt("ID_USER"));
            user.setRegistration(res.getString("REGISTRATION"));
            user.setName(res.getString("NAME"));
            user.setUser(res.getString("USER"));
            user.setPassword(res.getString("PASSWORD"));
            user.setRole(res.getString("ROLE"));
            user.setPosition(res.getString("POSITION"));

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
