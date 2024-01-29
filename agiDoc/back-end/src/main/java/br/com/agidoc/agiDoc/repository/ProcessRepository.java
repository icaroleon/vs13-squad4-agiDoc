package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProcessRepository implements IRepository<Integer, Process> {

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
            process.setProcessId(nextId);

            String sql = "INSERT INTO PROCESSES\n" + "(ID_PROCESS, PROCESS_NUMBER, TITLE, DESCRIPTION, STATUS, ID_INSTITUTION)\n" + "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, process.getProcessId());
            stmt.setString(2, process.getProcessNumber());
            stmt.setString(3, process.getTitle());
            stmt.setString(4, process.getDescription());
            stmt.setInt(5, process.getProcessStatus().getType());
            stmt.setInt(6, process.getInstitutionId());

            int res = (stmt.executeUpdate());
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

    public Process getProcessById(Integer idProcess) throws Exception {
        Connection con = null;
        List<Process> returnValue = new ArrayList<>();

        try {
            con = DBConnection.getConnection();

            PreparedStatement query = con.prepareStatement("SELECT * FROM PROCESSES WHERE ID_PROCESS = ?");

            query.setInt(1, idProcess);

            List<Process> result = resultProcess(con, returnValue, query);
            if (!result.isEmpty()) {
                return result.get(0);
            }
            throw new RegraDeNegocioException("No process found with ID = " + idProcess);

        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public Process searchProcess(String processNumber) throws Exception {
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

    private List<Process> resultProcess(Connection con, List<Process> returnValue, PreparedStatement query) throws Exception {
        try (ResultSet res = query.executeQuery()) {
            while (res.next()) {
                Process process = new Process();
                process.setProcessId(res.getInt("id_process"));
                process.setProcessNumber(res.getString("process_number"));
                process.setTitle(res.getString("title"));
                process.setDescription(res.getString("description"));
                process.setProcessStatus(ProcessStatus.ofType(res.getInt("status")));
                returnValue.add(process);
            }
        } catch (SQLException e) {
            throw new RegraDeNegocioException("Nenhum processo com o id fornecido foi encontrado.");
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
                process.setProcessId(res.getInt("id_process"));
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

    public Process update(Integer idProcess, Process process) throws Exception {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql = "UPDATE PROCESSES SET " +
                    " title = ?," +
                    " description = ?," +
                    " status = ?" +
                    " WHERE id_process = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, process.getTitle());
            stmt.setString(2, process.getDescription());
            stmt.setInt(3, process.getProcessStatus().getType());
            stmt.setInt(4, idProcess);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return process;
            } else {
                throw new RegraDeNegocioException("No process found with ID = " + idProcess);
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
    }

    public void delete(Integer idProcess) throws Exception {
        Connection con = null;
        try {
            con = DBConnection.getConnection();

            String sql = "DELETE FROM PROCESSES WHERE id_process = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idProcess);

            // Executa-se a consulta
            int rowsAffected  = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Processo removido com SUCESSO");
            } else {
                throw new RegraDeNegocioException("No process found with ID = " + idProcess);
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
    }
}