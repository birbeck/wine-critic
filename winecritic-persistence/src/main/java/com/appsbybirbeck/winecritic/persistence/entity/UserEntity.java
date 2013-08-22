package com.appsbybirbeck.winecritic.persistence.entity;

import com.appsbybirbeck.winecritic.api.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends AbstractAuditable<Long> implements User {

    private static final long serialVersionUID = -7616630578067605283L;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<WineRatingEntity> ratings;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<WineRatingEntity> getRatings() {
        return ratings;
    }

}
