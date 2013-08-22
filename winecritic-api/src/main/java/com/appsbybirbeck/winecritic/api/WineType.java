package com.appsbybirbeck.winecritic.api;

public enum WineType {

    RED("Red Wine"),
    WHITE("White Wine"),
    ROSE("Rosé"),
    SPARKLING("Champagne & Sparkling"),
    DESSERT("Dessert, Sherry & Port");

    private final String name;

    WineType(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
