package com.appsbybirbeck.winecritic.api;

public interface WineRating {

    Long getId();

    int getRating();

    String getReview();

    Wine getWine();

    User getUser();

}
