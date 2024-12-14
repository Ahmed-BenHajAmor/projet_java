package com.projet.ConnectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBD {
    public static Connection con;
    public static Statement st;
    public static void init() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/plateformedeformation";
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "Promysql001/");
        st = con.createStatement();
    }
    public static void close() throws SQLException{
        st.close();
        con.close();
    }
}
