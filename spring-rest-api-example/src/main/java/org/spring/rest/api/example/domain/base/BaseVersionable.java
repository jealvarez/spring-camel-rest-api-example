package org.spring.rest.api.example.domain.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseVersionable {

    @Version
    @Column(name = "version_lock")
    protected Long versionLock;

    public Long getVersionLock() {
        return versionLock;
    }

}
