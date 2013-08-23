package com.appsbybirbeck.winecritic.api;

import java.util.List;

/**
 * Interface for getting information from User classes.
 *
 * @author Stewart Gateley
 */
public interface User {

    /**
     * Get the id of this user.
     *
     * @return id of the user.
     */
    long getId();

    /**
     * Get the username of this user.
     *
     * @return the username of the user.
     */
    String getUsername();

    /**
     * Get a list of ratings by this user.
     *
     * @return list of {@link WineRating} by the user.
     */
    List<WineRating> getWineRatings();

}
