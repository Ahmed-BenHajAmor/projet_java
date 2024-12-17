package com.projet;

import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Etudiant.Etudiant;
import com.projet.Formateur.Formateur;
import com.projet.Formation.Formation;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Initialisation de la connexion à la base de données
        ConnectionBD.init();
        Formateur f = new Formateur("aa", "aa", "dsdq");
        Formation form = new Formation("hh", "null", f, 1.4);
        Etudiant e = new Etudiant("aa", "aa", "123");
        e.sinscrireFormation(form);
        ConnectionBD.close();
    }
}