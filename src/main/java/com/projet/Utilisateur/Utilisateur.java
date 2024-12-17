package com.projet.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Exceptions.UtilisateurNonTrouveException;
import com.projet.communicationBD.communicationBD;

public class Utilisateur {
    // Attributs de la classe Utilisateur
    public String nom;
    public String email;
    public String motDePasse;

    // Getter et Setter pour l'attribut nom
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour l'attribut email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter et Setter pour l'attribut MotDePasse
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    // Constructeur de la classe Utilisateur
    public Utilisateur(String nom,String email,String motDePasse, String type){
        try {
            // Exécution d'une requête SQL pour vérifier si un utilisateur existe déjà avec cet email
            ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+email+"'");
            if(type != "formateur" && type != "etudiant") System.out.println("verifie les donnees");
            // Si l'utilisateur n'existe pas, on l'ajoute à la base de données
            else if (!res.next()) {
                // On définit les attributs de l'utilisateur avec les valeurs passées en paramètres
                setNom(nom);
                setEmail(email);
                setMotDePasse(motDePasse);
                // Insertion des données de l'utilisateur dans la table "utilisateur"
                int changesNumber = communicationBD.insert("utilisateur", new String[]{"nom", "email", "mot_de_passe", "type"}, new String[]{nom, email, motDePasse, type});
                // Vérification de l'insertion
                if(changesNumber > 0){
                    System.out.println(type + " ajouter a la table utilisateur");
                }
                else System.out.println("probleme lors du l ajout de l "+type+" a la table utilisateur");
            // Si l'utilisateur existe déjà, on vérifie que le mot de passe correspond
            }else if(res.getString("mot_de_passe") == motDePasse){
                // Si le mot de passe correspond, on initialise l'utilisateur avec les données récupérées
                setNom(nom);
                setEmail(email);
                setMotDePasse(motDePasse);
            }
            // Si l'utilisateur existe mais que le mot de passe est incorrect, on lance une exception
            else{
                throw new UtilisateurNonTrouveException();
            }
           
        }catch(SQLException e){
            // Gestion des erreurs SQL
            System.out.println("Erreur de connexion a la BD");
        }
         catch (UtilisateurNonTrouveException e) {
            // Gestion de l'exception personnalisée lorsque l'utilisateur n'est pas trouvé ou que le mot de passe est incorrect
            System.out.println(e);
        }
    }    
}