package com.appsbybirbeck.winecritic.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for getting information from Wine classes.
 *
 * @author Stewart Gateley
 */
public class Wine {

    private long id;
    private String name;
    private String winery;
    private String varietal;
    private WineType type;
    private String appellation;
    private int vintage;
    private BigDecimal price;

    /**
     * Get the id of this wine.
     *
     * @return id of the wine.
     */
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Get the name of this wine.
     *
     * @return name of the wine.
     */
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the winery where this wine was made.
     *
     * @return winery of the wine.
     */
    public String getWinery() {
        return winery;
    }

    public void setWinery(final String winery) {
        this.winery = winery;
    }

    /**
     * Get the grape varietal this wine is made from.
     *
     * @return varietal of the wine or {@value WineContstants.NON_VARIETAL} for blended wines or table wine.
     */
    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(final String varietal) {
        this.varietal = varietal;
    }

    /**
     * Get the general type (eg. Red, White, etc) of this wine.
     *
     * @return {@link WineType} type of the wine.
     */
    public WineType getType() {
        return type;
    }

    public void setType(final WineType type) {
        this.type = type;
    }

    /**
     * Get the appellation (growing region) for the grapes this wine is made from.
     *
     * @return appellation of the wine or {@value WineContstants.NON_APPELLATION} for wines from multiple appellations.
     */
    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(final String appellation) {
        this.appellation = appellation;
    }

    /**
     * Get the vintage (year the grapes were picked) of this wine.
     *
     * @return vintage of the wine or {@value WineContstants.NON_VINTAGE} for non-vintage/multi-vintage wines.
     */
    public int getVintage() {
        return vintage;
    }

    public void setVintage(final int vintage) {
        this.vintage = vintage;
    }

    /**
     * Get the retail price of this wine.
     *
     * @return price of the wine.
     */
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

}
