package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public interface Repository<KEY, OBJECT> {
    Integer getNextId(Connection connection);

    OBJECT add(OBJECT object);

    boolean remove(KEY id);

    boolean edit(KEY id, OBJECT object);

    List<OBJECT> list();
}
