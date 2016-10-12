package org.spring.rest.api.example.domain.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseIdentifiable extends BaseVersionable{

    public abstract Long getId();

    public abstract void setId(final Long id);

}
