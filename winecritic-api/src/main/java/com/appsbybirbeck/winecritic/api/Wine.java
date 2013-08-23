package com.appsbybirbeck.winecritic.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for getting information from Wine classes.
 *
 * @author Stewart Gateley
 */
public interface Wine {

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

    /**
     * Get the id of this wine.
     *
     * @return id of the wine.
     */
    long getId();

    /**
     * Get the name of this wine.
     *
     * @return name of the wine.
     */
    String getName();

    /**
     * Get the winery where this wine was made.
     *
     * @return winery of the wine.
     */
    String getWinery();

    /**
     * Get the grape varietal this wine is made from.
     *
     * @return varietal of the wine or {@value #NON_VARIETAL} for blended wines or table wine.
     */
    String getVarietal();

    /**
     * Get the general type (eg. Red, White, etc) of this wine.
     *
     * @return {@link WineType} type of the wine.
     */
    WineType getType();

    /**
     * Get the appellation (growing region) for the grapes this wine is made from.
     *
     * @return appellation of the wine or {@value #NON_APPELLATION} for wines from multiple appellations.
     */
    String getAppellation();

    /**
     * Get the vintage (year the grapes were picked) of this wine.
     *
     * @return vintage of the wine or {@value #NON_VINTAGE} for non-vintage/multi-vintage wines.
     */
    Integer getVintage();

    /**
     * Get the retail price of this wine.
     *
     * @return price of the wine.
     */
    BigDecimal getPrice();

    /**
     * Get a list of ratings for this wine.
     *
     * @return list of {@link WineRating} for this wine.
     */
    List<WineRating> getRatings();

}
