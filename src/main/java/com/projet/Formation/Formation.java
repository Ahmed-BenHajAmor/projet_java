package com.projet.Formation;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formateur.Formateur;
import com.projet.communicationBD.communicationBD;

public class Formation {
    String titre;
    String description;
    Formateur formateur;
    double prix;
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Formateur getFormateur() {
        return formateur;
    }
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public Formation(String titre, String description, Formateur formateur, double prix) throws SQLException {
        this.titre = titre;
        this.description = description;
        this.formateur = formateur;
        this.prix = prix;

        formateur.ajouterFormation(this);
        int formateurID;
        ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+formateur.email+"'");
        res.next();
        formateurID = res.getInt("id_user");

        int changesNumber = communicationBD.insert("formation", new String[]{"titre", "discription", "formateur_id"}, new Object[]{titre, description, formateurID});
        if(changesNumber > 0){
            System.out.println("formation added succefully");
        }else{
            System.out.println("formation ajout erreur");
        }
    }
   

}
