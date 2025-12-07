package com.compagnieaerienne.enumeration;

/**
 * Énumération pour le statut d'un billet
 */
public enum StatutBillet {
    RESERVE("Réservé"),
    ANNULE("Annulé"),
    REMBOURSE("Remboursé"),
    MODIFIE("Modifié");
    
    private final String libelle;
    
    /**
     * Constructeur
     * @param libelle Le libellé du statut
     */
    StatutBillet(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Obtenir le libellé
     * @return String - le libellé du statut
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
     * Convertir une chaîne en StatutBillet
     * @param text La chaîne à convertir
     * @return StatutBillet correspondant
     * @throws IllegalArgumentException si le statut n'existe pas
     */
    public static StatutBillet fromString(String text) {
        for (StatutBillet statut : StatutBillet.values()) {
            if (statut.libelle.equalsIgnoreCase(text)) {
                return statut;
            }
        }
        throw new IllegalArgumentException("Statut de billet inconnu : " + text);
    }
}