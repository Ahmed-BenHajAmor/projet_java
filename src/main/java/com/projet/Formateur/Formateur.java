package com.projet.Formateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;
import com.projet.communicationBD.communicationBD;

public class Formateur extends Utilisateur {
    Vector<Formation> formations;

    public Formateur(String nom, String email, String motDePasse) throws SQLException{
        super(nom, email, motDePasse);

        
        ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+email+"'");
        res.next();
        int changesNumber = communicationBD.insert("formateur", new String[]{"id_formateur"}, new Object[]{res.getInt("id_user")} );
        if(changesNumber > 0) System.out.println("formateur ajouter a la table formateur");
        else System.out.println("probleme lors du l ajout de l formateur a la table formateur");

    }
    public void ajouterFormation(Formation formation){
        formations.add(formation);
    }
}
