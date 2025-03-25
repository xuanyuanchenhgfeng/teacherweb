package com.main.web;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private  static Properties properties = new Properties();
    private JDBCUtils() {
    }
    static {
        InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(resourceAsStream);
            Class.forName(properties.getProperty("driver"));//加载驱动

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        Connection connection = null;
        //System.out.println(properties.getProperty("driver"));
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  connection;
    }
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs!=null) {
            rs.close();
        }
        if (ps!=null){
            ps.close();
        }
        if (conn!=null){
            conn.close();
        }
    }
    public static void close(Connection conn, PreparedStatement ps) throws SQLException {
        if (ps!=null){
            ps.close();
        }
        if (conn!=null){
            conn.close();
        }
    }
}
