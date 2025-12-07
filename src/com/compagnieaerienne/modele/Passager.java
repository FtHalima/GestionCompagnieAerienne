package com.compagnieaerienne.modele;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.compagnieaerienne.dao.ConnexionBD;
import com.compagnieaerienne.enumeration.*;

/**
 * Classe représentant un Passager
 * Gère les opérations CRUD sur la table Passager
 */
public class Passager {
    
    // ========== ATTRIBUTS ==========
    private Integer idPassager;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numPasseport;
    private Nationalite nationalite;
    private TypePassager typePassager;
    private Genre genre;
    private String email;
    private String telephone;
    
    // ========== CONSTRUCTEURS ==========
    
    /**
     * Constructeur par défaut
     */
    public Passager() {
    }
    
    /**
     * Constructeur avec tous les paramètres (sans ID)
     */
    public Passager(String nom, String prenom, LocalDate dateNaissance, 
                   String numPasseport, Nationalite nationalite, 
                   TypePassager typePassager, Genre genre, 
                   String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numPasseport = numPasseport;
        this.nationalite = nationalite;
        this.typePassager = typePassager;
        this.genre = genre;
        this.email = email;
        this.telephone = telephone;
    }
    
    /**
     * Constructeur avec tous les paramètres (avec ID)
     */
    public Passager(Integer idPassager, String nom, String prenom, 
                   LocalDate dateNaissance, String numPasseport, 
                   Nationalite nationalite, TypePassager typePassager, 
                   Genre genre, String email, String telephone) {
        this.idPassager = idPassager;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numPasseport = numPasseport;
        this.nationalite = nationalite;
        this.typePassager = typePassager;
        this.genre = genre;
        this.email = email;
        this.telephone = telephone;
    }
    
    // ========== GETTERS ET SETTERS ==========
    
    public Integer getIdPassager() {
        return idPassager;
    }
    
    public void setIdPassager(Integer idPassager) {
        this.idPassager = idPassager;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getNumPasseport() {
        return numPasseport;
    }
    
    public void setNumPasseport(String numPasseport) {
        this.numPasseport = numPasseport;
    }
    
    public Nationalite getNationalite() {
        return nationalite;
    }
    
    public void setNationalite(Nationalite nationalite) {
        this.nationalite = nationalite;
    }
    
    public TypePassager getTypePassager() {
        return typePassager;
    }
    
    public void setTypePassager(TypePassager typePassager) {
        this.typePassager = typePassager;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    // ========== MÉTHODE CHERCHER ==========
    
    /**
     * Chercher un passager par son ID
     * @param id L'ID du passager
     * @return Passager trouvé ou null
     */
    public static Passager chercher(int id) {
        String sql = "SELECT * FROM Passager WHERE id_passager = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extrairePassager(rs);
            }
            
            System.out.println("⚠ Aucun passager trouvé avec l'ID : " + id);
            return null;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la recherche du passager");
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
     * Chercher un passager par son numéro de passeport
     * @param numPasseport Le numéro de passeport
     * @return Passager trouvé ou null
     */
    public static Passager chercherParPasseport(String numPasseport) {
        String sql = "SELECT * FROM Passager WHERE num_passeport = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numPasseport);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extrairePassager(rs);
            }
            
            System.out.println("⚠ Aucun passager trouvé avec le passeport : " + numPasseport);
            return null;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la recherche du passager");
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
    
    // ========== MÉTHODE LISTER TOUS ==========
    
    /**
     * Récupérer tous les passagers de la base
     * @return Liste de tous les passagers
     */
    public static List<Passager> listerTous() {
        List<Passager> passagers = new ArrayList<>();
        String sql = "SELECT * FROM Passager ORDER BY nom, prenom";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Passager p = extrairePassager(rs);
                passagers.add(p);
            }
            
            System.out.println("✓ " + passagers.size() + " passagers récupérés");
            return passagers;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors du listing des passagers");
            e.printStackTrace();
            return passagers; // Retourne une liste vide en cas d'erreur
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
     * Ajouter un nouveau passager dans la base de données
     * @return true si succès, false sinon
     */
    public boolean ajouter() {
        String sql = "INSERT INTO Passager (nom, prenom, date_naissance, num_passeport, " +
                    "nationalite, type_passager, genre, email, telephone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Pour PostgreSQL avec RETURNING
        String sqlPostgres = sql + " RETURNING id_passager";
        String sqlMySQL = sql; // Pour MySQL
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            
            // Utiliser la bonne requête selon le type de base
            String databaseProductName = conn.getMetaData().getDatabaseProductName();
            boolean isPostgreSQL = databaseProductName.toLowerCase().contains("postgresql");
            
            if (isPostgreSQL) {
                pstmt = conn.prepareStatement(sqlPostgres);
            } else {
                pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
            
            // Paramètres
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setDate(3, Date.valueOf(this.dateNaissance));
            pstmt.setString(4, this.numPasseport);
            pstmt.setString(5, this.nationalite.getLibelle());
            pstmt.setString(6, this.typePassager.getLibelle());
            pstmt.setString(7, this.genre.getLibelle());
            pstmt.setString(8, this.email);
            pstmt.setString(9, this.telephone);
            
            // Exécution
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Récupérer l'ID généré
                if (isPostgreSQL) {
                    rs = pstmt.getResultSet();
                    if (rs.next()) {
                        this.idPassager = rs.getInt("id_passager");
                    }
                } else {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        this.idPassager = rs.getInt(1);
                    }
                }
                
                System.out.println("✓ Passager ajouté avec succès ! ID : " + this.idPassager);
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de l'ajout du passager");
            
            // Gestion des erreurs spécifiques
            if (e.getMessage().contains("num_passeport")) {
                System.err.println("  Ce numéro de passeport existe déjà !");
            } else if (e.getMessage().contains("email")) {
                System.err.println("  Cet email existe déjà !");
            } else {
                e.printStackTrace();
            }
            
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
    
    // ========== MÉTHODE MODIFIER ==========
    
    /**
     * Modifier un passager existant
     * @return true si succès, false sinon
     */
    public boolean modifier() {
        if (this.idPassager == null) {
            System.err.println("✗ Impossible de modifier : ID du passager non défini");
            return false;
        }
        
        String sql = "UPDATE Passager SET nom = ?, prenom = ?, date_naissance = ?, " +
                    "num_passeport = ?, nationalite = ?, type_passager = ?, " +
                    "genre = ?, email = ?, telephone = ? " +
                    "WHERE id_passager = ?";
        
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Paramètres
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setDate(3, Date.valueOf(this.dateNaissance));
            pstmt.setString(4, this.numPasseport);
            pstmt.setString(5, this.nationalite.getLibelle());
            pstmt.setString(6, this.typePassager.getLibelle());
            pstmt.setString(7, this.genre.getLibelle());
            pstmt.setString(8, this.email);
            pstmt.setString(9, this.telephone);
            pstmt.setInt(10, this.idPassager);
            
            // Exécution
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Passager modifié avec succès !");
                return true;
            } else {
                System.out.println("⚠ Aucun passager modifié (ID introuvable)");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la modification du passager");
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
     * Supprimer un passager par son ID
     * @param id L'ID du passager à supprimer
     * @return true si succès, false sinon
     */
    public static boolean supprimer(int id) {
        String sql = "DELETE FROM Passager WHERE id_passager = ?";
        PreparedStatement pstmt = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Passager supprimé avec succès !");
                return true;
            } else {
                System.out.println("⚠ Aucun passager supprimé (ID introuvable)");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la suppression du passager");
            
            // Si le passager a des billets, la suppression échouera
            if (e.getMessage().contains("foreign key")) {
                System.err.println("  Ce passager a des billets associés !");
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
    
    // ========== MÉTHODE UTILITAIRE ==========
    
    /**
     * Extraire un objet Passager depuis un ResultSet
     * @param rs Le ResultSet contenant les données
     * @return Un objet Passager
     */
    private static Passager extrairePassager(ResultSet rs) throws SQLException {
        return new Passager(
            rs.getInt("id_passager"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getDate("date_naissance").toLocalDate(),
            rs.getString("num_passeport"),
            Nationalite.fromString(rs.getString("nationalite")),
            TypePassager.fromString(rs.getString("type_passager")),
            Genre.fromString(rs.getString("genre")),
            rs.getString("email"),
            rs.getString("telephone")
        );
    }
    
    // ========== MÉTHODES VALIDATION ==========
    
    /**
     * Vérifier si un numéro de passeport existe déjà
     * @param numPasseport Le numéro à vérifier
     * @param idExclu ID à exclure (pour la modification)
     * @return true si le passeport existe déjà
     */
    public static boolean passeportExiste(String numPasseport, Integer idExclu) {
        String sql = "SELECT COUNT(*) FROM Passager WHERE num_passeport = ? AND id_passager != ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, numPasseport);
            pstmt.setObject(2, idExclu);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la vérification du passeport");
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
     * Vérifier si un email existe déjà
     * @param email L'email à vérifier
     * @param idExclu ID à exclure (pour la modification)
     * @return true si l'email existe déjà
     */
    public static boolean emailExiste(String email, Integer idExclu) {
        String sql = "SELECT COUNT(*) FROM Passager WHERE email = ? AND id_passager != ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Connection conn = ConnexionBD.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setObject(2, idExclu);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erreur lors de la vérification de l'email");
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
    
    // ========== TOSTRING ==========
    
    @Override
    public String toString() {
        return "Passager{" +
                "ID=" + idPassager +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", passeport='" + numPasseport + '\'' +
                ", nationalite=" + nationalite +
                ", type=" + typePassager +
                ", genre=" + genre +
                ", email='" + email + '\'' +
                ", tel='" + telephone + '\'' +
                '}';
    }
}