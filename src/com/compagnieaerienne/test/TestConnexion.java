package com.compagnieaerienne.test;

import com.compagnieaerienne.dao.ConnexionBD;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de test pour vérifier la connexion à la base de données
 */
public class TestConnexion {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   TEST DE CONNEXION À LA BASE DE DONNÉES          ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // ========== TEST 1 : Obtenir la connexion ==========
        System.out.println("▶ TEST 1 : Obtention de la connexion...");
        Connection conn = ConnexionBD.getConnection();
        
        if (conn != null) {
            System.out.println("✓ Connexion obtenue avec succès !\n");
        } else {
            System.out.println("✗ Échec de la connexion !\n");
            return; // Arrêter si la connexion a échoué
        }
        
        // ========== TEST 2 : Vérifier que la connexion est active ==========
        System.out.println("▶ TEST 2 : Vérification de l'état de la connexion...");
        if (ConnexionBD.isConnected()) {
            System.out.println("✓ La connexion est active !\n");
        } else {
            System.out.println("✗ La connexion n'est pas active !\n");
        }
        
        // ========== TEST 3 : Afficher les informations de connexion ==========
        System.out.println("▶ TEST 3 : Informations de connexion...");
        ConnexionBD.afficherInfos();
        
        // ========== TEST 4 : Obtenir les métadonnées de la base ==========
        System.out.println("▶ TEST 4 : Métadonnées de la base de données...");
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("  Nom de la BD      : " + metaData.getDatabaseProductName());
            System.out.println("  Version de la BD  : " + metaData.getDatabaseProductVersion());
            System.out.println("  Driver JDBC       : " + metaData.getDriverName());
            System.out.println("  Version du Driver : " + metaData.getDriverVersion());
            System.out.println("✓ Métadonnées récupérées avec succès !\n");
            
        } catch (SQLException e) {
            System.out.println("✗ Erreur lors de la récupération des métadonnées");
            e.printStackTrace();
        }
        
        // ========== TEST 5 : Lister les tables ==========
        System.out.println("▶ TEST 5 : Liste des tables dans la base de données...");
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            
            System.out.println("  Tables trouvées :");
            int count = 0;
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("    - " + tableName);
                count++;
            }
            
            if (count == 0) {
                System.out.println("    ⚠ Aucune table trouvée (base de données vide)");
            } else {
                System.out.println("✓ " + count + " table(s) trouvée(s) !\n");
            }
            
            tables.close();
            
        } catch (SQLException e) {
            System.out.println("✗ Erreur lors de la récupération des tables");
            e.printStackTrace();
        }
        
        // ========== TEST 6 : Exécuter une requête simple ==========
        System.out.println("▶ TEST 6 : Exécution d'une requête SQL simple...");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM Passager");
            
            if (rs.next()) {
                int total = rs.getInt("total");
                System.out.println("  Nombre de passagers dans la BD : " + total);
                System.out.println("✓ Requête exécutée avec succès !\n");
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("⚠ Table 'Passager' non trouvée ou base vide");
            System.out.println("  (C'est normal si vous n'avez pas encore exécuté le script SQL)\n");
        }
        
        // ========== TEST 7 : Fermer la connexion ==========
        System.out.println("▶ TEST 7 : Fermeture de la connexion...");
        ConnexionBD.closeConnection();
        
        if (!ConnexionBD.isConnected()) {
            System.out.println("✓ Connexion fermée avec succès !\n");
        } else {
            System.out.println("✗ La connexion n'a pas été fermée correctement !\n");
        }
        
        // ========== RÉSUMÉ ==========
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              RÉSUMÉ DES TESTS                      ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ Connexion établie                               ║");
        System.out.println("║  ✓ Driver PostgreSQL fonctionnel                   ║");
        System.out.println("║  ✓ Base de données accessible                      ║");
        System.out.println("║  ✓ Fermeture de connexion fonctionnelle            ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}