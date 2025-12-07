package com.compagnieaerienne.vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.compagnieaerienne.modele.Billet;
import com.compagnieaerienne.enumeration.*;

/**
 * Interface graphique pour la gestion des billets
 */
public class InterfaceBillet extends JFrame {
    
    // Composants du formulaire
    private JTextField txtNumBillet, txtIdPassager, txtTarif, txtDateEmission, txtNumVol, txtNumSiege;
    private JComboBox<ClasseBillet> cmbClasse;
    private JComboBox<StatutBillet> cmbStatut;
    private JButton btnAjouter, btnModifier, btnSupprimer, btnRechercher, btnEffacer, btnAnnuler, btnRembourser, btnActualiser, btnTest;
    private JTable tableBillets;
    private DefaultTableModel modelTable;
    private JTextField txtRechercheNum, txtRecherchePassager, txtRechercheVol;
    
    private Billet billetSelectionne = null;
    
    public InterfaceBillet() {
        // Configuration de la fenêtre
        setTitle("Gestion des Billets");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header
        JPanel panelHeader = creerHeader();
        panelPrincipal.add(panelHeader, BorderLayout.NORTH);
        
        // Formulaire
        JPanel panelFormulaire = creerFormulaire();
        panelPrincipal.add(panelFormulaire, BorderLayout.WEST);
        
        // Table
        JPanel panelTable = creerTable();
        panelPrincipal.add(panelTable, BorderLayout.CENTER);
        
        add(panelPrincipal);
        
        // Charger les données au démarrage
        chargerBillets();
    }
    
    /**
     * Créer le header
     */
    private JPanel creerHeader() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(72, 209, 204));
        panel.setPreferredSize(new Dimension(1200, 60));
        
        JLabel lblTitre = new JLabel(" GESTION DES BILLETS");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setForeground(Color.WHITE);
        panel.add(lblTitre);
        
        return panel;
    }
    
    /**
     * Créer le formulaire
     */
    private JPanel creerFormulaire() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Informations du Billet"));
        panel.setPreferredSize(new Dimension(400, 650));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int row = 0;
        
        // Numéro billet
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("N° Billet :"), gbc);
        gbc.gridx = 1;
        txtNumBillet = new JTextField(20);
        txtNumBillet.setToolTipText("Laisser vide pour générer automatiquement");
        panel.add(txtNumBillet, gbc);
        row++;
        
        // ID Passager
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("ID Passager :"), gbc);
        gbc.gridx = 1;
        txtIdPassager = new JTextField(20);
        txtIdPassager.setToolTipText("ID du passager (doit exister dans la BD)");
        panel.add(txtIdPassager, gbc);
        row++;
        
        // Classe
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Classe :"), gbc);
        gbc.gridx = 1;
        cmbClasse = new JComboBox<>(ClasseBillet.values());
        panel.add(cmbClasse, gbc);
        row++;
        
        // Tarif
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Tarif (MAD) :"), gbc);
        gbc.gridx = 1;
        txtTarif = new JTextField(20);
        txtTarif.setToolTipText("Exemple: 2500.00");
        panel.add(txtTarif, gbc);
        row++;
        
        // Statut
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Statut :"), gbc);
        gbc.gridx = 1;
        cmbStatut = new JComboBox<>(StatutBillet.values());
        panel.add(cmbStatut, gbc);
        row++;
        
        // Date émission
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Date émission (AAAA-MM-JJ) :"), gbc);
        gbc.gridx = 1;
        txtDateEmission = new JTextField(20);
        txtDateEmission.setText(LocalDate.now().toString());
        txtDateEmission.setToolTipText("Format: 2025-12-07");
        panel.add(txtDateEmission, gbc);
        row++;
        
        // Numéro vol
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("N° Vol :"), gbc);
        gbc.gridx = 1;
        txtNumVol = new JTextField(20);
        txtNumVol.setToolTipText("Exemple: VOL123");
        panel.add(txtNumVol, gbc);
        row++;
        
        // Numéro siège
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("N° Siège :"), gbc);
        gbc.gridx = 1;
        txtNumSiege = new JTextField(20);
        txtNumSiege.setToolTipText("Exemple: 12A");
        panel.add(txtNumSiege, gbc);
        row++;
        
        // Boutons
        JPanel panelBoutons = new JPanel(new GridLayout(5, 2, 5, 5));
        
        btnAjouter = new JButton("➕ Ajouter");
        btnAjouter.setBackground(new Color(34, 139, 34));
        btnAjouter.setForeground(Color.GREEN);
        btnAjouter.addActionListener(e -> ajouterBillet());
        
        btnModifier = new JButton("✏️ Modifier");
        btnModifier.setBackground(new Color(255, 165, 0));
        btnModifier.setForeground(Color.GREEN);
        btnModifier.addActionListener(e -> modifierBillet());
        
        btnSupprimer = new JButton("🗑️ Supprimer");
        btnSupprimer.setBackground(new Color(220, 20, 60));
        btnSupprimer.setForeground(Color.GREEN);
        btnSupprimer.addActionListener(e -> supprimerBillet());
        
        btnRechercher = new JButton("🔍 Rechercher");
        btnRechercher.setBackground(new Color(70, 130, 180));
        btnRechercher.setForeground(Color.GREEN);
        btnRechercher.addActionListener(e -> rechercherBillet());
        
        btnAnnuler = new JButton("❌ Annuler");
        btnAnnuler.setBackground(new Color(255, 99, 71));
        btnAnnuler.setForeground(Color.GREEN);
        btnAnnuler.addActionListener(e -> annulerBillet());
        
        btnRembourser = new JButton("💰 Rembourser");
        btnRembourser.setBackground(new Color(46, 139, 87));
        btnRembourser.setForeground(Color.GREEN);
        btnRembourser.addActionListener(e -> rembourserBillet());
        
        btnActualiser = new JButton("🔄 Actualiser");
        btnActualiser.setBackground(new Color(30, 144, 255));
        btnActualiser.setForeground(Color.GREEN);
        btnActualiser.addActionListener(e -> chargerBillets());
        
        btnEffacer = new JButton("🗑️ Effacer");
        btnEffacer.setBackground(new Color(128, 128, 128));
        btnEffacer.setForeground(Color.GREEN);
        btnEffacer.addActionListener(e -> effacerFormulaire());
        
        btnTest = new JButton("🧪 Tester");
        btnTest.setBackground(new Color(138, 43, 226));
        btnTest.setForeground(Color.GREEN);
        btnTest.addActionListener(e -> testerPassager());
        
        JButton btnFermer = new JButton("🚪 Fermer");
        btnFermer.setBackground(new Color(105, 105, 105));
        btnFermer.setForeground(Color.GREEN);
        btnFermer.addActionListener(e -> dispose());
        
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);
        panelBoutons.add(btnRechercher);
        panelBoutons.add(btnAnnuler);
        panelBoutons.add(btnRembourser);
        panelBoutons.add(btnActualiser);
        panelBoutons.add(btnEffacer);
        panelBoutons.add(btnTest);
        panelBoutons.add(btnFermer);
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panel.add(panelBoutons, gbc);
        
        return panel;
    }
    
    /**
     * Créer la table
     */
    private JPanel creerTable() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Liste des Billets"));
        
        // Modèle de table
        String[] colonnes = {"N° Billet", "ID Passager", "Classe", "Tarif", "Statut", "Date Émission", "N° Vol", "N° Siège"};
        modelTable = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableBillets = new JTable(modelTable);
        tableBillets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableBillets.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                afficherBilletSelectionne();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableBillets);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de recherche multiple
        JPanel panelRecherche = new JPanel(new GridLayout(3, 1, 5, 5));
        panelRecherche.setBorder(BorderFactory.createTitledBorder("Recherches"));
        
        // Recherche par numéro
        JPanel panelRechNum = new JPanel();
        panelRechNum.add(new JLabel("Par N° Billet:"));
        txtRechercheNum = new JTextField(10);
        panelRechNum.add(txtRechercheNum);
        JButton btnRechNum = new JButton("🔍");
        btnRechNum.addActionListener(e -> rechercherParNumero());
        panelRechNum.add(btnRechNum);
        
        // Recherche par passager
        JPanel panelRechPass = new JPanel();
        panelRechPass.add(new JLabel("Par ID Passager:"));
        txtRecherchePassager = new JTextField(10);
        panelRechPass.add(txtRecherchePassager);
        JButton btnRechPass = new JButton("🔍");
        btnRechPass.addActionListener(e -> rechercherParPassager());
        panelRechPass.add(btnRechPass);
        
        // Recherche par vol
        JPanel panelRechVol = new JPanel();
        panelRechVol.add(new JLabel("Par N° Vol:"));
        txtRechercheVol = new JTextField(10);
        panelRechVol.add(txtRechercheVol);
        JButton btnRechVol = new JButton("🔍");
        btnRechVol.addActionListener(e -> rechercherParVol());
        panelRechVol.add(btnRechVol);
        
        panelRecherche.add(panelRechNum);
        panelRecherche.add(panelRechPass);
        panelRecherche.add(panelRechVol);
        
        panel.add(panelRecherche, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Charger tous les billets dans la table
     */
    private void chargerBillets() {
        // Effacer les données existantes
        modelTable.setRowCount(0);
        
        try {
            // Récupérer la liste des billets
            List<Billet> billets = Billet.listerTous();
            
            if (billets != null && !billets.isEmpty()) {
                for (Billet b : billets) {
                    Object[] rowData = {
                        b.getNumBillet(),
                        b.getIdPassager(),
                        b.getClasse() != null ? b.getClasse().getLibelle() : "",
                        b.getTarif(),
                        b.getStatut() != null ? b.getStatut().getLibelle() : "",
                        b.getDateEmission(),
                        b.getNumVol(),
                        b.getNumSiege()
                    };
                    modelTable.addRow(rowData);
                }
                
                System.out.println("✓ " + billets.size() + " billets chargés");
            } else {
                System.out.println("ℹ️ Aucun billet trouvé");
            }
        } catch (Exception e) {
            System.err.println("✗ Erreur lors du chargement des billets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ajouter un billet
     */
    private void ajouterBillet() {
        try {
            // Validation des champs obligatoires
            if (txtIdPassager.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Passager est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérifier si le passager existe
            int idPassager = Integer.parseInt(txtIdPassager.getText().trim());
            if (!Billet.passagerExiste(idPassager)) {
                JOptionPane.showMessageDialog(this, "Le passager avec ID " + idPassager + " n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification du tarif
            BigDecimal tarif;
            try {
                tarif = new BigDecimal(txtTarif.getText().trim());
                if (tarif.compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this, "Le tarif doit être positif", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tarif invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification de la date
            LocalDate dateEmission;
            try {
                dateEmission = LocalDate.parse(txtDateEmission.getText().trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Format de date invalide (AAAA-MM-JJ)", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Si numBillet est vide, on le laissera générer automatiquement
            String numBillet = txtNumBillet.getText().trim().isEmpty() ? null : txtNumBillet.getText().trim();
            
            Billet b = new Billet(
                numBillet,
                idPassager,
                (ClasseBillet) cmbClasse.getSelectedItem(),
                tarif,
                (StatutBillet) cmbStatut.getSelectedItem(),
                dateEmission,
                txtNumVol.getText().trim(),
                txtNumSiege.getText().trim()
            );
            
            if (b.ajouter()) {
                JOptionPane.showMessageDialog(this, "✓ Billet ajouté avec succès !\nNuméro: " + b.getNumBillet(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerBillets(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Passager invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    /**
     * Modifier un billet
     */
    private void modifierBillet() {
        if (billetSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un billet", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Validation des champs obligatoires
            if (txtIdPassager.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Passager est obligatoire", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérifier si le passager existe
            int idPassager = Integer.parseInt(txtIdPassager.getText().trim());
            if (!Billet.passagerExiste(idPassager)) {
                JOptionPane.showMessageDialog(this, "Le passager avec ID " + idPassager + " n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification du tarif
            BigDecimal tarif;
            try {
                tarif = new BigDecimal(txtTarif.getText().trim());
                if (tarif.compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this, "Le tarif doit être positif", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tarif invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification de la date
            LocalDate dateEmission;
            try {
                dateEmission = LocalDate.parse(txtDateEmission.getText().trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Format de date invalide (AAAA-MM-JJ)", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            billetSelectionne.setIdPassager(idPassager);
            billetSelectionne.setClasse((ClasseBillet) cmbClasse.getSelectedItem());
            billetSelectionne.setTarif(tarif);
            billetSelectionne.setStatut((StatutBillet) cmbStatut.getSelectedItem());
            billetSelectionne.setDateEmission(dateEmission);
            billetSelectionne.setNumVol(txtNumVol.getText().trim());
            billetSelectionne.setNumSiege(txtNumSiege.getText().trim());
            
            if (billetSelectionne.modifier()) {
                JOptionPane.showMessageDialog(this, "✓ Billet modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerBillets(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Passager invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    /**
     * Supprimer un billet
     */
    private void supprimerBillet() {
        if (billetSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un billet", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment supprimer ce billet ?\nNuméro: " + billetSelectionne.getNumBillet(), 
            "Confirmation de suppression", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (choix == JOptionPane.YES_OPTION) {
            if (Billet.supprimer(billetSelectionne.getNumBillet())) {
                JOptionPane.showMessageDialog(this, "✓ Billet supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerBillets(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Annuler un billet
     */
    private void annulerBillet() {
        if (billetSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un billet", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous annuler ce billet ?\nNuméro: " + billetSelectionne.getNumBillet(), 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            if (billetSelectionne.annuler()) {
                JOptionPane.showMessageDialog(this, "✓ Billet annulé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                afficherBillet(billetSelectionne); // Rafraîchir l'affichage
                chargerBillets(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de l'annulation", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Rembourser un billet
     */
    private void rembourserBillet() {
        if (billetSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un billet", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous rembourser ce billet ?\nNuméro: " + billetSelectionne.getNumBillet(), 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            if (billetSelectionne.rembourser()) {
                JOptionPane.showMessageDialog(this, "✓ Billet remboursé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                afficherBillet(billetSelectionne); // Rafraîchir l'affichage
                chargerBillets(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec du remboursement", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Rechercher un billet
     */
    private void rechercherBillet() {
        String numBillet = JOptionPane.showInputDialog(this, "Entrez le numéro du billet:");
        if (numBillet != null && !numBillet.trim().isEmpty()) {
            Billet b = Billet.chercher(numBillet.trim());
            if (b != null) {
                afficherBillet(b);
                JOptionPane.showMessageDialog(this, "✓ Billet trouvé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun billet trouvé", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Rechercher par numéro depuis la table
     */
    private void rechercherParNumero() {
        String num = txtRechercheNum.getText().trim();
        if (!num.isEmpty()) {
            Billet b = Billet.chercher(num);
            if (b != null) {
                afficherBillet(b);
                JOptionPane.showMessageDialog(this, "✓ Billet trouvé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun billet trouvé", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de billet", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Rechercher par passager
     */
    private void rechercherParPassager() {
        try {
            String idText = txtRecherchePassager.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un ID passager", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int idPassager = Integer.parseInt(idText);
            List<Billet> billets = Billet.chercherParPassager(idPassager);
            
            if (!billets.isEmpty()) {
                // Afficher dans la table
                modelTable.setRowCount(0);
                for (Billet b : billets) {
                    Object[] row = {
                        b.getNumBillet(),
                        b.getIdPassager(),
                        b.getClasse() != null ? b.getClasse().getLibelle() : "",
                        b.getTarif(),
                        b.getStatut() != null ? b.getStatut().getLibelle() : "",
                        b.getDateEmission(),
                        b.getNumVol(),
                        b.getNumSiege()
                    };
                    modelTable.addRow(row);
                }
                JOptionPane.showMessageDialog(this, "✓ " + billets.size() + " billet(s) trouvé(s)", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun billet trouvé pour ce passager", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Rechercher par vol
     */
    private void rechercherParVol() {
        String numVol = txtRechercheVol.getText().trim();
        if (!numVol.isEmpty()) {
            List<Billet> billets = Billet.chercherParVol(numVol);
            
            if (!billets.isEmpty()) {
                // Afficher dans la table
                modelTable.setRowCount(0);
                for (Billet b : billets) {
                    Object[] row = {
                        b.getNumBillet(),
                        b.getIdPassager(),
                        b.getClasse() != null ? b.getClasse().getLibelle() : "",
                        b.getTarif(),
                        b.getStatut() != null ? b.getStatut().getLibelle() : "",
                        b.getDateEmission(),
                        b.getNumVol(),
                        b.getNumSiege()
                    };
                    modelTable.addRow(row);
                }
                JOptionPane.showMessageDialog(this, "✓ " + billets.size() + " billet(s) trouvé(s)", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun billet trouvé pour ce vol", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de vol", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Afficher un billet dans le formulaire
     */
    private void afficherBillet(Billet b) {
        billetSelectionne = b;
        txtNumBillet.setText(b.getNumBillet());
        txtIdPassager.setText(String.valueOf(b.getIdPassager()));
        cmbClasse.setSelectedItem(b.getClasse());
        txtTarif.setText(b.getTarif().toString());
        cmbStatut.setSelectedItem(b.getStatut());
        txtDateEmission.setText(b.getDateEmission().toString());
        txtNumVol.setText(b.getNumVol());
        txtNumSiege.setText(b.getNumSiege());
    }
    
    /**
     * Afficher le billet sélectionné dans la table
     */
    private void afficherBilletSelectionne() {
        int selectedRow = tableBillets.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                String numBillet = (String) modelTable.getValueAt(selectedRow, 0);
                Billet b = Billet.chercher(numBillet);
                if (b != null) {
                    afficherBillet(b);
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de l'affichage du billet: " + e.getMessage());
            }
        }
    }
    
    /**
     * Effacer le formulaire
     */
    private void effacerFormulaire() {
        txtNumBillet.setText("");
        txtIdPassager.setText("");
        txtTarif.setText("");
        txtDateEmission.setText(LocalDate.now().toString());
        txtNumVol.setText("");
        txtNumSiege.setText("");
        cmbClasse.setSelectedIndex(0);
        cmbStatut.setSelectedIndex(0);
        billetSelectionne = null;
        txtRechercheNum.setText("");
        txtRecherchePassager.setText("");
        txtRechercheVol.setText("");
        tableBillets.clearSelection();
    }
    
    /**
     * Tester si un passager existe
     */
    private void testerPassager() {
        try {
            String idText = txtIdPassager.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un ID passager", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int idPassager = Integer.parseInt(idText);
            if (Billet.passagerExiste(idPassager)) {
                JOptionPane.showMessageDialog(this, 
                    "✅ Le passager avec ID " + idPassager + " existe", 
                    "Test Passager",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Le passager avec ID " + idPassager + " n'existe pas", 
                    "Test Passager",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Méthode main pour tester l'interface
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
            InterfaceBillet interfaceBillet = new InterfaceBillet();
            interfaceBillet.setVisible(true);
        });
    }
}