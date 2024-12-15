package com.projet;

import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Etudiant.Etudiant;
import com.projet.Formateur.Formateur;
import com.projet.Formation.Formation;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBD.init();

        Formateur e = new Formateur("ahmed", "a@da.com", "123qs");
        Etudiant et = new Etudiant("aaa", "qsdsqda@da.com", "12dfsd3");
        Formation f = new Formation("fff","aaa", e, 1.1);
        e.ajouterFormation(f);
        et.sinscrireFormation(f);
        ConnectionBD.close();
    }
}