package org.spring.rest.api.example.domain.base;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Cacheable
public abstract class BaseNamed extends BaseAuditable {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
