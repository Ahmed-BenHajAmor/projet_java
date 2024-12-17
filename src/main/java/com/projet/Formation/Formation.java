package com.projet.Formation;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formateur.Formateur;
import com.projet.communicationBD.communicationBD;

public class Formation {
    // Attributs de la classe Formation
    private String titre;
    private String description;
    private Formateur formateur;
    double prix;

     // Getter et Setter pour l'attribut titre
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Getter et Setter pour l'attribut description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et Setter pour l'attribut formateur
    public Formateur getFormateur() {
        return formateur;
    }
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    // Getter et Setter pour l'attribut prix
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    // Constructeur de la classe Formation
    public Formation(String titre, String description, Formateur formateur, double prix)  {
        // Initialisation des attributs avec les valeurs fournies
        this.titre = titre;
        this.description = description;
        this.formateur = formateur;
        this.prix = prix;

        try {
            int formateurID;
            // Exécution de la requête pour obtenir les informations du formateur dans la base de données
            ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+formateur.email+"'");
            res.next(); // Avancer le curseur pour obtenir la première ligne de résultats
            formateurID = res.getInt("id_user");// Récupération de l'ID de l'utilisateur (formateur)

            // Insertion de la formation dans la base de données
            int changesNumber = communicationBD.insert("formation", new String[]{"titre", "description", "formateur_id", "prix"}, new Object[]{titre, description, formateurID, prix});
            
            // Vérification si l'insertion a réussi et affichage d'un message approprié
            if(changesNumber > 0){
                System.out.println("Formation est ajouté avec succés");
            }else{
                System.out.println("Probleme lors de l'ajout du formation");
            }
            
        } catch (SQLException e) {
            // Gestion des exceptions liées aux erreurs SQL
            System.out.println("Probleme lors de l ajout du formation");
        }
        
        
    }
   

}
