package org.spring.rest.api.example.domain.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.spring.rest.api.example.domain.base.BaseNamed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "authority_group")
@Entity(name = "AuthorityGroup")
@JsonDeserialize(builder = AuthorityGroup.Builder.class)
public class AuthorityGroup extends BaseNamed {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "enabled")
    private Boolean enabled;

    public AuthorityGroup() { }

    private AuthorityGroup(final Builder builder) {
        id = builder.id;
        createdBy = builder.createdBy;
        createdDate = builder.createdDate;
        updatedBy = builder.updatedBy;
        updatedDate = builder.updatedDate;
        name = builder.name;
        description = builder.description;
        enabled = builder.enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public static Builder anAuthorityGroup() {
        return new Builder();
    }

    public static Builder anAuthorityGroup(final AuthorityGroup authorityGroup) {
        return new Builder(authorityGroup);
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private Long id;
        private String createdBy;
        private Date createdDate;
        private String updatedBy;
        private Date updatedDate;
        private String name;
        private String description;
        private Boolean enabled;

        private Builder(final AuthorityGroup authorityGroup) {
            this.id = authorityGroup.id;
            this.createdBy = authorityGroup.createdBy;
            this.createdDate = authorityGroup.createdDate;
            this.updatedBy = authorityGroup.updatedBy;
            this.updatedDate = authorityGroup.updatedDate;
            this.name = authorityGroup.name;
            this.description = authorityGroup.description;
            this.enabled = authorityGroup.enabled;
        }

        private Builder() {

        }

        public Builder withId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder withCreatedBy(final String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder withCreatedDate(final Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withUpdatedBy(final String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder withUpdatedDate(final Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withEnabled(final Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public AuthorityGroup build() {
            return new AuthorityGroup(this);
        }

    }

}
