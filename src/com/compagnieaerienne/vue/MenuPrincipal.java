package com.compagnieaerienne.vue;

import javax.swing.*;
import java.awt.*;

/**
 * Menu principal de l'application
 * Point d'entrée de l'interface graphique
 */
public class MenuPrincipal extends JFrame {
    
    public MenuPrincipal() {
        // Configuration de la fenêtre
        setTitle("Gestion Compagnie Aérienne");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Création du panneau principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 248, 255));
        
        // En-tête
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(0, 130, 180));
        panelHeader.setPreferredSize(new Dimension(600, 60));
        
        JLabel lblTitre = new JLabel(" GESTION COMPAGNIE AÉRIENNE");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(Color.WHITE);
        panelHeader.add(lblTitre);
        
        // Panneau central avec les boutons
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new GridBagLayout());
        panelCentre.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Bouton Gestion Passagers
        JButton btnPassagers = new JButton(" Gestion des Passagers");
        btnPassagers.setFont(new Font("Arial", Font.BOLD, 16));
        btnPassagers.setPreferredSize(new Dimension(300, 60));
        btnPassagers.setBackground(new Color(200, 149, 237));
        btnPassagers.setForeground(Color.BLACK);
        btnPassagers.setFocusPainted(false);
        btnPassagers.addActionListener(e -> ouvrirGestionPassagers());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentre.add(btnPassagers, gbc);
        
        // Bouton Gestion Billets
        JButton btnBillets = new JButton(" Gestion des Billets");
        btnBillets.setFont(new Font("Arial", Font.BOLD, 16));
        btnBillets.setPreferredSize(new Dimension(300, 60));
        btnBillets.setBackground(new Color(72, 209, 204));
        btnBillets.setForeground(Color.BLACK);
        btnBillets.setFocusPainted(false);
        btnBillets.addActionListener(e -> ouvrirGestionBillets());
        
        gbc.gridy = 1;
        panelCentre.add(btnBillets, gbc);
        
        // Bouton Quitter
        JButton btnQuitter = new JButton(" Quitter");
        btnQuitter.setFont(new Font("Arial", Font.BOLD, 16));
        btnQuitter.setPreferredSize(new Dimension(300, 60));
        btnQuitter.setBackground(new Color(220, 20, 60));
        btnQuitter.setForeground(Color.BLACK);
        btnQuitter.setFocusPainted(false);
        btnQuitter.addActionListener(e -> quitter());
        
        gbc.gridy = 2;
        panelCentre.add(btnQuitter, gbc);
        
        // Pied de page
        JPanel panelFooter = new JPanel();
        panelFooter.setBackground(new Color(70, 130, 180));
        panelFooter.setPreferredSize(new Dimension(600, 40));
        
        JLabel lblFooter = new JLabel("© 2025 - Projet POO-Java");
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFooter.setForeground(Color.WHITE);
        panelFooter.add(lblFooter);
        
        // Ajout des panneaux
        panelPrincipal.add(panelHeader, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelFooter, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    /**
     * Ouvrir la fenêtre de gestion des passagers
     */
    private void ouvrirGestionPassagers() {
        InterfacePassager interfacePassager = new InterfacePassager();
        interfacePassager.setVisible(true);
    }
    
    /**
     * Ouvrir la fenêtre de gestion des billets
     */

    private void ouvrirGestionBillets() {
        InterfaceBillet interfaceBillet = new InterfaceBillet();
        interfaceBillet.setVisible(true);
    }
    
    /**
     * Quitter l'application
     */
    private void quitter() {
        int choix = JOptionPane.showConfirmDialog(
            this,
            "Voulez-vous vraiment quitter l'application ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (choix == JOptionPane.YES_OPTION) {
            System.out.println("Fermeture de l'application...");
            System.exit(0);
        }
    }
    
    /**
     * Point d'entrée de l'application
     */
    public static void main(String[] args) {
        // Utiliser le Look and Feel du système
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Lancer l'interface dans le thread Swing
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}