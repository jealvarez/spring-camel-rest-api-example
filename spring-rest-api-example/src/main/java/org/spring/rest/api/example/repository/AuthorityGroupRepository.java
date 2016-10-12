package org.spring.rest.api.example.repository;

import org.spring.rest.api.example.domain.security.AuthorityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityGroupRepository extends CrudRepository<AuthorityGroup, Long> {

}


