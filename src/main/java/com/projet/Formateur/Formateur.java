package com.projet.Formateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;
import com.projet.communicationBD.communicationBD;

public class Formateur extends Utilisateur {
    // Liste des formations créées par le formateur
    Vector<Formation> formations = new Vector<Formation>();

    // Constructeur de la classe Formateur
    public Formateur(String nom, String email, String motDePasse) {
        // Appel du constructeur de la classe parente (Utilisateur) pour initialiser les attributs communs
        super(nom, email, motDePasse);

        try {
            // Requête SQL pour récupérer l'ID de l'utilisateur (formateur) en fonction de son email
            ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+email+"'");
            res.next(); // Avancer le curseur pour récupérer les résultats
            // Insère l'ID du formateur dans la table "formateur" de la base de données
            int changesNumber = communicationBD.insert("formateur", new String[]{"id_formateur"}, new Object[]{res.getInt("id_user")} );
            
            // Vérifie si l'insertion a réussi et affiche un message approprié
            if(changesNumber > 0) System.out.println("Formateur est ajouté a la table formateur");
            else System.out.println("Probleme lors du l'ajout du formateur");

        } catch (SQLException e) {
            // Gestion des erreurs liées à la base de données
            System.out.println("Probleme lors de la connexion du formateur");
        }
        
        
    }
    // Méthode pour ajouter une formation à la liste des formations du formateur
    public void ajouterFormation(Formation formation){
        // Ajoute la formation à la liste des formations créées par ce formateur
        formations.add(formation);
    }
}
