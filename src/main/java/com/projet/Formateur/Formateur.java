package com.projet.Formateur;

import java.util.Vector;





import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;

public class Formateur extends Utilisateur {
    // Liste des formations créées par le formateur
    Vector<Formation> formations = new Vector<Formation>();

    // Constructeur de la classe Formateur
    public Formateur(String nom, String email, String motDePasse) {
        
        super(nom, email, motDePasse, "formateur");

        
        
    }
    // Méthode pour ajouter une formation à la liste des formations du formateur
    public void ajouterFormation(Formation formation){
        // Ajoute la formation à la liste des formations créées par ce formateur
        formations.add(formation);
    }
}
