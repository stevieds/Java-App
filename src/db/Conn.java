package db;

import java.sql.*;

public class Conn {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/java_app";
    static final String USER = "root";
    static final String PW = "";
    private Connection connection;
    private Statement statement;

    public Conn () {}

    public ResultSet queryToSelect (String sql) {
        ResultSet rs = null;
        try {
            this.statement = this.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs = this.statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }


    public int queryToInsert (String sql) {
        int rs=0;
        try {
            this.statement = this.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs = this.statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(rs);
        return rs;
    }




    public void connect () {
        try {
            Class.forName(this.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(this.URL, this.USER, this.PW);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect () {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
