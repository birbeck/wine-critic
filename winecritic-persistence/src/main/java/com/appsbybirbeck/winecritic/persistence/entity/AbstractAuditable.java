package com.appsbybirbeck.winecritic.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * A base entity that will automatically set the created and last modified timestamp of derived classes.
 *
 * @author Stewart Gateley
 */
@MappedSuperclass
abstract class AbstractAuditable<ID extends Serializable>
        extends AbstractPersistable<ID>
{

    @Column(updatable = false, nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModifiedDate;

    /**
     * Gets the DateTime when this entity was initially stored in the database.
     *
     * @return created datetime
     */
    public DateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Gets the DateTime that this entity was last modified.
     *
     * @return last modified datetime
     */
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createdDate", createdDate)
                .append("lastModifiedDate", lastModifiedDate)
                .toString();
    }

    /**
     * A listener applied to all persistent entities derived from this entity. Ensures that the {@link #lastModifiedDate}
     * and {@link #createdDate} are set before persisting a new record.
     */
    @PrePersist
    void onPrePersist() {
        lastModifiedDate = createdDate = new DateTime();
    }

    /**
     * A listener applied to all persistent entities derived from this entity. Ensures that the {@link #lastModifiedDate}
     * is updated before updating an existing record.
     */
    @PreUpdate
    void onPreUpdate() {
        lastModifiedDate = new DateTime();
    }

}
