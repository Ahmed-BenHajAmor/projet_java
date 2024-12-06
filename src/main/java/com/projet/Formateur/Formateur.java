package com.projet.Formateur;

import java.util.Vector;

import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;

public class Formateur extends Utilisateur {
    Vector<Formation> formations;

    public Formateur(String nom, String email, String motDePasse){
        super(nom, email, motDePasse);

    }
    void ajouterFormation(Formation formation){
        formations.add(formation);
    }
}
