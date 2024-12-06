package com.projet.Formation;

import com.projet.Formateur.Formateur;

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
    public Formation(String titre, String description, Formateur formateur, double prix) {
        this.titre = titre;
        this.description = description;
        this.formateur = formateur;
        this.prix = prix;
    }
   

}
