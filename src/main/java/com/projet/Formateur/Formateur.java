package com.projet.Formateur;

import java.util.Vector;

import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;

public class Formateur extends Utilisateur {
    Vector<Formation> formations = new Vector<Formation>();

    public Formateur(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse, "formateur");
        
    }
    public void ajouterFormation(Formation formation){
        formations.add(formation);
    }
}
