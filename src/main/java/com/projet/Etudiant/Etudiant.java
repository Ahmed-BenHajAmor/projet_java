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
    // Liste des formations auxquelles l'étudiant est inscrit
    Vector<Formation> listFormation = new Vector<Formation>();

    // Constructeur de la classe Etudiant
    public Etudiant(String nom, String email, String motDePasse){
        // Appel du constructeur de la classe parente (Utilisateur)
        super(nom, email, motDePasse);
        try {
             // Récupère l'id de l'utilisateur (étudiant) à partir de l'email
            ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+email+"'");
            res.next();// Avancer le curseur pour récupérer les résultats
            int changesNumber = communicationBD.insert("etudiant", new String[]{"id_etudiant"}, new Object[]{res.getInt("id_user")} );
             
             // Vérifie si l'insertion a réussi
            if(changesNumber > 0) System.out.println("Etudiant est ajouté a la table etudiant");
            else System.out.println("Probleme lors du l ajout de l etudiant a la table etudiant");
        } catch (SQLException e) {
            // Gestion des exceptions liées à la connexion ou à la requête SQL
            System.out.println("Probleme lors de la connexion du formateur");
        }
        

    }
<<<<<<< HEAD
    // Méthode pour inscrire l'étudiant à une formation
    public void sinscrireFormation(Formation formation) {
=======

    public void sinscrireFormation(Formation formation){
>>>>>>> 1e07a181538bb2d6574ab031432a98fc23ed94c2

        try {
            // Vérifie si la formation est déjà inscrite ou n'est pas valide
            if (formation == null || listFormation.contains(formation)){
                // Lance une exception si la formation est déjà inscrite
                throw new FormationDejaInscriteException();
            }
            // Ajoute la formation à la liste des formations de l'étudiant
            listFormation.add(formation);

            // Déclare les variables pour l'ID de l'étudiant, de la formation et du formateur
            int idEtud;
            int idFormation;
            int formateurId;

            // Recherche l'ID du formateur en fonction de son email
            ResultSet res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+formation.getFormateur().email+"'");
            res.next();
            formateurId = res.getInt("id_user");
            
            // Recherche l'ID de la formation en fonction du titre, de la description et de l'ID du formateur
            res = ConnectionBD.st.executeQuery("select id_formation from formation where titre = '"+formation.getTitre()+"' and discription = '"+formation.getDescription()+"' and formateur_id = '"+formateurId+"'");
            res.next();
            idFormation = res.getInt("id_formation");

            // Recherche l'ID de l'étudiant à partir de son email
            res = ConnectionBD.st.executeQuery("select id_user from utilisateur where email = '"+this.email+"'");
            res.next();
            idEtud = res.getInt("id_user");

            // Insère l'association entre l'étudiant et la formation dans la table "assiste"
            int changesNumber = communicationBD.insert("assiste", new String[]{"id_etudiant", "id_formation"}, new Object[]{idEtud, idFormation});
           
            // Vérifie si l'insertion a réussi
            if(changesNumber > 0){
                System.out.println("Etudiant inscrit");
            }else{
                System.out.println("Probleme lors de l'inscription d'etudiant");
            }
        } catch (FormationDejaInscriteException e){
            // Gestion des exceptions si la formation est déjà inscrite
            System.out.println(e);
        } catch(SQLException e){
            // Gestion des erreurs liées à la base de données
            System.out.println("Probleme lors de l'inscription d'etudiant");
        }
    }
}
