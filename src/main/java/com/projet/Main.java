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

        // Création d'un objet Formateur avec les informations nécessaires (nom, email, mot de passe)
        Formateur ahmed = new Formateur("Ahmed", "ahmed@da.com", "123qs");

        // Création d'un objet Etudiant avec les informations nécessaires (nom, email, mot de passe)
        Etudiant wassim = new Etudiant("Wassim", "wassim@da.com", "12dfsd3");

        // Création d'un objet Formation avec les informations nécessaires (titre, description, formateur, prix)
        Formation linux = new Formation("Linux","La meilleure formation", ahmed, 250.50);

        // Ajout de la formation à la liste des formations du formateur
        ahmed.ajouterFormation(linux);

        // L'étudiant s'inscrit à la formation
        wassim.sinscrireFormation(linux);

        // Fermeture de la connexion à la base de données
        ConnectionBD.close();
    }
}