package com.appsbybirbeck.winecritic.api;

/**
 * A class to represent the type classification of a wine.
 *
 * @author Stewart Gateley
 */
public enum WineType {

    RED("Red Wine"),
    WHITE("White Wine"),
    ROSE("Rosé"),
    SPARKLING("Champagne & Sparkling"),
    FORTIFIED("Dessert, Sherry & Port");

    private final String name;

    private WineType(final String name) {
        this.name = name;
    }

    /**
     * Get the plain-text description for this wine type.
     *
     * @return the name of this wine type.
     */
    public String getName() {
        return this.name;
    }

}
