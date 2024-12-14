package com.projet.communicationBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projet.ConnectionBD.ConnectionBD;

public class communicationBD {


    public static int insert(String table, String[] fields, Object[] values) throws SQLException{
        String fieldsString = "(";
        for(int i = 0; i<fields.length-1; i++){
            fieldsString += fields[i] + ", ";
        }
        fieldsString += fields[fields.length-1]+")";

        String valuesQuestionMarkString = "(";
        for(int i = 0; i<values.length-1; i++){
            valuesQuestionMarkString += "?,";
        }
        valuesQuestionMarkString += "?)";

        PreparedStatement pst = ConnectionBD.con.prepareStatement("insert into "+table+" "+fieldsString+" values"+valuesQuestionMarkString);
        for(int i = 0; i<values.length; i++){
            Object value = values[i];
            if (value instanceof String) {
                pst.setString(i + 1, (String) value);
            } else if (value instanceof Integer) {
                pst.setInt(i + 1, (Integer) value);
            } else if (value instanceof Double) {
                pst.setDouble(i + 1, (Double) value);
            }
        }
        int changesNumber = pst.executeUpdate();
        return changesNumber;
    }
}
