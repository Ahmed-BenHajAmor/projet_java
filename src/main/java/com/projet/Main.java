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
        // Initialisation de la connexion à la base de données
        ConnectionBD.init();

        // Création de la fenêtre principale
        JFrame frame = new JFrame("Swing Navigation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);

        // Gestion de la fermeture de la fenêtre pour effectuer un nettoyage
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ConnectionBD.close();// Fermeture de la connexion à la base de données
                } catch (SQLException e1) {
                    e1.printStackTrace();// Gestion des erreurs lors de la fermeture
                }
                System.exit(0);// Fermeture de l'application
            }
        });
        // Création d'un CardLayout pour gérer l'affichage des différents panneaux
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout); // Panneau contenant le CardLayout
        Border paddingCards = BorderFactory.createEmptyBorder(0, 67, 0, 0);  // Ajout d'un padding autour du panneau des cartes
        cardPanel.setBorder(paddingCards);

        // Panneau pour contenir les boutons de navigation
        JPanel buttonPanel = new JPanel();
        
        // Boutons pour naviguer entre les panneaux
        JButton goToEtudiantButton = new JButton("Etudiant");
        goToEtudiantButton.addActionListener(e -> cardLayout.show(cardPanel, "etudiantPanel"));

        JButton inscriEtudBtn = new JButton("inscription Etudiant");
        inscriEtudBtn.addActionListener(e -> cardLayout.show(cardPanel, "inscriEtudPanel"));

        JButton goToFormateurButton = new JButton("Formateur");
        goToFormateurButton.addActionListener(e -> cardLayout.show(cardPanel, "formateurPanel"));

        JButton goToFormationButton = new JButton("Formation");
        goToFormationButton.addActionListener(e -> cardLayout.show(cardPanel, "formationPanel"));
        
        // Ajout des boutons dans le panneau des boutons
        buttonPanel.add(goToEtudiantButton);
        buttonPanel.add(inscriEtudBtn);
        buttonPanel.add(goToFormateurButton);
        buttonPanel.add(goToFormationButton);
        
        // Ajout d'un padding en bas du panneau des boutons
        Border padding = BorderFactory.createEmptyBorder(0, 0, 200, 0); // Padding en haut, à gauche, en bas et à droite
        buttonPanel.setBorder(padding);

        // Création des panneaux pour le CardLayout
        JPanel etudiantPanel = new JPanel();
        etudiantPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Utilisation de FlowLayout pour aligner les composants

        // Ajout des champs pour saisir le nom, l'email et le mot de passe de l'étudiant
        etudiantPanel.add(new JLabel("Name:"));
        JTextField name = new JTextField(15); // Champ de texte pour le nom
        name.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        etudiantPanel.add(name);

        etudiantPanel.add(new JLabel("Email:"));
        JTextField email = new JTextField(15); // Champ de texte pour l'email
        email.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        etudiantPanel.add(email);

        etudiantPanel.add(new JLabel("mot de passe:"));
        JTextField pwd = new JTextField(15); // Champ de texte pour le mot de passe
        pwd.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        etudiantPanel.add(pwd);

        // Bouton de soumission pour l'étudiant
        JButton submitButton = new JButton("Submit");
        etudiantPanel.add(submitButton);
        submitButton.addActionListener(e -> {
            // Création d'un objet Etudiant avec les informations saisies
            Etudiant etud = new Etudiant(name.getText(), email.getText(), pwd.getText());
        });

        // Panneau pour gérer les informations d'un formateur
        JPanel formateurPanel = new JPanel();
        formateurPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout pour aligner les composants

        // Ajout des champs pour saisir le nom, l'email et le mot de passe du formateur
        formateurPanel.add(new JLabel("Name:"));
        JTextField nameF = new JTextField(15); // Champ de texte pour le nom
        nameF.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formateurPanel.add(nameF);

        formateurPanel.add(new JLabel("Email:"));
        JTextField emailF = new JTextField(15); // Champ de texte pour l'email'
        emailF.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formateurPanel.add(emailF);

        formateurPanel.add(new JLabel("mot de passe:"));
        JTextField pwdF = new JTextField(15); // Champ de texte pour le mot de passe
        pwdF.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formateurPanel.add(pwdF);

        // Bouton de soumission pour le formateur
        JButton submitButtonF = new JButton("Submit");
        formateurPanel.add(submitButtonF);
        submitButtonF.addActionListener(e -> {
            // Création d'un objet Formateur avec les informations saisies
            Formateur formateur = new Formateur(nameF.getText(), emailF.getText(), pwdF.getText());
        });

        // Panneau pour gérer les informations d'une formation
        JPanel formationPanel = new JPanel();
        formationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout pour aligner les composants

        // Ajout des champs pour saisir le titre, la description et le prix de la formation
        formationPanel.add(new JLabel("titre:"));
        JTextField titreFormation = new JTextField(15);  // Champ de texte pour le titre de la formation
        titreFormation.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formationPanel.add(titreFormation);

        formationPanel.add(new JLabel("description:"));
        JTextField descriptionFormation = new JTextField(15);  // Champ de texte pour la description de la formation
        descriptionFormation.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formationPanel.add(descriptionFormation);

        formationPanel.add(new JLabel("email formateur :"));
        JTextField emailFormateurFormation = new JTextField(15);  // Champ de texte pour l'email du formateur
        emailFormateurFormation.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formationPanel.add(emailFormateurFormation);

        formationPanel.add(new JLabel("prix :"));
        JTextField prix = new JTextField(15);  // Champ de texte pour le prix de la formation
        prix.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        formationPanel.add(prix);

        // Bouton de soumission pour la formation
        JButton submitButtonFormation = new JButton("Submit");
        formationPanel.add(submitButtonFormation);
        submitButtonFormation.addActionListener(e -> {
            try {
                 // Requête pour vérifier si un formateur avec l'email spécifié existe dans la base de données
                ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailFormateurFormation.getText()+"' and type = 'formateur'");
                if(!res.next()){
                    System.out.println("in");
                    System.out.println("formateur non trouver");
                }else{
                    // Création d'un objet Formateur si l'email est trouvé
                    Formateur f = new Formateur(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                    // Création d'un objet Formation avec les informations saisies
                    Formation formation = new Formation(titreFormation.getText(), descriptionFormation.getText(), f, Double.parseDouble(prix.getText()));
                }
            } catch (SQLException ex) {
                System.out.println("erreur de communication avec la BD");
            }
        });


        // Panneau pour gérer l'inscription d'un étudiant à une formation
        JPanel inscriEtudPanel = new JPanel();
        inscriEtudPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout pour aligner les composants

        // Ajout des champs pour saisir les informations nécessaires à l'inscription d'un étudiant à une formation
        inscriEtudPanel.add(new JLabel("titre formation:"));
        JTextField titreFormationIn = new JTextField(15);  // Champ de texte pour le titre de la formation
        titreFormationIn.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        inscriEtudPanel.add(titreFormationIn);

        inscriEtudPanel.add(new JLabel("description:"));
        JTextField descriptionFormationIn = new JTextField(15); // Champ de texte pour la description de la formation
        descriptionFormationIn.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        inscriEtudPanel.add(descriptionFormationIn);

        inscriEtudPanel.add(new JLabel("email formateur :"));
        JTextField emailFormateurFormatioIn = new JTextField(15); // Champ de texte pour l'email du formateur
        emailFormateurFormatioIn.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        inscriEtudPanel.add(emailFormateurFormatioIn);

        inscriEtudPanel.add(new JLabel("prix :"));
        JTextField prixIn = new JTextField(15); // Champ de texte pour le prix de la formation
        prixIn.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        inscriEtudPanel.add(prixIn);

        inscriEtudPanel.add(new JLabel("email etudiant :"));
        JTextField emailEtudIn = new JTextField(15); // Champ de texte pour l'email de l'étudiant
        emailEtudIn.setPreferredSize(new Dimension(200, 30)); // Taille préférée du champ
        inscriEtudPanel.add(emailEtudIn);


        // Bouton de soumission pour l'inscription
        JButton submitIn = new JButton("Submit");
        inscriEtudPanel.add(submitIn);
        submitIn.addActionListener(e -> {
            try {
                // Vérification si l'étudiant existe dans la base de données
                ResultSet res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailEtudIn.getText()+"' and type = 'etudiant'");
                if(!res.next()){
                    System.out.println("etudiant non trouver");
                }else{
                    // Création d'un objet Etudiant
                    Etudiant etudiantIn = new Etudiant(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                    
                    // Vérification si le formateur existe dans la base de données
                    res = ConnectionBD.st.executeQuery("select * from utilisateur where email = '"+emailFormateurFormatioIn.getText()+"' and type = 'formateur'");
                    if(!res.next()){
                        System.out.println("formateur n'existe pas");
                    }else{
                        // Création d'un objet Formateur
                        Formateur formateurIn = new Formateur(res.getString("nom"), res.getString("email"), res.getString("mot_de_passe"));
                        // Création d'une formation et inscription de l'étudiant
                        Formation formation = new Formation(titreFormationIn.getText(), descriptionFormationIn.getText(), formateurIn, Double.parseDouble(prixIn.getText()));
                        etudiantIn.sinscrireFormation(formation);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("erreur de communication avec la BD");
            }
        });



        // Ajout des panneaux au panneau de carte avec des noms pour le CardLayout
        cardPanel.add(etudiantPanel, "etudiantPanel");
        cardPanel.add(formateurPanel, "formateurPanel");
        cardPanel.add(formationPanel, "formationPanel");
        cardPanel.add(inscriEtudPanel, "inscriEtudPanel");

        // Définir la mise en page de la fenêtre principale et ajouter les panneaux
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);  // Ajout du panneau des boutons en haut
        frame.add(cardPanel, BorderLayout.CENTER);   // Ajout du panneau de cartes au centre

        frame.setVisible(true);


    }
}
