package com.projet.communicationBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;

public class communicationBD {


    public static int insert(String table, String[] fields, Object[] values) throws SQLException{
        // Construction de la chaîne de colonnes au format SQL : (colonne1, colonne2, ...)
        String fieldsString = "(";
        for(int i = 0; i<fields.length-1; i++){
            fieldsString += fields[i] + ", ";
        }
        fieldsString += fields[fields.length-1]+")";

        // Construction de la chaîne de marqueurs de position : (?, ?, ...)
        String valuesQuestionMarkString = "(";
        for(int i = 0; i<values.length-1; i++){
            valuesQuestionMarkString += "?,";
        }
        valuesQuestionMarkString += "?)";

        // Préparation de la requête SQL avec des marqueurs de position pour les valeurs
        PreparedStatement pst = ConnectionBD.con.prepareStatement("insert into "+table+" "+fieldsString+" values"+valuesQuestionMarkString);
        for(int i = 0; i<values.length; i++){
            Object value = values[i];
            if (value instanceof String) {
                pst.setString(i + 1, (String) value); // Assignation d'une chaîne de caractères
            } else if (value instanceof Integer) {
                pst.setInt(i + 1, (Integer) value); // Assignation d'un entier
            } else if (value instanceof Double) {
                pst.setDouble(i + 1, (Double) value); // Assignation d'un nombre reel
            }
        }


        // Exécution de la requête SQL et récupération du nombre de lignes affectées
        int changesNumber = pst.executeUpdate();
        return changesNumber;
    }
}
