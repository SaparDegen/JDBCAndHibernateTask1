package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String username = "postgres";
    private static final String password = "1234";
    private static final String url = "jdbc:postgresql://localhost:5432/talentLMS_practice4";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Get connection successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
