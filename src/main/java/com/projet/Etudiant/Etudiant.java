package com.projet.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Exceptions.FormationDejaInscriteException;
import com.projet.Formation.Formation;
import com.projet.Utilisateur.Utilisateur;
import com.projet.communicationBD.communicationBD;

public class Etudiant extends Utilisateur {
    Vector<Formation> listFormation = new Vector<Formation>();

    public Etudiant(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        try {
            ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+email+"'");
            res.next();
            int changesNumber = communicationBD.insert("etudiant", new String[]{"id_etudiant"}, new Object[]{res.getInt("id_user")} );
            if(changesNumber > 0) System.out.println("etudiant ajouter a la table etudiant");
            else System.out.println("probleme lors du l ajout de l etudiant a la table etudiant");
        } catch (SQLException e) {
            System.out.println("Probleme lors de la connexion du formateur");
        }
        




    }

    public void sinscrireFormation(Formation formation){

        try {
            if (formation == null || listFormation.contains(formation)){
                throw new FormationDejaInscriteException();
            }
            listFormation.add(formation);
            int idEtud;
            int idFormation;
            int formateurId;
            ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+formation.getFormateur().email+"'");
            res.next();
            formateurId = res.getInt("id_user");
            
            res = ConnectionBD.st.executeQuery("select id_formation from formation where titre = '"+formation.getTitre()+"' and discription = '"+formation.getDescription()+"' and formateur_id = '"+formateurId+"'");
            res.next();
            idFormation = res.getInt("id_formation");

            res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+this.email+"'");
            res.next();
            idEtud = res.getInt("id_user");

            int changesNumber = communicationBD.insert("assiste", new String[]{"id_etudiant", "id_formation"}, new Object[]{idEtud, idFormation});
            if(changesNumber > 0){
                System.out.println("eutiant inscri");
            }else{
                System.out.println("etud inscri faild");
            }
        } catch (FormationDejaInscriteException e){
            System.out.println(e);
        } catch(SQLException e){
            System.out.println("Probleme lors de l inscription du l etudiant");
        }
    }
}
