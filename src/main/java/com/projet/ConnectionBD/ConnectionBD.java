package com.projet.ConnectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class ConnectionBD {
    public static Connection con; // Objet Connection pour gérer la connexion à la base de données.
    public static Statement st;// Objet Statement pour exécuter les requêtes SQL.

    public static void init() throws ClassNotFoundException {
        try {
            String url = "jdbc:mysql://localhost:3306/plateformedeformation";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "Promysql001/");
            st = con.createStatement();
        } catch (SQLException e) {
            // Gestion des erreurs de connexion
            System.out.println("connection echouée");
        }
        
    }

    public static void close() throws SQLException{
        // Fermeture des objets Statement et Connection pour libérer les ressources
        st.close();
        con.close();
    }
}
