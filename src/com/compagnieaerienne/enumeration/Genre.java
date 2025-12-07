package com.compagnieaerienne.enumeration;

/**
 * Énumération pour le genre d'une personne
 */
public enum Genre {
    MASCULIN("Masculin"),
    FEMININ("Féminin");
    
    private final String libelle;
    
    /**
     * Constructeur
     * @param libelle Le libellé du genre
     */
    Genre(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Obtenir le libellé
     * @return String - le libellé du genre
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
     * Convertir une chaîne en Genre
     * @param text La chaîne à convertir
     * @return Genre correspondant
     * @throws IllegalArgumentException si le genre n'existe pas
     */
    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.libelle.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Genre inconnu : " + text);
    }
}