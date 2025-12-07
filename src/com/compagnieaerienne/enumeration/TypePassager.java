package com.compagnieaerienne.enumeration;

/**
 * Énumération pour le type de passager
 * Utilisée pour catégoriser les passagers selon leur âge
 */
public enum TypePassager {
    ADULTE("Adulte"),
    ENFANT("Enfant"),
    BEBE("Bébé"),
    SENIOR("Senior");
    
    private final String libelle;
    
    /**
     * Constructeur
     * @param libelle Le libellé du type de passager
     */
    TypePassager(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Obtenir le libellé
     * @return String - le libellé du type
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
     * Convertir une chaîne en TypePassager
     * @param text La chaîne à convertir
     * @return TypePassager correspondant
     * @throws IllegalArgumentException si le type n'existe pas
     */
    public static TypePassager fromString(String text) {
        for (TypePassager type : TypePassager.values()) {
            if (type.libelle.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Type de passager inconnu : " + text);
    }
}