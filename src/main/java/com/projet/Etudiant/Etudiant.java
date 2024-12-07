package com.projet.Etudiant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Exceptions.FormationDejaInscriteException;
import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;

public class Etudiant extends Utilisateur {
    Vector<Formation> listFormation;

    public Etudiant(String nom, String email, String motDePasse) throws SQLException{
        super(nom, email, motDePasse);
        ResultSet res = ConnectionBD.st.executeQuery("select * from utlisateur where email = "+email);
        if(res.next()){
            System.out.println("utilisateur exist !!!");
        }else{

            PreparedStatement pst = ConnectionBD.con.prepareStatement("insert into utilisateur (nom, email, mot_de_passe) values(?,?,?)");
            pst.setString(1, nom);
            pst.setString(2, email);
            pst.setString(3, motDePasse);
            int changesNumber = pst.executeUpdate();
            if(changesNumber > 0){
                System.out.println("utilisateur ajouter a la table utilisateur");
                res = ConnectionBD.st.executeQuery("select id_user from utlisateur where email = "+email);
                res.next();
                pst = ConnectionBD.con.prepareStatement("insert into Etudiant values(?)");
                pst.setInt(1, res.getInt("user_id"));
                changesNumber = pst.executeUpdate();
                if(changesNumber > 0) System.out.println("etudiant ajouter a la table etudiant");
                else System.out.println("probleme lors du l ajout de l etudiant a la table etudiant");
            }
            else System.out.println("probleme lors du l ajout de l utilisateur a la table utilisateur");
        }



    }

    public void sinscrireFormation(Formation formation){
        try {
            if (listFormation.contains(formation)){
                throw new FormationDejaInscriteException();
            }
            listFormation.add(formation);
        } catch (FormationDejaInscriteException e){
            System.out.println(e);
        }
    }
}
