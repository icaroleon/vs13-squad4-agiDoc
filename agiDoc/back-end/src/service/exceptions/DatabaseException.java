package service.exceptions;

import java.sql.SQLException;

public class DatabaseException extends Throwable {
    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
