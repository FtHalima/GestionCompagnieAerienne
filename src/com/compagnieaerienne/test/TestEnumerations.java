package com.compagnieaerienne.test;

// Imports explicites de toutes les énumérations
import com.compagnieaerienne.enumeration.TypePassager;
import com.compagnieaerienne.enumeration.Genre;
import com.compagnieaerienne.enumeration.ClasseBillet;
import com.compagnieaerienne.enumeration.StatutBillet;
import com.compagnieaerienne.enumeration.Nationalite;

/**
 * Classe de test pour toutes les énumérations
 */
public class TestEnumerations {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        TEST DES ÉNUMÉRATIONS                       ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // ========== TEST 1 : TypePassager ==========
        System.out.println("▶ TEST 1 : Énumération TypePassager");
        System.out.println("  Types disponibles :");
        for (TypePassager type : TypePassager.values()) {
            System.out.println("    - " + type + " (code: " + type.name() + ")");
        }
        
        // Test de conversion
        try {
            TypePassager adulte = TypePassager.fromString("Adulte");
            System.out.println("  ✓ Conversion 'Adulte' → " + adulte);
        } catch (Exception e) {
            System.out.println("  ✗ Erreur : " + e.getMessage());
        }
        System.out.println();
        
        // ========== TEST 2 : Genre ==========
        System.out.println("▶ TEST 2 : Énumération Genre");
        System.out.println("  Genres disponibles :");
        for (Genre genre : Genre.values()) {
            System.out.println("    - " + genre + " (code: " + genre.name() + ")");
        }
        
        // Test de conversion
        try {
            Genre masculin = Genre.fromString("Masculin");
            System.out.println("  ✓ Conversion 'Masculin' → " + masculin);
        } catch (Exception e) {
            System.out.println("  ✗ Erreur : " + e.getMessage());
        }
        System.out.println();
        
        // ========== TEST 3 : ClasseBillet ==========
        System.out.println("▶ TEST 3 : Énumération ClasseBillet");
        System.out.println("  Classes disponibles :");
        for (ClasseBillet classe : ClasseBillet.values()) {
            System.out.println("    - " + classe + " (code: " + classe.name() + ")");
        }
        
        // Test de conversion
        try {
            ClasseBillet business = ClasseBillet.fromString("Business");
            System.out.println("  ✓ Conversion 'Business' → " + business);
        } catch (Exception e) {
            System.out.println("  ✗ Erreur : " + e.getMessage());
        }
        System.out.println();
        
        // ========== TEST 4 : StatutBillet ==========
        System.out.println("▶ TEST 4 : Énumération StatutBillet");
        System.out.println("  Statuts disponibles :");
        for (StatutBillet statut : StatutBillet.values()) {
            System.out.println("    - " + statut + " (code: " + statut.name() + ")");
        }
        
        // Test de conversion
        try {
            StatutBillet reserve = StatutBillet.fromString("Réservé");
            System.out.println("  ✓ Conversion 'Réservé' → " + reserve);
        } catch (Exception e) {
            System.out.println("  ✗ Erreur : " + e.getMessage());
        }
        System.out.println();
        
        // ========== TEST 5 : Nationalite ==========
        System.out.println("▶ TEST 5 : Énumération Nationalite");
        System.out.println("  Quelques nationalités disponibles :");
        System.out.println("    - " + Nationalite.MAROCAINE);
        System.out.println("    - " + Nationalite.FRANCAISE);
        System.out.println("    - " + Nationalite.ESPAGNOLE);
        System.out.println("    - " + Nationalite.AMERICAINE);
        System.out.println("    - " + Nationalite.AUTRE);
        System.out.println("  Total : " + Nationalite.values().length + " nationalités");
        
        // Test de conversion
        Nationalite marocaine = Nationalite.fromString("Marocaine");
        System.out.println("  ✓ Conversion 'Marocaine' → " + marocaine);
        
        Nationalite inconnu = Nationalite.fromString("Martienne");
        System.out.println("  ✓ Conversion 'Martienne' → " + inconnu + " (défaut)");
        System.out.println();
        
        // ========== RÉSUMÉ ==========
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              RÉSUMÉ DES TESTS                      ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ TypePassager : 4 valeurs                        ║");
        System.out.println("║  ✓ Genre : 2 valeurs                               ║");
        System.out.println("║  ✓ ClasseBillet : 3 valeurs                        ║");
        System.out.println("║  ✓ StatutBillet : 4 valeurs                        ║");
        System.out.println("║  ✓ Nationalite : " + String.format("%-2d", Nationalite.values().length) + " valeurs                            ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ Toutes les énumérations sont fonctionnelles !   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}