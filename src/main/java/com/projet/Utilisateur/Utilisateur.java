package com.projet.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Exceptions.UtilisateurNonTrouveException;
import com.projet.communicationBD.communicationBD;

public class Utilisateur {
    public String nom;
    public String email;
    public String motDePasse;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public Utilisateur(String nom,String email,String motDePasse){
        try {
            ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+email+"'");
            if (!res.next()) {
                setNom(nom);
                setEmail(email);
                setMotDePasse(motDePasse);
        
                int changesNumber = communicationBD.insert("utilisateur", new String[]{"nom", "email", "mot_de_passe"}, new String[]{nom, email, motDePasse});
                if(changesNumber > 0){
                    System.out.println("utilisateur ajouter a la table utilisateur");
                }
                else System.out.println("probleme lors du l ajout de l utilisateur a la table utilisateur");
            }else if(res.getString("mot_de_passe") == motDePasse){
                setNom(nom);
                setEmail(email);
                setMotDePasse(motDePasse);
            }else{
                throw new UtilisateurNonTrouveException();
            }
           
        }catch(SQLException e){
            System.out.println("erreur de connexion a la BD");
        }
         catch (UtilisateurNonTrouveException e) {
            System.out.println(e);
        }
    }    
}
