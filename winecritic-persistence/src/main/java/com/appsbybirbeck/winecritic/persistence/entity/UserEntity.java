package com.appsbybirbeck.winecritic.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An entity used for storing and retrieving of User records.
 *
 * @author Stewart Gateley
 */
@Entity
@Table(name = "user")
public class UserEntity extends AbstractAuditable<Long> {

    private static final long serialVersionUID = -7616630578067605283L;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<WineRatingEntity> ratings;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<WineRatingEntity> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("ratings", ratings)
                .toString();
    }

}
