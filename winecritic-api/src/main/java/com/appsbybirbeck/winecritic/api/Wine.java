package com.appsbybirbeck.winecritic.api;

import java.math.BigDecimal;
import java.util.List;

public interface Wine {

    Long getId();

    String getName();

    String getWinery();

    String getVarietal();

    WineType getType();

    String getAppellation();

    Integer getVintage();

    BigDecimal getPrice();

    List<? extends WineRating> getRatings();

}
