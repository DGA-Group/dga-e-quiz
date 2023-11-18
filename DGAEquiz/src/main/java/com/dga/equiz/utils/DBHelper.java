package com.dga.equiz.utils;

import java.sql.*;

/**
 * The DBHelper class provides utility methods for executing SQL queries and managing database connections.
 */
public class DBHelper {
    public static final String MysqlURL = "jdbc:mysql://" + SecretKey.HOST + ":" + SecretKey.PORT + "/" + SecretKey.DATABASE;
    private static final String SqliteURL = "jdbc:sqlite:src/main/resources/database/dict_hh.db";

    /**
     * Executes the provided SQL query and returns the result set.
     * Use for SELECT query.
     *
     * @param sqlQuery the SQL query to be executed
     * @return a ResultSet containing the results of the query
     * @throws SQLException if a database access error occurs or the SQL statement does not return a ResultSet object
     */
    public static ResultSet executeQuery(String sqlQuery) throws SQLException {
        Connection connection = DriverManager.getConnection(MysqlURL, SecretKey.USERNAME, SecretKey.PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }

    /**
     * Executes the provided SQL update query.
     * Use for UPDATE, INSERT, DELETE,...
     *
     * @param sqlQuery the SQL update query to be executed
     * @return either the row count for SQL Data Manipulation Language (DML) statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs or the SQL statement does not return a ResultSet object
     */
    public static int executeUpdate(String sqlQuery) throws SQLException {
        try (Connection connection = DriverManager.getConnection(MysqlURL, SecretKey.USERNAME, SecretKey.PASSWORD);
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sqlQuery);
        }
    }


    /**
     * Executes the provided SQL query and returns the result set.
     * Use for SELECT query.
     *
     * @param sqlQuery the SQL query to be executed
     * @return a ResultSet containing the results of the query
     * @throws SQLException if a database access error occurs or the SQL statement does not return a ResultSet object
     */
    public static ResultSet executeQuerySqlite(String sqlQuery) throws SQLException {
        Connection connection = DriverManager.getConnection(SqliteURL);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlQuery);
    }

    /**
     * Executes the provided SQL update query.
     * Use for UPDATE, INSERT, DELETE,...
     *
     * @param sqlQuery the SQL update query to be executed
     * @return either the row count for SQL Data Manipulation Language (DML) statements or 0 for SQL statements that return nothing
     * @throws SQLException if a database access error occurs or the SQL statement does not return a ResultSet object
     */
    public static int executeUpdateSqlite(String sqlQuery) throws SQLException {
        try (Connection connection = DriverManager.getConnection(SqliteURL);
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(sqlQuery);
        }
    }


    /**
     * Closes the ResultSet, Statement, and Connection objects.
     *
     * @param resultSet  the ResultSet object to be closed
     * @param statement  the Statement object to be closed
     * @param connection the Connection object to be closed
     * @throws SQLException if an error occurs while closing the objects
     */
    public static void closeQuery(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }



}
