package com.dga.equiz.utils;

import java.sql.*;

public class DBHelper {
    private static final String URL = "jdbc:mysql://" + SecretKey.HOST + ":" + SecretKey.PORT + "/" + SecretKey.DATABASE;

    /**
     * Executes an SQL query using the specified SQL statement and returns a ResultSet containing the query results.
     *
     * @param sqlQuery the SQL query to be executed.
     * @return A ResultSet containing the results of the SQL query.
     * @throws SQLException if a database access error occurs or the SQL query is invalid.
     */
    public static ResultSet query(String sqlQuery) throws SQLException {
        // Establish a database connection using the specified URL, username, and password
        Connection connection = DriverManager.getConnection(URL, SecretKey.USERNAME, SecretKey.PASSWORD);

        // Create a Statement object for executing the SQL query
        Statement statement = connection.createStatement();

        // Execute the SQL query and return the ResultSet containing the results
        return statement.executeQuery(sqlQuery);
    }
}
