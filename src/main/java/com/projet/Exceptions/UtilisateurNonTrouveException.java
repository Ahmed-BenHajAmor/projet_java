package com.projet.Exceptions;

public class UtilisateurNonTrouveException extends Exception{
    public String toString(){
        return "L'utilisateur essaie de se connecter avec des informations incorrectes";
    }
}
