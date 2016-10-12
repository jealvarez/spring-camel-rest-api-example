package org.spring.rest.api.example.domain.base;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@Cacheable
public abstract class BaseAuditable {

    @Column(name = "created_by", length = 45, updatable = false)
    protected String createdBy;

    @Temporal(value = TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    protected Date createdDate;

    @Column(name = "updated_by", length = 45)
    protected String updatedBy;

    @Temporal(value = TIMESTAMP)
    @Column(name = "updated_date")
    protected Date updatedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = new Date(System.currentTimeMillis());
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

}
