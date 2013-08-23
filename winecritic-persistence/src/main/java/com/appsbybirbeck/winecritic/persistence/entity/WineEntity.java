package com.appsbybirbeck.winecritic.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
public class WineEntity extends AbstractAuditable<Long> {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<WineRatingEntity> ratings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinery() {
        return winery;
    }

    public void setWinery(String winery) {
        this.winery = winery;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public WineType getType() {
        return type;
    }

    public void setType(WineType type) {
        this.type = type;
    }

    public Integer getVintage() {
        return vintage;
    }

    public void setVintage(Integer vintage) {
        this.vintage = vintage;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<WineRatingEntity> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("winery", winery)
                .append("varietal", varietal)
                .append("type", type)
                .append("vintage", vintage)
                .append("appellation", appellation)
                .append("price", price)
                .append("ratings", ratings)
                .toString();
    }

}
