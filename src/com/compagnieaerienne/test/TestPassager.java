package com.compagnieaerienne.test;

// Imports explicites
import com.compagnieaerienne.modele.Passager;
import com.compagnieaerienne.enumeration.Genre;
import com.compagnieaerienne.enumeration.Nationalite;
import com.compagnieaerienne.enumeration.TypePassager;
import java.time.LocalDate;

/**
 * Classe de test pour la classe Passager
 */
public class TestPassager {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        TEST DE LA CLASSE PASSAGER                  ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // ========== TEST 1 : AJOUTER UN PASSAGER ==========
        System.out.println("▶ TEST 1 : Ajouter un nouveau passager");
        
        Passager p1 = new Passager(
            "Tazi",
            "Amina",
            LocalDate.of(1995, 6, 20),
            "M999888",
            Nationalite.MAROCAINE,
            TypePassager.ADULTE,
            Genre.FEMININ,
            "amina.tazi@email.com",
            "0661234567"
        );
        
        if (p1.ajouter()) {
            System.out.println("  Passager ajouté : " + p1);
        }
        System.out.println();
        
        // ========== TEST 2 : CHERCHER UN PASSAGER ==========
        System.out.println("▶ TEST 2 : Chercher un passager par ID");
        
        Passager p2 = Passager.chercher(1);
        if (p2 != null) {
            System.out.println("  Passager trouvé : " + p2);
        }
        System.out.println();
        
        // ========== TEST 3 : CHERCHER PAR PASSEPORT ==========
        System.out.println("▶ TEST 3 : Chercher par numéro de passeport");
        
        Passager p3 = Passager.chercherParPasseport("M123456");
        if (p3 != null) {
            System.out.println("  Passager trouvé : " + p3);
        }
        System.out.println();
        
        // ========== TEST 4 : MODIFIER UN PASSAGER ==========
        System.out.println("▶ TEST 4 : Modifier un passager");
        
        if (p1.getIdPassager() != null) {
            p1.setTelephone("0677889900");
            p1.setEmail("amina.tazi.new@email.com");
            
            if (p1.modifier()) {
                System.out.println("  Passager modifié : " + p1);
            }
        }
        System.out.println();
        
        // ========== TEST 5 : SUPPRIMER UN PASSAGER ==========
        System.out.println("▶ TEST 5 : Supprimer un passager");
        
        // Créer un passager temporaire pour le supprimer
        Passager pTemp = new Passager(
            "Test",
            "Suppression",
            LocalDate.of(2000, 1, 1),
            "TEMP001",
            Nationalite.AUTRE,
            TypePassager.ADULTE,
            Genre.MASCULIN,
            "temp@test.com",
            "0600000000"
        );
        
        if (pTemp.ajouter()) {
            System.out.println("  Passager temporaire créé : ID " + pTemp.getIdPassager());
            
            if (Passager.supprimer(pTemp.getIdPassager())) {
                System.out.println("  Passager supprimé avec succès");
            }
        }
        System.out.println();
        
        // ========== RÉSUMÉ ==========
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              TESTS TERMINÉS                        ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ Ajout de passager testé                        ║");
        System.out.println("║  ✓ Recherche par ID testée                        ║");
        System.out.println("║  ✓ Recherche par passeport testée                 ║");
        System.out.println("║  ✓ Modification testée                            ║");
        System.out.println("║  ✓ Suppression testée                             ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}