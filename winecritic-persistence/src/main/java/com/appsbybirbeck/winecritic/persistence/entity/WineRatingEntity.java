package com.appsbybirbeck.winecritic.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An entity used for storing and retrieving of WineRating records.
 *
 * @author Stewart Gateley
 */
@Entity
@Table(name = "wine_rating")
public class WineRatingEntity
        extends AbstractAuditable<Long>
{

    private static final long serialVersionUID = -6537941792332562709L;

    @Column(name = "score", nullable = false)
    private int score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wineId", nullable = false)
    private WineEntity wineEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;

    @Column(name = "review")
    private String review;

    /**
     * Gets the score for this rating.
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score for this rating.
     *
     * @param score score of this rating.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the wine for this rating.
     *
     * @return {@link WineEntity}
     */
    public WineEntity getWine() {
        return wineEntity;
    }

    /**
     * Sets the user for this rating.
     *
     * @param wineEntity {@link WineEntity} user
     */
    public void setWineEntity(WineEntity wineEntity) {
        this.wineEntity = wineEntity;
    }

    /**
     * Gets the user for this rating.
     *
     * @return {@link UserEntity}
     */
    public UserEntity getUser() {
        return userEntity;
    }

    /**
     * Sets the user for this rating.
     *
     * @param userEntity {@link UserEntity} user
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Gets the review for this rating.
     *
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * Sets the review for this rating.
     *
     * @param review review of this rating.
     */
    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("score", score)
                .append("wine", wineEntity.getName())
                .append("user", userEntity.getUsername())
                .append("review", review.length() > 20 ? review.substring(0, 17) + "..." : review)
                .appendSuper(super.toString())
                .toString();
    }

}
