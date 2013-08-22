package com.appsbybirbeck.winecritic.api;

import java.util.List;

public interface User {

    Long getId();

    String getUsername();

    List<? extends WineRating> getRatings();

}
