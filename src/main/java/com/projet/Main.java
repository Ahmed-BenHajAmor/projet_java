package com.projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/plateformedeformation";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "root", "Promysql001/");
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from utilisateur");

        res.next();
        String s = res.getString("nom");
        System.out.println(s);

        con.close();
        st.close();

    }
}