package org.spring.rest.api.example.service;

import org.apache.commons.beanutils.BeanUtils;
import org.spring.rest.api.example.domain.security.AuthorityGroup;
import org.spring.rest.api.example.enums.ModificationType;
import org.spring.rest.api.example.model.xml.Records;
import org.spring.rest.api.example.repository.AuthorityGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorityGroupServiceImpl implements AuthorityGroupService {

    private final AuthorityGroupRepository authorityGroupRepository;
    private Map<String, String> fieldMapping;

    @Autowired
    public AuthorityGroupServiceImpl(final AuthorityGroupRepository authorityGroupRepository) {
        this.authorityGroupRepository = authorityGroupRepository;
    }

    @Override
    public AuthorityGroup findOne(final Long id) throws Exception {
        return authorityGroupRepository.findOne(id);
    }

    @Override
    public void insert(final AuthorityGroup authorityGroup) throws Exception {
        authorityGroupRepository.save(authorityGroup);
    }

    @Override
    public void update(final AuthorityGroup authorityGroup) throws Exception {
        authorityGroupRepository.save(authorityGroup);
    }

    @Override
    public void delete(final AuthorityGroup authorityGroup) throws Exception {
        authorityGroupRepository.delete(authorityGroup);
    }

    @Override
    public void processUploadedFile(final MultipartFile multipartFile) throws Exception {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final Records records = (Records) unmarshaller.unmarshal(multipartFile.getInputStream());

        for (final Records.Record record : records.getRecord()) {
            for (final Records.Record.Modifications.Modification modification : record.getModifications().getModification()) {
                final ModificationType modificationType = ModificationType.getModificationByExternalName(modification.getAction());
                final AuthorityGroup authorityGroup = mapToAuthorityGroup(modification.getFields());

                switch (modificationType) {
                    case INSERT:
                        insert(authorityGroup);
                        break;
                    case UPDATE:
                        update(authorityGroup);
                        break;
                    case DELETE:
                        delete(authorityGroup);
                        break;
                }
            }
        }
    }

    @PostConstruct
    private void initialize() {
        fieldMapping = new HashMap();
        fieldMapping.put("CODE", "id");
        fieldMapping.put("NAME", "name");
        fieldMapping.put("DESCRIPTION", "description");
        fieldMapping.put("ENABLED", "enabled");
    }

    private AuthorityGroup mapToAuthorityGroup(final Records.Record.Modifications.Modification.Fields fields) throws Exception {
        final AuthorityGroup authorityGroup = new AuthorityGroup();
        final Map<String, String> authorityGroupProperties = BeanUtils.describe(authorityGroup);

        for (final Records.Record.Modifications.Modification.Fields.Field field : fields.getField()) {
            authorityGroupProperties.put(fieldMapping.get(field.getName()), field.getValue());
        }

        BeanUtils.populate(authorityGroup, authorityGroupProperties);

        return authorityGroup;
    }

}
