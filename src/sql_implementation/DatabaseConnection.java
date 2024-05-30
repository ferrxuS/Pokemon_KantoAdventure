package sql_implementation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String RELATIVE_DB_PATH = "src/sql_implementation/pokemon.db";

    public static Connection getConnection() throws SQLException {
        Path path = Paths.get(RELATIVE_DB_PATH);
        String url = "jdbc:sqlite:" + path.toAbsolutePath().toString();
        return DriverManager.getConnection(url);
    }

}
