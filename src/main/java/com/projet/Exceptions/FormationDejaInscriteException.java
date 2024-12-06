package com.projet.Exceptions;

public class FormationDejaInscriteException extends Exception {
    public String toString(){
        return "L étudiant essaie de s'inscrire à une formation qu'il a déjà suivie";
    }
}