package com.projet;

import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formateur.Formateur;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBD.init();

        Formateur e = new Formateur("ahdmed", "a@da.com", "12d3");


        ConnectionBD.con.close();
        ConnectionBD.st.close();

    }
}