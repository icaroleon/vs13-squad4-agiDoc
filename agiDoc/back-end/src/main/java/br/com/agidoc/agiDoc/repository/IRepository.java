package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository<KEY, OBJECT> {
    Integer getNextId(Connection con) throws SQLException;
    OBJECT create(OBJECT object) throws DatabaseException;
    boolean update(KEY id, OBJECT object) throws DatabaseException;
    boolean delete(KEY id) throws DatabaseException;
    ArrayList<OBJECT> list() throws DatabaseException;
}
