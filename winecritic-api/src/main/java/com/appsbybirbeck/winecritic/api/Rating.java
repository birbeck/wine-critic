package com.appsbybirbeck.winecritic.api;

/**
 * Interface for getting information from ratings.
 *
 * @param <T> The type of rating.
 * @author Stewart Gateley
 */
public interface Rating<T> {

    /**
     * Get the id of this rating.
     *
     * @return id of the rating.
     */
    long getId();

    /**
     * Get the score of this rating.
     *
     * @return score of the rating.
     */
    int getScore();

    /**
     * Get the review of this rating.
     *
     * @return review of the rating.
     */
    String getReview();

    /**
     * Get the User who rated created this rating.
     *
     * @return {@link User} of the rating.
     */
    User getUser();

}
