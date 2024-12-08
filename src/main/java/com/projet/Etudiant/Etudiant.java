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
    Vector<Formation> listFormation;

    public Etudiant(String nom, String email, String motDePasse) throws SQLException{
        super(nom, email, motDePasse);


        System.out.println("select id_user from utlisateur where email = "+email);
        ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+email+"'");
        res.next();
        int changesNumber = communicationBD.insert("etudiant", new String[]{"id_etudiant"}, new Object[]{res.getInt("id_user")} );
        if(changesNumber > 0) System.out.println("etudiant ajouter a la table etudiant");
        else System.out.println("probleme lors du l ajout de l etudiant a la table etudiant");




    }

    public void sinscrireFormation(Formation formation) throws SQLException{

        try {
            if (listFormation.contains(formation)){
                throw new FormationDejaInscriteException();
            }
            listFormation.add(formation);
            int idEtud;
            int idFormation;
            int formateurId;
            ResultSet res = ConnectionBD.st.executeQuery("select user_id from utilisateur where email = "+formation.getFormateur().email);
            res.next();
            formateurId = res.getInt("user_id");
            res = ConnectionBD.st.executeQuery("select id_formation from formation where titre = "+formation.getTitre()+" description = "+formation.getDescription()+" formateur_id = "+formateurId);
            res.next();
            idFormation = res.getInt("id_formation");
            res = ConnectionBD.st.executeQuery("select id_user from utlisateur where email = "+this.email);
            res.next();
            idEtud = res.getInt("user_id");
            int changesNumber = communicationBD.insert("assite", new String[]{"id_etudiant", "id_formation"}, new Object[]{idEtud, idFormation});
            if(changesNumber > 0){
                System.out.println("eutiant inscri");
            }else{
                System.out.println("etud inscri faild");
            }
        } catch (FormationDejaInscriteException e){
            System.out.println(e);
        }
    }
}
