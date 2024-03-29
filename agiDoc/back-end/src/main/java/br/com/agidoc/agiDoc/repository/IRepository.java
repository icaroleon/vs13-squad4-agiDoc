package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.exception.DatabaseException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository<KEY, OBJECT> {
    Integer getNextId(Connection con) throws SQLException;
    OBJECT create(OBJECT object) throws Exception;
    OBJECT update(KEY id, OBJECT object) throws Exception;
    void delete(KEY id) throws Exception;
    ArrayList<OBJECT> list() throws DatabaseException;
}
