package com.compagnieaerienne.enumeration;

/**
 * Énumération pour les nationalités
 * Liste des nationalités principales (extensible selon les besoins)
 */
public enum Nationalite {
    MAROCAINE("Marocaine"),
    FRANCAISE("Française"),
    ESPAGNOLE("Espagnole"),
    ALLEMANDE("Allemande"),
    ITALIENNE("Italienne"),
    BRITANNIQUE("Britannique"),
    AMERICAINE("Américaine"),
    CANADIENNE("Canadienne"),
    ALGERIENNE("Algérienne"),
    TUNISIENNE("Tunisienne"),
    EGYPTIENNE("Égyptienne"),
    SAOUDIENNE("Saoudienne"),
    EMIRATIE("Émiratie"),
    BELGE("Belge"),
    SUISSE("Suisse"),
    PORTUGAISE("Portugaise"),
    NEERLANDAISE("Néerlandaise"),
    TURQUE("Turque"),
    CHINOISE("Chinoise"),
    JAPONAISE("Japonaise"),
    INDIENNE("Indienne"),
    BRESILIENNE("Brésilienne"),
    ARGENTINE("Argentine"),
    SENEGALAISE("Sénégalaise"),
    IVOIRIENNE("Ivoirienne"),
    AUTRE("Autre");
    
    private final String libelle;
    
    /**
     * Constructeur
     * @param libelle Le libellé de la nationalité
     */
    Nationalite(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Obtenir le libellé
     * @return String - le libellé de la nationalité
     */
    public String getLibelle() {
        return libelle;
    }
    
    /**
     * Représentation textuelle
     * @return String - le libellé
     */
    @Override
    public String toString() {
        return libelle;
    }
    
    /**
     * Convertir une chaîne en Nationalite
     * @param text La chaîne à convertir
     * @return Nationalite correspondant (AUTRE si non trouvé)
     */
    public static Nationalite fromString(String text) {
        for (Nationalite nat : Nationalite.values()) {
            if (nat.libelle.equalsIgnoreCase(text)) {
                return nat;
            }
        }
        // Retourner AUTRE au lieu de lancer une exception
        return AUTRE;
    }
}