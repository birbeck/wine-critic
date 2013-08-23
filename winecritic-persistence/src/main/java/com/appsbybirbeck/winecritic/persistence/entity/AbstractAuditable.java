package com.appsbybirbeck.winecritic.persistence.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

/**
 * A base entity will automatically set the created and last modified timestamp of derived classes.
 *
 * @author Stewart Gateley
 */
@MappedSuperclass
@EntityListeners({AbstractAuditable.PrePersistListener.class, AbstractAuditable.PreUpdateListener.class})
abstract class AbstractAuditable<ID extends Serializable> extends AbstractPersistable<ID> {

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

    /**
     * A listener applied to all persistent entities derived from this entity. Ensures that the {@link #lastModifiedDate}
     * and {@link #createdDate} are set before persisting a new record.
     */
    public static class PrePersistListener {
        @PrePersist
        public void setAuditFields(final Object o) {
            if (o instanceof AbstractAuditable) {
                final DateTime now = new DateTime();
                ((AbstractAuditable) o).createdDate = now;
                ((AbstractAuditable) o).lastModifiedDate = now;
            }
        }
    }

    /**
     * A listener applied to all persistent entities derived from this entity. Ensures that the {@link #lastModifiedDate}
     * is updated before updating an existing record.
     */
    public static class PreUpdateListener {
        @PreUpdate
        public void setAuditFields(final Object o) {
            if (o instanceof AbstractAuditable) {
                ((AbstractAuditable) o).lastModifiedDate = new DateTime();
            }
        }
    }

}
