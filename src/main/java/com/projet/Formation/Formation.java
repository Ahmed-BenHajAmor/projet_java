package com.projet.Formation;

import java.sql.PreparedStatement;
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

        PreparedStatement pst = ConnectionBD.con.prepareStatement("insert into formation (titre, description, formateur) values(?,?,?)");
        pst.setString(1, titre);
        pst.setString(2, description);

        int formateurID;
        ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = "+formateur.email);
        res.next();
        formateurID = res.getInt("user_id");

        int changesNumber = communicationBD.insert("formation", new String[]{"titre", "description", "formateur"}, new Object[]{titre, description, formateurID});
        if(changesNumber < 0){
            System.out.println("formation added succefully");
        }else{
            System.out.println("formation ajout erreur");
        }
    }
   

}
