package app.management.project.utils;

import app.management.project.servlets.RegisterServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);

    public static Connection getConnection() {
        final String url = "jdbc:postgresql://localhost:5432/project-management-app";
        final String user = "postgres";
        final String password = "admin";
        Connection connection = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return connection;
    }

}
