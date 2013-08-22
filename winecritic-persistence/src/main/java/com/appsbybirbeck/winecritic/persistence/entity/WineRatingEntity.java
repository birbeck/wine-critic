package com.appsbybirbeck.winecritic.persistence.entity;

import com.appsbybirbeck.winecritic.api.WineRating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class WineRatingEntity extends AbstractAuditable<Long> implements WineRating {

    private static final long serialVersionUID = -6537941792332562709L;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wineId", nullable = false)
    private WineEntity wineEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;

    @Column(name = "review")
    private String review;

    @Override
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public WineEntity getWine() {
        return wineEntity;
    }

    public void setWineEntity(WineEntity wineEntity) {
        this.wineEntity = wineEntity;
    }

    @Override
    public UserEntity getUser() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
