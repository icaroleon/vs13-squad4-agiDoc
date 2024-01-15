package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.process.Process;
import exception.DatabaseException;
import model.process.ProcessStatus;

public class ProcessService implements IService<Integer, Process> {
    private ArrayList<Process> processes = new ArrayList<>();

    public ProcessService() {}

    public ProcessService(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public Integer getNextId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_PROCESSES.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }
        return null;
    }

    @Override
    public Process create(Process process) throws DatabaseException {
        Connection con = null;

        try {
            con = DBConnection.getConnection();

            Integer nextId = this.getNextId(con);
            process.setId(nextId);

            String sql = "INSERT INTO PROCESSES\n" + "(ID_PROCESS, PROCESS_NUMBER, TITLE, DESCRIPTION, STATUS, ID_INSTITUTION)\n" + "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, process.getId());
            stmt.setString(2, process.getProcessNumber());
            stmt.setString(3, process.getTitle());
            stmt.setString(4, process.getDescription());
            stmt.setInt(5, process.getProcessStatus().getType());
            stmt.setInt(6, process.getInstitutionId());

            int res = stmt.executeUpdate();
            System.out.println("adicionarProcess.res= " + res);
            return process;
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

    public Process getProcessById(Integer id) throws Exception {
        Connection con = null;
        List<Process> returnValue = new ArrayList<>();

        try {
            con = DBConnection.getConnection();

            PreparedStatement query = con.prepareStatement("SELECT * FROM PROCESSES WHERE ID_PROCESS = ?");

            query.setInt(1, id);

            return resultProcess(con, returnValue, query).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Process searchProcess(String processNumber) throws DatabaseException {
        Connection con = null;
        List<Process> returnValue = new ArrayList<>();

        try {
            con = DBConnection.getConnection();

            PreparedStatement query = con.prepareStatement("SELECT * FROM PROCESSES WHERE PROCESS_NUMBER = ?");

            query.setString(1, processNumber);

            return resultProcess(con, returnValue, query).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Process> resultProcess(Connection con, List<Process> returnValue, PreparedStatement query) throws DatabaseException {
        try (ResultSet res = query.executeQuery()) {
            while (res.next()) {
                Process process = new Process();
                process.setId(res.getInt("id_process"));
                process.setProcessNumber(res.getString("process_number"));
                process.setTitle(res.getString("title"));
                process.setDescription(res.getString("description"));
                process.setProcessStatus(ProcessStatus.ofType(res.getInt("status")));
                returnValue.add(process);
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
        return returnValue;
    }

    @Override
    public ArrayList<Process> list() throws DatabaseException {
        ArrayList<Process> processes = new ArrayList<>();
        Connection con = null;

        try {
            con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PROCESSES";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Process process = new Process();
                process.setId(res.getInt("id_process"));
                process.setProcessNumber(res.getString("process_number"));
                process.setTitle(res.getString("title"));
                process.setDescription(res.getString("description"));
                process.setProcessStatus(ProcessStatus.ofType(res.getInt("status")));
                processes.add(process);
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
        return processes;
    }

    public boolean update(Integer id, Process process) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PROCESSES SET ");
            sql.append(" title = ?,");
            sql.append(" description = ?,");
            sql.append(" status = ?");
            sql.append(" WHERE id_process = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, process.getTitle());
            stmt.setString(2, process.getDescription());
            stmt.setInt(3, process.getProcessStatus().getType());
            stmt.setInt(4, process.getId());

            int res = stmt.executeUpdate();
            System.out.println("editarPessoa.res=" + res);

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

    public boolean delete(Integer id) throws DatabaseException {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql = "DELETE FROM PROCESSES WHERE id_process = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerPessoaPorId.res=" + res);

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

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}

