package com.appsbybirbeck.winecritic.api;

/**
 * Interface for getting information from WineRating classes.
 *
 * @author Stewart Gateley
 */
public interface WineRating extends Rating<Wine> {

    /**
     * Get the {@link Wine} that was rated.
     *
     * @return {@link Wine} of the rating.
     */
    Wine getWine();

}
