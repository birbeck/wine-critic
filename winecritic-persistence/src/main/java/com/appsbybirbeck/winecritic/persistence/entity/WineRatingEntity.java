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
public class WineRatingEntity extends AbstractAuditable<Long> {

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public WineEntity getWine() {
        return wineEntity;
    }

    public void setWineEntity(WineEntity wineEntity) {
        this.wineEntity = wineEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("score", score)
                .append("wine", wineEntity.getName())
                .append("user", userEntity.getUsername())
                .append("review", review.length() > 20 ? review.substring(0, 17) + "..." : review)
                .toString();
    }

}
