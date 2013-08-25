package com.appsbybirbeck.winecritic.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.appsbybirbeck.winecritic.api.WineType;

/**
 * An entity used for storing and retrieving of Wine records.
 *
 * @author Stewart Gateley
 */
@Entity
@Table(name = "wine")
public class WineEntity
        extends AbstractAuditable<Long>
{

    private static final long serialVersionUID = 106968675823169898L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "winery", nullable = false)
    private String winery;

    @Column(name = "varietal")
    private String varietal;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WineType type;

    @Column(name = "vintage")
    private Integer vintage;

    @Column(name = "appellation")
    private String appellation;

    @Column(name = "price")
    private BigDecimal price;

    /**
     * Gets the name of this wine.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this wine.
     *
     * @param name name of this wine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the winery of this wine.
     *
     * @return winery
     */
    public String getWinery() {
        return winery;
    }

    /**
     * Sets the winery of this wine.
     *
     * @param winery winery of this wine.
     */
    public void setWinery(String winery) {
        this.winery = winery;
    }

    /**
     * Gets the varietal of this wine.
     *
     * @return varietal
     */
    public String getVarietal() {
        return varietal;
    }

    /**
     * Sets the varietal of this wine.
     *
     * @param varietal varietal of this wine.
     */
    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    /**
     * Get the type of this wine.
     *
     * @return {@link WineType}
     */
    public WineType getType() {
        return type;
    }

    /**
     * Sets the type of this wine.
     *
     * @param type {@link WineType}
     */
    public void setType(WineType type) {
        this.type = type;
    }

    /**
     * Gets the vintage of this wine.
     *
     * @return vintage
     */
    public Integer getVintage() {
        return vintage;
    }

    /**
     * Sets the vintage of this wine.
     *
     * @param vintage vintage of this wine.
     */
    public void setVintage(Integer vintage) {
        this.vintage = vintage;
    }

    /**
     * Gets the appellation of this wine.
     *
     * @return appellation
     */
    public String getAppellation() {
        return appellation;
    }

    /**
     * Sets the appellation of this wine.
     *
     * @param appellation appellation of this wine.
     */
    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    /**
     * Gets the price of this wine.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of this wine.
     *
     * @param price price of this wine.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("name", name)
                .append("winery", winery)
                .append("varietal", varietal)
                .append("type", type)
                .append("vintage", vintage)
                .append("appellation", appellation)
                .append("price", price)
                .appendSuper(super.toString())
                .toString();
    }

}
