package com.projet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.projet.ConnectionBD.ConnectionBD;
import com.projet.Etudiant.Etudiant;
import com.projet.Formateur.Formateur;
import com.projet.Formation.Formation;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionBD.init();

        // Creation of the window
        JFrame frame = new JFrame("Swing Navigation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform cleanup
                try {
                    ConnectionBD.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // Exit the application
                System.exit(0);
            }
        });
        // Create a CardLayout to switch between panels
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout); // Panel to hold the CardLayout
        Border paddingCards = BorderFactory.createEmptyBorder(0, 67, 0, 0); // top, left, bottom, right

        // Set the border to the buttonPanel
        cardPanel.setBorder(paddingCards);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        
        // Create the buttons
        JButton goToEtudiantButton = new JButton("Etudiant");
        goToEtudiantButton.addActionListener(e -> cardLayout.show(cardPanel, "etudiantPanel"));

        JButton inscriEtudBtn = new JButton("inscription Etudiant");
        inscriEtudBtn.addActionListener(e -> cardLayout.show(cardPanel, "inscriEtudPanel"));

        JButton goToFormateurButton = new JButton("Formateur");
        goToFormateurButton.addActionListener(e -> cardLayout.show(cardPanel, "formateurPanel"));

        JButton goToFormationButton = new JButton("Formation");
        goToFormationButton.addActionListener(e -> cardLayout.show(cardPanel, "formationPanel"));
        
        // Add buttons to the button panel
        buttonPanel.add(goToEtudiantButton);
        buttonPanel.add(inscriEtudBtn);
        buttonPanel.add(goToFormateurButton);
        buttonPanel.add(goToFormationButton);

        Border padding = BorderFactory.createEmptyBorder(0, 0, 200, 0); // top, left, bottom, right

        // Set the border to the buttonPanel
        buttonPanel.setBorder(padding);

        // Create the panels for CardLayout
        JPanel etudiantPanel = new JPanel();
        etudiantPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout with 10px horizontal and vertical gap

        // Add labels and text fields with labels and fields next to each other
        etudiantPanel.add(new JLabel("Name:"));
        JTextField name = new JTextField(15); // Adjusted width
        name.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        etudiantPanel.add(name);

        etudiantPanel.add(new JLabel("Email:"));
        JTextField email = new JTextField(15); // Adjusted width
        email.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        etudiantPanel.add(email);

        etudiantPanel.add(new JLabel("mot de passe:"));
        JTextField pwd = new JTextField(15); // Adjusted width
        pwd.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        etudiantPanel.add(pwd);

        // Submit button
        JButton submitButton = new JButton("Submit");
        etudiantPanel.add(submitButton);
        submitButton.addActionListener(e -> {
            
            Etudiant etud = new Etudiant(name.getText(), email.getText(), pwd.getText());
        });

        // Panel for Formateur
        JPanel formateurPanel = new JPanel();
        formateurPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout with 10px horizontal and vertical gap

        // Add labels and text fields with labels and fields next to each other
        formateurPanel.add(new JLabel("Name:"));
        JTextField nameF = new JTextField(15); // Adjusted width
        nameF.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formateurPanel.add(nameF);

        formateurPanel.add(new JLabel("Email:"));
        JTextField emailF = new JTextField(15); // Adjusted width
        emailF.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formateurPanel.add(emailF);

        formateurPanel.add(new JLabel("mot de passe:"));
        JTextField pwdF = new JTextField(15); // Adjusted width
        pwdF.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formateurPanel.add(pwdF);

        // Submit button
        JButton submitButtonF = new JButton("Submit");
        formateurPanel.add(submitButtonF);
        submitButtonF.addActionListener(e -> {
            
            Formateur formateur = new Formateur(nameF.getText(), emailF.getText(), pwdF.getText());
        });

        // Panel for Formation
        JPanel formationPanel = new JPanel();
        formationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout with 10px horizontal and vertical gap

        // Add labels and text fields with labels and fields next to each other
        formationPanel.add(new JLabel("titre:"));
        JTextField titreFormation = new JTextField(15); // Adjusted width
        titreFormation.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formationPanel.add(titreFormation);

        formationPanel.add(new JLabel("description:"));
        JTextField descriptionFormation = new JTextField(15); // Adjusted width
        descriptionFormation.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formationPanel.add(descriptionFormation);

        formationPanel.add(new JLabel("email formateur :"));
        JTextField emailFormateurFormation = new JTextField(15); // Adjusted width
        emailFormateurFormation.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formationPanel.add(emailFormateurFormation);

        formationPanel.add(new JLabel("prix :"));
        JTextField prix = new JTextField(15); // Adjusted width
        prix.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        formationPanel.add(prix);

        // Submit button
        JButton submitButtonFormation = new JButton("Submit");
        formationPanel.add(submitButtonFormation);
        submitButtonFormation.addActionListener(e -> {
            try {
                ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailFormateurFormation.getText()+"' and type = 'formateur'");
                if(!res.next()){
                    System.out.println("in");
                    System.out.println("formateur non trouver");
                }else{
                    Formateur f = new Formateur(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                    Formation formation = new Formation(titreFormation.getText(), descriptionFormation.getText(), f, Double.parseDouble(prix.getText()));
                }
            } catch (SQLException ex) {
                System.out.println("erreur de communication avec la BD");
            }
        });



        JPanel inscriEtudPanel = new JPanel();
        inscriEtudPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout with 10px horizontal and vertical gap

        // Add labels and text fields with labels and fields next to each other
        inscriEtudPanel.add(new JLabel("titre formation:"));
        JTextField titreFormationIn = new JTextField(15); // Adjusted width
        titreFormationIn.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        inscriEtudPanel.add(titreFormationIn);

        inscriEtudPanel.add(new JLabel("description:"));
        JTextField descriptionFormationIn = new JTextField(15); // Adjusted width
        descriptionFormationIn.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        inscriEtudPanel.add(descriptionFormationIn);

        inscriEtudPanel.add(new JLabel("email formateur :"));
        JTextField emailFormateurFormatioIn = new JTextField(15); // Adjusted width
        emailFormateurFormatioIn.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        inscriEtudPanel.add(emailFormateurFormatioIn);

        inscriEtudPanel.add(new JLabel("prix :"));
        JTextField prixIn = new JTextField(15); // Adjusted width
        prixIn.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        inscriEtudPanel.add(prixIn);

        inscriEtudPanel.add(new JLabel("email etudiant :"));
        JTextField emailEtudIn = new JTextField(15); // Adjusted width
        emailEtudIn.setPreferredSize(new Dimension(200, 30)); // Custom size for height
        inscriEtudPanel.add(emailEtudIn);


        // Submit button
        JButton submitIn = new JButton("Submit");
        inscriEtudPanel.add(submitIn);
        submitIn.addActionListener(e -> {
            try {
                ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailEtudIn.getText()+"' and type = 'etudiant'");
                if(!res.next()){
                    System.out.println("etudiant non trouver");
                }else{
                    Etudiant etudiantIn = new Etudiant(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                    
                    res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailFormateurFormatioIn.getText()+"' and type = 'formateur'");
                    if(!res.next()){
                        System.out.println("formateur n'existe pas");
                    }else{
                        
                        Formateur formateurIn = new Formateur(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                        Formation formation = new Formation(titreFormationIn.getText(), descriptionFormationIn.getText(), formateurIn, Double.parseDouble(prixIn.getText()));
                        etudiantIn.sinscrireFormation(formation);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("erreur de communication avec la BD");
            }
        });



        // Add panels to the card panel with names for CardLayout
        cardPanel.add(etudiantPanel, "etudiantPanel");
        cardPanel.add(formateurPanel, "formateurPanel");
        cardPanel.add(formationPanel, "formationPanel");
        cardPanel.add(inscriEtudPanel, "inscriEtudPanel");

        // Set the layout of the main frame and add panels
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);  // Add button panel to the top
        frame.add(cardPanel, BorderLayout.CENTER);   // Add card panel to the center

        frame.setVisible(true);


    }
}
