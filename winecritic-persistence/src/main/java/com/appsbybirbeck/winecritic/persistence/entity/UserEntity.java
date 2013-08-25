package com.appsbybirbeck.winecritic.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * An entity used for storing and retrieving of User records.
 *
 * @author Stewart Gateley
 */
@Entity
@Table(name = "user")
public class UserEntity
        extends AbstractAuditable<Long>
{

    private static final long serialVersionUID = -7616630578067605283L;

    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Gets the username for this user.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this user.
     *
     * @param username username for this user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("username", username)
                .appendSuper(super.toString())
                .toString();
    }

}
