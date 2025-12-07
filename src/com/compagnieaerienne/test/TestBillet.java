package com.compagnieaerienne.test;

// Imports explicites
import com.compagnieaerienne.modele.Billet;
import com.compagnieaerienne.enumeration.ClasseBillet;
import com.compagnieaerienne.enumeration.StatutBillet;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de test pour la classe Billet
 */
public class TestBillet {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        TEST DE LA CLASSE BILLET                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // ========== TEST 1 : AJOUTER UN BILLET ==========
        System.out.println("▶ TEST 1 : Ajouter un nouveau billet");
        
        Billet b1 = new Billet(
            "BIL999",
            1,  // ID du passager (doit exister dans la BD)
            ClasseBillet.ECONOMIQUE,
            new BigDecimal("2500.00"),
            StatutBillet.RESERVE,
            LocalDate.now(),
            "VOL789",
            "25C"
        );
        
        if (b1.ajouter()) {
            System.out.println("  Billet ajouté : " + b1);
        }
        System.out.println();
        
        // ========== TEST 2 : CHERCHER UN BILLET ==========
        System.out.println("▶ TEST 2 : Chercher un billet par numéro");
        
        Billet b2 = Billet.chercher("BIL001");
        if (b2 != null) {
            System.out.println("  Billet trouvé : " + b2);
        }
        System.out.println();
        
        // ========== TEST 3 : CHERCHER PAR PASSAGER ==========
        System.out.println("▶ TEST 3 : Chercher les billets d'un passager");
        
        List<Billet> billetsPassager = Billet.chercherParPassager(1);
        System.out.println("  Billets du passager 1 :");
        for (Billet b : billetsPassager) {
            System.out.println("    - " + b.getNumBillet() + " (" + b.getStatut() + ")");
        }
        System.out.println();
        
        // ========== TEST 4 : CHERCHER PAR VOL ==========
        System.out.println("▶ TEST 4 : Chercher les billets d'un vol");
        
        List<Billet> billetsVol = Billet.chercherParVol("VOL123");
        System.out.println("  Billets du vol VOL123 :");
        for (Billet b : billetsVol) {
            System.out.println("    - " + b.getNumBillet() + " (Passager " + b.getIdPassager() + ")");
        }
        System.out.println();
        
        // ========== TEST 5 : MODIFIER UN BILLET ==========
        System.out.println("▶ TEST 5 : Modifier un billet");
        
        if (b1.getNumBillet() != null) {
            b1.setNumSiege("30A");
            b1.setTarif(new BigDecimal("2800.00"));
            
            if (b1.modifier()) {
                System.out.println("  Billet modifié : " + b1);
            }
        }
        System.out.println();
        
        // ========== TEST 6 : ANNULER UN BILLET ==========
        System.out.println("▶ TEST 6 : Annuler un billet");
        
        if (b1.annuler()) {
            System.out.println("  Statut après annulation : " + b1.getStatut());
        }
        System.out.println();
        
        // ========== TEST 7 : REMBOURSER UN BILLET ==========
        System.out.println("▶ TEST 7 : Rembourser un billet");
        
        if (b1.rembourser()) {
            System.out.println("  Statut après remboursement : " + b1.getStatut());
        }
        System.out.println();
        
        // ========== TEST 8 : SUPPRIMER UN BILLET ==========
        System.out.println("▶ TEST 8 : Supprimer un billet");
        
        // Créer un billet temporaire pour le supprimer
        Billet bTemp = new Billet(
            "TEMP999",
            1,
            ClasseBillet.ECONOMIQUE,
            new BigDecimal("1000.00"),
            StatutBillet.RESERVE,
            LocalDate.now(),
            "VOL000",
            "99Z"
        );
        
        if (bTemp.ajouter()) {
            System.out.println("  Billet temporaire créé : " + bTemp.getNumBillet());
            
            if (Billet.supprimer(bTemp.getNumBillet())) {
                System.out.println("  Billet supprimé avec succès");
            }
        }
        System.out.println();
        
        // ========== RÉSUMÉ ==========
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              TESTS TERMINÉS                        ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  ✓ Ajout de billet testé                          ║");
        System.out.println("║  ✓ Recherche par numéro testée                    ║");
        System.out.println("║  ✓ Recherche par passager testée                  ║");
        System.out.println("║  ✓ Recherche par vol testée                       ║");
        System.out.println("║  ✓ Modification testée                            ║");
        System.out.println("║  ✓ Annulation testée                              ║");
        System.out.println("║  ✓ Remboursement testé                            ║");
        System.out.println("║  ✓ Suppression testée                             ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
