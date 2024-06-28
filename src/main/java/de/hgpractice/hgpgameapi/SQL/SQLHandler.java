package de.hgpractice.hgpgameapi.SQL;

import java.sql.*;

public abstract class SQLHandler {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    private Connection con;

    private boolean isConnected = false;

    /**
     * Creates a new SQLHandler with basic actions to connect, close and query a MySQL database.
     * @param host The host IP or Domain
     * @param port The port of the MySQL server
     * @param database The database to connect to
     * @param username The username to connect with
     * @param password The password of the user
     */
    public SQLHandler(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Connects to the MySQL database with the given credentials.
     */
    public void connect() {
        if (!this.isConnected) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
                System.out.println("Die MySQL Verbindung wurde erfolgreich zu " + this.host + ":" + this.port + "/" + this.database + " aufgebaut.");
                isConnected = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the connection to the MySQL database.
     */
    public void close() {
        if (this.isConnected) {
            try {
                this.con.close();
                System.out.println("Die MySQL Verbindung zu " + this.host + ":" + this.port + "/" + this.database + " wurde erfolgreich geschlossen.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Executes a query on the MySQL database.
     * @param query The query to execute
     */
    public void query(String query) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Executes a query on the MySQL database and returns the result.
     * @param query The query to execute
     * @return The result of the query
     */
    public ResultSet getQueryResult(String query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a table in the MySQL database. Can be implemented with {@link #query(String)}.
     */
    public abstract void createTable();
}
