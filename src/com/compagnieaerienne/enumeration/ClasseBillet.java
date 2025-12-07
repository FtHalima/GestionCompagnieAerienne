package com.compagnieaerienne.enumeration;

/**
 * Énumération pour la classe d'un billet d'avion
 */
public enum ClasseBillet {
    PREMIERE("Première"),
    BUSINESS("Business"),
    ECONOMIQUE("Économique");
    
    private final String libelle;
    
    /**
     * Constructeur
     * @param libelle Le libellé de la classe
     */
    ClasseBillet(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Obtenir le libellé
     * @return String - le libellé de la classe
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
     * Convertir une chaîne en ClasseBillet
     * @param text La chaîne à convertir
     * @return ClasseBillet correspondant
     * @throws IllegalArgumentException si la classe n'existe pas
     */
    public static ClasseBillet fromString(String text) {
        for (ClasseBillet classe : ClasseBillet.values()) {
            if (classe.libelle.equalsIgnoreCase(text)) {
                return classe;
            }
        }
        throw new IllegalArgumentException("Classe de billet inconnue : " + text);
    }
}