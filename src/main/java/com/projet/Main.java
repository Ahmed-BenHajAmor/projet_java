package com.projet;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBD.init();

        ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur");
        res.next();

        String s = res.getString("nom");
        System.out.println(s);

        ConnectionBD.con.close();
        ConnectionBD.st.close();

    }
}