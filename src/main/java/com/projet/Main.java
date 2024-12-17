package com.projet;

import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formateur.Formateur;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Initialisation de la connexion à la base de données
        ConnectionBD.init();
        Formateur f = new Formateur("aa", "aa", "123");
        ConnectionBD.close();
    }
}