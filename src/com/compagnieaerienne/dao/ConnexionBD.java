package com.compagnieaerienne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion à la base de données PostgreSQL
 * Pattern Singleton : une seule instance de connexion
 */
public class ConnexionBD {
    
    // ========== CONFIGURATION DE LA BASE DE DONNÉES ==========
    private static final String URL = "jdbc:postgresql://localhost:5432/CompagnieAérienne";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";  // ⚠️ Votre mot de passe
    
    // Instance unique de connexion (Singleton)
    private static Connection connection = null;
    
    // ========== CONSTRUCTEUR PRIVÉ (SINGLETON) ==========
    private ConnexionBD() {
    }
    
    // ========== MÉTHODE POUR OBTENIR LA CONNEXION ==========
    public static Connection getConnection() {
        try {
            // Vérifier si la connexion existe et est valide
            if (connection == null || connection.isClosed()) {
                // Charger le driver PostgreSQL
                Class.forName("org.postgresql.Driver");
                System.out.println("✓ Driver PostgreSQL chargé avec succès");
                
                // Établir la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Connexion à la base de données établie avec succès !");
                System.out.println("  URL: " + URL);
                System.out.println("  User: " + USER);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("✗ ERREUR : Driver PostgreSQL non trouvé !");
            System.err.println("  Vérifiez que postgresql.jar est dans le Build Path");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✗ ERREUR : Impossible de se connecter à la base de données !");
            System.err.println("  Vérifiez :");
            System.err.println("  - PostgreSQL est démarré");
            System.err.println("  - La base de données 'CompagnieAérienne' existe");
            System.err.println("  - L'URL, le user et le password sont corrects");
            e.printStackTrace();
        }
        return connection;
    }
    
    // ========== MÉTHODE POUR FERMER LA CONNEXION ==========
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✓ Connexion fermée avec succès");
            } catch (SQLException e) {
                System.err.println("✗ ERREUR lors de la fermeture de la connexion !");
                e.printStackTrace();
            }
        }
    }
    
    // ========== MÉTHODE POUR TESTER LA CONNEXION ==========
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    // ========== MÉTHODE POUR OBTENIR LES INFORMATIONS ==========
    public static void afficherInfos() {
        System.out.println("\n========== INFORMATIONS DE CONNEXION ==========");
        System.out.println("URL      : " + URL);
        System.out.println("User     : " + USER);
        System.out.println("Statut   : " + (isConnected() ? "✓ Connecté" : "✗ Déconnecté"));
        System.out.println("===============================================\n");
    }
}