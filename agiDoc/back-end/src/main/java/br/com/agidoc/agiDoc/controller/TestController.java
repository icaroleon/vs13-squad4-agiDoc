package br.com.agidoc.agiDoc.controller;

import br.com.agidoc.agiDoc.database.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public String testRoute () throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM USERS";

        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet res = stmt.executeQuery(sql);

        return res.toString();
    }
}
