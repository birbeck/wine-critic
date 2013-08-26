package com.appsbybirbeck.winecritic.api;

public final class WineContstants {

    private WineContstants() {
    };

    /**
     * Indicates that a wine is made by blending grapes harvested in different years.
     */
    public static final int NON_VINTAGE = -1;

    /**
     * Indicates that a wine is made from grapes grown in more than one appellation.
     */
    public static final String NON_APPELLATION = "NA";

    /**
     * Indicates that a wine is made by blending more than one known varietal or is designated as a table wine.
     */
    public static final String NON_VARIETAL = "NV";

}
