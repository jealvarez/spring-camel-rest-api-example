package org.spring.rest.api.example.service;

import org.spring.rest.api.example.domain.security.AuthorityGroup;
import org.springframework.web.multipart.MultipartFile;

public interface AuthorityGroupService {

    AuthorityGroup findOne(final Long id) throws Exception;

    void insert(final AuthorityGroup authorityGroup) throws Exception;

    void update(final AuthorityGroup authorityGroup) throws Exception;

    void delete(final AuthorityGroup authorityGroup) throws Exception;

    void processUploadedFile(final MultipartFile multipartFile) throws Exception;

}
