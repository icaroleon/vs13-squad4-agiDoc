package service;

import service.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IService<KEY, OBJECT> {
    Integer getNextId(Connection connection) throws SQLException;
    OBJECT get(KEY id) throws Exception;
    ArrayList<OBJECT> getAll() throws DatabaseException;
    OBJECT create(OBJECT object) throws DatabaseException;
    OBJECT update(KEY id, OBJECT object) throws DatabaseException;

    boolean delete(KEY id) throws DatabaseException;
}
