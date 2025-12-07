package com.compagnieaerienne.vue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.compagnieaerienne.modele.Passager;
import com.compagnieaerienne.enumeration.*;
import com.compagnieaerienne.dao.ConnexionBD;

/**
 * Interface graphique pour la gestion des passagers
 */
public class InterfacePassager extends JFrame {
    
    // Composants du formulaire
    private JTextField txtNom, txtPrenom, txtDateNaissance, txtPasseport, txtEmail, txtTelephone;
    private JComboBox<Nationalite> cmbNationalite;
    private JComboBox<TypePassager> cmbTypePassager;
    private JComboBox<Genre> cmbGenre;
    private JButton btnAjouter, btnModifier, btnSupprimer, btnRechercher, btnEffacer, btnActualiser;
    private JTable tablePassagers;
    private DefaultTableModel modelTable;
    private JTextField txtRechercheId;
    
    private Passager passagerSelectionne = null;
    
    public InterfacePassager() {
        // Configuration de la fenêtre
        setTitle("Gestion des Passagers");
        setSize(1000, 700);
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
        chargerPassagers();
    }
    
    /**
     * Créer le header
     */
    private JPanel creerHeader() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180));
        panel.setPreferredSize(new Dimension(1000, 60));
        
        JLabel lblTitre = new JLabel(" GESTION DES PASSAGERS");
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
        panel.setBorder(BorderFactory.createTitledBorder("Informations du Passager"));
        panel.setPreferredSize(new Dimension(400, 600));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int row = 0;
        
        // Nom
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 1;
        txtNom = new JTextField(20);
        panel.add(txtNom, gbc);
        row++;
        
        // Prénom
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 1;
        txtPrenom = new JTextField(20);
        panel.add(txtPrenom, gbc);
        row++;
        
        // Date de naissance
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Date naissance (AAAA-MM-JJ) :"), gbc);
        gbc.gridx = 1;
        txtDateNaissance = new JTextField(20);
        txtDateNaissance.setToolTipText("Format: 1990-05-15");
        panel.add(txtDateNaissance, gbc);
        row++;
        
        // Passeport
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("N° Passeport :"), gbc);
        gbc.gridx = 1;
        txtPasseport = new JTextField(20);
        panel.add(txtPasseport, gbc);
        row++;
        
        // Nationalité
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Nationalité :"), gbc);
        gbc.gridx = 1;
        cmbNationalite = new JComboBox<>(Nationalite.values());
        panel.add(cmbNationalite, gbc);
        row++;
        
        // Type passager
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Type :"), gbc);
        gbc.gridx = 1;
        cmbTypePassager = new JComboBox<>(TypePassager.values());
        panel.add(cmbTypePassager, gbc);
        row++;
        
        // Genre
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Genre :"), gbc);
        gbc.gridx = 1;
        cmbGenre = new JComboBox<>(Genre.values());
        panel.add(cmbGenre, gbc);
        row++;
        
        // Email
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Email :"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        panel.add(txtEmail, gbc);
        row++;
        
        // Téléphone
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Téléphone :"), gbc);
        gbc.gridx = 1;
        txtTelephone = new JTextField(20);
        panel.add(txtTelephone, gbc);
        row++;
        
        // Boutons
        JPanel panelBoutons = new JPanel(new GridLayout(4, 2, 5, 5));
        
        btnAjouter = new JButton("➕ Ajouter");
        btnAjouter.setBackground(new Color(34, 139, 34));
        btnAjouter.setForeground(Color.BLUE);
        btnAjouter.addActionListener(e -> ajouterPassager());
        
        btnModifier = new JButton("✏️ Modifier");
        btnModifier.setBackground(new Color(255, 165, 0));
        btnModifier.setForeground(Color.BLUE);
        btnModifier.addActionListener(e -> modifierPassager());
        
        btnSupprimer = new JButton("🗑️ Supprimer");
        btnSupprimer.setBackground(new Color(220, 20, 60));
        btnSupprimer.setForeground(Color.BLUE);
        btnSupprimer.addActionListener(e -> supprimerPassager());
        
        btnRechercher = new JButton("🔍 Rechercher");
        btnRechercher.setBackground(new Color(70, 130, 180));
        btnRechercher.setForeground(Color.BLUE);
        btnRechercher.addActionListener(e -> rechercherPassager());
        
        btnActualiser = new JButton("🔄 Actualiser");
        btnActualiser.setBackground(new Color(30, 144, 255));
        btnActualiser.setForeground(Color.BLUE);
        btnActualiser.addActionListener(e -> chargerPassagers());
        
        btnEffacer = new JButton("🗑️ Effacer");
        btnEffacer.setBackground(new Color(128, 128, 128));
        btnEffacer.setForeground(Color.BLUE);
        btnEffacer.addActionListener(e -> effacerFormulaire());
        
        JButton btnFermer = new JButton("🚪 Fermer");
        btnFermer.setBackground(new Color(105, 105, 105));
        btnFermer.setForeground(Color.BLUE);
        btnFermer.addActionListener(e -> dispose());
        
        JButton btnTest = new JButton("🧪 Tester");
        btnTest.setBackground(new Color(138, 43, 226));
        btnTest.setForeground(Color.BLUE);
        btnTest.addActionListener(e -> testerConnexion());
        
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);
        panelBoutons.add(btnRechercher);
        panelBoutons.add(btnActualiser);
        panelBoutons.add(btnEffacer);
        panelBoutons.add(btnFermer);
        panelBoutons.add(btnTest);
        
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panel.add(panelBoutons, gbc);
        
        return panel;
    }
    
    /**
     * Créer la table
     */
    private JPanel creerTable() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Liste des Passagers"));
        
        // Modèle de table
        String[] colonnes = {"ID", "Nom", "Prénom", "Date Naissance", "Passeport", "Nationalité", "Type", "Genre", "Email", "Téléphone"};
        modelTable = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablePassagers = new JTable(modelTable);
        tablePassagers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePassagers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                afficherPassagerSelectionne();
            }
        });
        
        // Personnaliser l'affichage de la date
        tablePassagers.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof LocalDate) {
                    setText(((LocalDate) value).format(formatter));
                } else if (value instanceof java.sql.Date) {
                    setText(((java.sql.Date) value).toLocalDate().format(formatter));
                } else if (value instanceof String) {
                    setText((String) value);
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablePassagers);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de recherche
        JPanel panelRecherche = new JPanel();
        panelRecherche.add(new JLabel("Rechercher par ID:"));
        txtRechercheId = new JTextField(10);
        panelRecherche.add(txtRechercheId);
        JButton btnRechercherTable = new JButton("🔍 Rechercher");
        btnRechercherTable.addActionListener(e -> rechercherParId());
        panelRecherche.add(btnRechercherTable);
        
        JButton btnAfficherTous = new JButton("📋 Afficher tous");
        btnAfficherTous.addActionListener(e -> chargerPassagers());
        panelRecherche.add(btnAfficherTous);
        
        panel.add(panelRecherche, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Charger tous les passagers dans la table
     */
    private void chargerPassagers() {
        // Effacer les données existantes
        modelTable.setRowCount(0);
        
        try {
            // Récupérer la liste des passagers
            java.util.List<Passager> passagers = Passager.listerTous();
            
            if (passagers != null && !passagers.isEmpty()) {
                for (Passager p : passagers) {
                    Object[] rowData = {
                        p.getIdPassager(),
                        p.getNom(),
                        p.getPrenom(),
                        p.getDateNaissance(),
                        p.getNumPasseport(),
                        p.getNationalite() != null ? p.getNationalite().getLibelle() : "",
                        p.getTypePassager() != null ? p.getTypePassager().getLibelle() : "",
                        p.getGenre() != null ? p.getGenre().getLibelle() : "",
                        p.getEmail(),
                        p.getTelephone()
                    };
                    modelTable.addRow(rowData);
                }
                
                System.out.println("✓ " + passagers.size() + " passagers chargés");
            } else {
                System.out.println("ℹ️ Aucun passager trouvé");
            }
        } catch (Exception e) {
            System.err.println("✗ Erreur lors du chargement: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ajouter un passager
     */
    private void ajouterPassager() {
        try {
            // Validation des champs obligatoires
            if (txtNom.getText().trim().isEmpty() || txtPrenom.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom et prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification du format de date
            LocalDate dateNaissance;
            try {
                dateNaissance = LocalDate.parse(txtDateNaissance.getText().trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Format de date invalide (AAAA-MM-JJ)", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Passager p = new Passager(
                txtNom.getText().trim(),
                txtPrenom.getText().trim(),
                dateNaissance,
                txtPasseport.getText().trim(),
                (Nationalite) cmbNationalite.getSelectedItem(),
                (TypePassager) cmbTypePassager.getSelectedItem(),
                (Genre) cmbGenre.getSelectedItem(),
                txtEmail.getText().trim(),
                txtTelephone.getText().trim()
            );
            
            if (p.ajouter()) {
                JOptionPane.showMessageDialog(this, "✓ Passager ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerPassagers(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    /**
     * Modifier un passager
     */
    private void modifierPassager() {
        if (passagerSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un passager", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Validation des champs obligatoires
            if (txtNom.getText().trim().isEmpty() || txtPrenom.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom et prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Vérification du format de date
            LocalDate dateNaissance;
            try {
                dateNaissance = LocalDate.parse(txtDateNaissance.getText().trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Format de date invalide (AAAA-MM-JJ)", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            passagerSelectionne.setNom(txtNom.getText().trim());
            passagerSelectionne.setPrenom(txtPrenom.getText().trim());
            passagerSelectionne.setDateNaissance(dateNaissance);
            passagerSelectionne.setNumPasseport(txtPasseport.getText().trim());
            passagerSelectionne.setNationalite((Nationalite) cmbNationalite.getSelectedItem());
            passagerSelectionne.setTypePassager((TypePassager) cmbTypePassager.getSelectedItem());
            passagerSelectionne.setGenre((Genre) cmbGenre.getSelectedItem());
            passagerSelectionne.setEmail(txtEmail.getText().trim());
            passagerSelectionne.setTelephone(txtTelephone.getText().trim());
            
            if (passagerSelectionne.modifier()) {
                JOptionPane.showMessageDialog(this, "✓ Passager modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerPassagers(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    /**
     * Supprimer un passager
     */
    private void supprimerPassager() {
        if (passagerSelectionne == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un passager", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment supprimer ce passager ?\nNom: " + passagerSelectionne.getNom() + " " + passagerSelectionne.getPrenom(), 
            "Confirmation de suppression", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (choix == JOptionPane.YES_OPTION) {
            if (Passager.supprimer(passagerSelectionne.getIdPassager())) {
                JOptionPane.showMessageDialog(this, "✓ Passager supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                effacerFormulaire();
                chargerPassagers(); // Actualiser la table
            } else {
                JOptionPane.showMessageDialog(this, "✗ Échec de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Rechercher un passager
     */
    private void rechercherPassager() {
        String passeport = JOptionPane.showInputDialog(this, "Entrez le numéro de passeport:");
        if (passeport != null && !passeport.trim().isEmpty()) {
            Passager p = Passager.chercherParPasseport(passeport.trim());
            if (p != null) {
                afficherPassager(p);
                JOptionPane.showMessageDialog(this, "✓ Passager trouvé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun passager trouvé", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Rechercher par ID depuis la table
     */
    private void rechercherParId() {
        try {
            String idText = txtRechercheId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un ID", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            Passager p = Passager.chercher(id);
            if (p != null) {
                afficherPassager(p);
                // Trouver et sélectionner la ligne dans la table
                for (int i = 0; i < modelTable.getRowCount(); i++) {
                    if (modelTable.getValueAt(i, 0).equals(id)) {
                        tablePassagers.setRowSelectionInterval(i, i);
                        tablePassagers.scrollRectToVisible(tablePassagers.getCellRect(i, 0, true));
                        break;
                    }
                }
                JOptionPane.showMessageDialog(this, "✓ Passager trouvé !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "✗ Aucun passager trouvé avec ID: " + id, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide. Veuillez entrer un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Afficher un passager dans le formulaire
     */
    private void afficherPassager(Passager p) {
        passagerSelectionne = p;
        txtNom.setText(p.getNom());
        txtPrenom.setText(p.getPrenom());
        txtDateNaissance.setText(p.getDateNaissance().toString());
        txtPasseport.setText(p.getNumPasseport());
        cmbNationalite.setSelectedItem(p.getNationalite());
        cmbTypePassager.setSelectedItem(p.getTypePassager());
        cmbGenre.setSelectedItem(p.getGenre());
        txtEmail.setText(p.getEmail());
        txtTelephone.setText(p.getTelephone());
    }
    
    /**
     * Afficher le passager sélectionné dans la table
     */
    private void afficherPassagerSelectionne() {
        int selectedRow = tablePassagers.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Object idObj = modelTable.getValueAt(selectedRow, 0);
                if (idObj instanceof Integer) {
                    int id = (Integer) idObj;
                    Passager p = Passager.chercher(id);
                    if (p != null) {
                        afficherPassager(p);
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de l'affichage du passager: " + e.getMessage());
            }
        }
    }
    
    /**
     * Effacer le formulaire
     */
    private void effacerFormulaire() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtDateNaissance.setText("");
        txtPasseport.setText("");
        txtEmail.setText("");
        txtTelephone.setText("");
        cmbNationalite.setSelectedIndex(0);
        cmbTypePassager.setSelectedIndex(0);
        cmbGenre.setSelectedIndex(0);
        passagerSelectionne = null;
        txtRechercheId.setText("");
        tablePassagers.clearSelection();
    }
    
    /**
     * Tester la connexion à la base de données
     */
    private void testerConnexion() {
        try {
            java.sql.Connection conn = ConnexionBD.getConnection();
            if (conn != null && !conn.isClosed()) {
                JOptionPane.showMessageDialog(this, 
                    "✅ Connexion à la base de données réussie !\n" +
                    "URL: " + conn.getMetaData().getURL() + "\n" +
                    "Base: " + conn.getMetaData().getDatabaseProductName(),
                    "Test de connexion",
                    JOptionPane.INFORMATION_MESSAGE);
                conn.close();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Échec de la connexion",
                    "Test de connexion",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Erreur de connexion:\n" + e.getMessage(),
                "Test de connexion",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
            InterfacePassager interfacePassager = new InterfacePassager();
            interfacePassager.setVisible(true);
        });
    }
}