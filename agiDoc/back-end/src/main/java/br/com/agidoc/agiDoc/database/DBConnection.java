package br.com.agidoc.agiDoc.database;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class DBConnection {
    private static final String SERVER = "vemser-dbc.dbccompany.com.br";
    private static final String PORT = "25000";
    private static final String DATABASE = "xe";
    private static final String USER = "VS_13_EQUIPE_4";
    private static final String PASS = "oracle";
    private static final String SCHEMA = "VS_13_EQUIPE_4";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
        Connection con = DriverManager.getConnection(url, USER, PASS);
        con.createStatement().execute("alter session set current_schema=" + SCHEMA);
        return con;
    }
}