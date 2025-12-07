package com.compagnieaerienne.modele;

import java.sql.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.compagnieaerienne.dao.ConnexionBD;
import com.compagnieaerienne.enumeration.*;

/**
 * Classe représentant un Billet
 * Gère les opérations CRUD sur la table Billet
 */
public class Billet {
    
    // ========== ATTRIBUTS ==========
    private String numBillet;
    private Integer idPassager;
    private ClasseBillet classe;
    private BigDecimal tarif;
    private StatutBillet statut;
    private LocalDate dateEmission;
    private String numVol;
    private String numSiege;
    
    // ========== CONSTRUCTEURS ==========
    
    /**
     * Constructeur par défaut
     */
    public Billet() {
    }
    
    /**
     * Constructeur avec tous les paramètres
     */
    public Billet(String numBillet, Integer idPassager, ClasseBillet classe, 
                 BigDecimal tarif, StatutBillet statut, LocalDate dateEmission,
                 String numVol, String numSiege) {
        this.numBillet = numBillet;
        this.idPassager = idPassager;
        this.classe = classe;
        this.tarif = tarif;
        this.statut = statut;
        this.dateEmission = dateEmission;
        this.numVol = numVol;
        this.numSiege = numSiege;
    }
    
    /**
     * Constructeur sans numBillet (sera généré par la BD)
     */
    public Billet(Integer idPassager, ClasseBillet classe, BigDecimal tarif,
                 StatutBillet statut, LocalDate dateEmission, String numVol, 
                 String numSiege) {
        this.idPassager = idPassager;
        this.classe = classe;
        this.tarif = tarif;
        this.statut = statut;
        this.dateEmission = dateEmission;
        this.numVol = numVol;
        this.numSiege = numSiege;
    }
    
    // ========== GETTERS ET SETTERS ==========
    
    public String getNumBillet() {
        return numBillet;
    }
    
    public void setNumBillet(String numBillet) {
        this.numBillet = numBillet;
    }
    
    public Integer getIdPassager() {
        return idPassager;
    }
    
    public void setIdPassager(Integer idPassager) {
        this.idPassager = idPassager;
    }
    
    public ClasseBillet getClasse() {
        return classe;
    }
    
    public void setClasse(ClasseBillet classe) {
        this.classe = classe;
    }
    
    public BigDecimal getTarif() {
        return tarif;
    }
    
    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
    
    public StatutBillet getStatut() {
        return statut;
    }
    
    public void setStatut(StatutBillet statut) {
        this.statut = statut;
    }
    
    public LocalDate getDateEmission() {
        return dateEmission;
    }
    
    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }
    
    public String getNumVol() {
        return numVol;
    }
    
    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }
    
    public String getNumSiege() {
        return numSiege;
    }
    
    public void setNumSiege(String numSiege) {
        this.numSiege = numSiege;
    }
    
    // ========== MÉTHODE CHERCHER ==========
    
    /**
     * Chercher un billet par son numéro
     * @param numBillet Le numéro du billet
     * @return Billet trouvé ou null
     */
    public static Billet chercher(String numBillet) {
        String sql = "SELECT * FROM Billet WHERE num_billet = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numBillet);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extraireBillet(rs);
            }
            
            System.out.println("⚠ Aucun billet trouvé avec le numéro : " + numBillet);
            return null;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la recherche du billet");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Chercher tous les billets d'un passager
     * @param idPassager L'ID du passager
     * @return Liste de billets (peut être vide)
     */
    public static List<Billet> chercherParPassager(int idPassager) {
        String sql = "SELECT * FROM Billet WHERE id_passager = ?";
        List<Billet> billets = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPassager);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                billets.add(extraireBillet(rs));
            }
            
            System.out.println("✓ " + billets.size() + " billet(s) trouvé(s) pour le passager " + idPassager);
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la recherche des billets");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return billets;
    }
    
    /**
     * Chercher tous les billets d'un vol
     * @param numVol Le numéro du vol
     * @return Liste de billets (peut être vide)
     */
    public static List<Billet> chercherParVol(String numVol) {
        String sql = "SELECT * FROM Billet WHERE num_vol = ?";
        List<Billet> billets = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numVol);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                billets.add(extraireBillet(rs));
            }
            
            System.out.println("✓ " + billets.size() + " billet(s) trouvé(s) pour le vol " + numVol);
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la recherche des billets");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return billets;
    }
    
    // ========== MÉTHODE LISTER TOUS ==========
    
    /**
     * Récupérer tous les billets de la base
     * @return Liste de tous les billets
     */
    public static List<Billet> listerTous() {
        List<Billet> billets = new ArrayList<>();
        String sql = "SELECT * FROM Billet ORDER BY date_emission DESC, num_billet";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                billets.add(extraireBillet(rs));
            }
            
            System.out.println("✓ " + billets.size() + " billet(s) récupéré(s)");
            return billets;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors du listing des billets");
            e.printStackTrace();
            return billets; // Retourne une liste vide en cas d'erreur
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // ========== MÉTHODE AJOUTER ==========
    
    /**
     * Ajouter un nouveau billet dans la base de données
     * @return true si succès, false sinon
     */
    public boolean ajouter() {
        // Si numBillet n'est pas défini, générer un numéro
        if (this.numBillet == null || this.numBillet.trim().isEmpty()) {
            this.numBillet = genererNumeroBillet();
        }
        
        String sql = "INSERT INTO Billet (num_billet, id_passager, classe, tarif, " +
                    "statut, date_emission, num_vol, num_siege) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Paramètres
            pstmt.setString(1, this.numBillet);
            pstmt.setInt(2, this.idPassager);
            pstmt.setString(3, this.classe.getLibelle());
            pstmt.setBigDecimal(4, this.tarif);
            pstmt.setString(5, this.statut.getLibelle());
            pstmt.setDate(6, Date.valueOf(this.dateEmission));
            pstmt.setString(7, this.numVol);
            pstmt.setString(8, this.numSiege);
            
            // Exécution
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Billet ajouté avec succès ! Numéro : " + this.numBillet);
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de l'ajout du billet");
            
            // Gestion des erreurs spécifiques
            if (e.getMessage().contains("num_billet")) {
                System.err.println("  Ce numéro de billet existe déjà !");
            } else if (e.getMessage().contains("id_passager")) {
                System.err.println("  Le passager n'existe pas !");
            } else if (e.getMessage().contains("foreign key")) {
                System.err.println("  Le passager n'existe pas ou contrainte de clé étrangère violée !");
            } else {
                e.printStackTrace();
            }
            
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // ========== MÉTHODE MODIFIER ==========
    
    /**
     * Modifier un billet existant
     * @return true si succès, false sinon
     */
    public boolean modifier() {
        if (this.numBillet == null) {
            System.err.println("✗ Impossible de modifier : Numéro de billet non défini");
            return false;
        }
        
        String sql = "UPDATE Billet SET id_passager = ?, classe = ?, tarif = ?, " +
                    "statut = ?, date_emission = ?, num_vol = ?, num_siege = ? " +
                    "WHERE num_billet = ?";
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Paramètres
            pstmt.setInt(1, this.idPassager);
            pstmt.setString(2, this.classe.getLibelle());
            pstmt.setBigDecimal(3, this.tarif);
            pstmt.setString(4, this.statut.getLibelle());
            pstmt.setDate(5, Date.valueOf(this.dateEmission));
            pstmt.setString(6, this.numVol);
            pstmt.setString(7, this.numSiege);
            pstmt.setString(8, this.numBillet);
            
            // Exécution
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Billet modifié avec succès !");
                return true;
            } else {
                System.out.println("⚠ Aucun billet modifié (numéro introuvable)");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la modification du billet");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // ========== MÉTHODE SUPPRIMER ==========
    
    /**
     * Supprimer un billet par son numéro
     * @param numBillet Le numéro du billet à supprimer
     * @return true si succès, false sinon
     */
    public static boolean supprimer(String numBillet) {
        String sql = "DELETE FROM Billet WHERE num_billet = ?";
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numBillet);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Billet supprimé avec succès !");
                return true;
            } else {
                System.out.println("⚠ Aucun billet supprimé (numéro introuvable)");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la suppression du billet");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // ========== MÉTHODES UTILITAIRES ==========
    
    /**
     * Annuler un billet (changer le statut en ANNULE)
     * @return true si succès, false sinon
     */
    public boolean annuler() {
        this.statut = StatutBillet.ANNULE;
        boolean resultat = this.modifier();
        if (resultat) {
            System.out.println("✓ Billet annulé : " + this.numBillet);
        }
        return resultat;
    }
    
    /**
     * Rembourser un billet (changer le statut en REMBOURSE)
     * @return true si succès, false sinon
     */
    public boolean rembourser() {
        this.statut = StatutBillet.REMBOURSE;
        boolean resultat = this.modifier();
        if (resultat) {
            System.out.println("✓ Billet remboursé : " + this.numBillet);
        }
        return resultat;
    }
    
    /**
     * Vérifier si un numéro de billet existe déjà
     * @param numBillet Le numéro à vérifier
     * @return true si le numéro existe déjà
     */
    public static boolean numeroExiste(String numBillet) {
        String sql = "SELECT COUNT(*) FROM Billet WHERE num_billet = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numBillet);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la vérification du numéro de billet");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Vérifier si un passager existe
     * @param idPassager L'ID du passager à vérifier
     * @return true si le passager existe
     */
    public static boolean passagerExiste(int idPassager) {
        String sql = "SELECT COUNT(*) FROM Passager WHERE id_passager = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPassager);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la vérification du passager");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Générer un numéro de billet unique
     * @return Numéro de billet généré
     */
    private String genererNumeroBillet() {
        // Format : BIL-YYYYMMDD-XXXX
        String prefix = "BIL-" + LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")) + "-";
        String sql = "SELECT COUNT(*) FROM Billet WHERE num_billet LIKE ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prefix + "%");
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1) + 1;
                return prefix + String.format("%04d", count);
            }
            
            return prefix + "0001";
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la génération du numéro de billet");
            // Fallback : timestamp
            return "BIL-" + System.currentTimeMillis();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Extraire un objet Billet depuis un ResultSet
     * @param rs Le ResultSet contenant les données
     * @return Un objet Billet
     */
    private static Billet extraireBillet(ResultSet rs) throws SQLException {
        return new Billet(
            rs.getString("num_billet"),
            rs.getInt("id_passager"),
            ClasseBillet.fromString(rs.getString("classe")),
            rs.getBigDecimal("tarif"),
            StatutBillet.fromString(rs.getString("statut")),
            rs.getDate("date_emission").toLocalDate(),
            rs.getString("num_vol"),
            rs.getString("num_siege")
        );
    }
    
    // ========== TOSTRING ==========
    
    @Override
    public String toString() {
        return "Billet{" +
                "numBillet='" + numBillet + '\'' +
                ", idPassager=" + idPassager +
                ", classe=" + classe +
                ", tarif=" + tarif +
                ", statut=" + statut +
                ", dateEmission=" + dateEmission +
                ", numVol='" + numVol + '\'' +
                ", numSiege='" + numSiege + '\'' +
                '}';
    }
}