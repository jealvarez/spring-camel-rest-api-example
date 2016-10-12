package org.spring.rest.api.example.repository;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.rest.api.example.configuration.MySqlDataSourceConfiguration;
import org.spring.rest.api.example.configuration.PropertyPlaceholderConfiguration;
import org.spring.rest.api.example.domain.security.AuthorityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertyPlaceholderConfiguration.class, MySqlDataSourceConfiguration.class})
public class AuthorityGroupRepositoryIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AuthorityGroupRepository authorityGroupRepository;

    @Test
    public void testFindOne() throws Exception {
        final AuthorityGroup expectedAuthorityGroup = anAuthorityGroup();
        final AuthorityGroup authorityGroupInserted = authorityGroupRepository.save(expectedAuthorityGroup);

        final AuthorityGroup actualAuthorityGroup = authorityGroupRepository.findOne(authorityGroupInserted.getId());

        assertThat(actualAuthorityGroup, is(not(nullValue())));
        assertThat(actualAuthorityGroup.getId(), is(not(nullValue())));
        assertThat(actualAuthorityGroup.getCreatedBy(), is(expectedAuthorityGroup.getCreatedBy()));
        assertThat(actualAuthorityGroup.getName(), Is.is(expectedAuthorityGroup.getName()));
        assertThat(actualAuthorityGroup.getDescription(), Is.is(expectedAuthorityGroup.getDescription()));
        assertThat(actualAuthorityGroup.getEnabled(), is(expectedAuthorityGroup.getEnabled()));
    }

    @Test
    public void testInsert() throws Exception {
        final AuthorityGroup expectedAuthorityGroup = anAuthorityGroup();
        final AuthorityGroup actualAuthorityGroup = authorityGroupRepository.save(expectedAuthorityGroup);

        assertThat(actualAuthorityGroup, is(not(nullValue())));
        assertThat(actualAuthorityGroup.getId(), is(not(nullValue())));
        assertThat(actualAuthorityGroup.getCreatedBy(), is(expectedAuthorityGroup.getCreatedBy()));
        assertThat(actualAuthorityGroup.getName(), Is.is(expectedAuthorityGroup.getName()));
        assertThat(actualAuthorityGroup.getDescription(), Is.is(expectedAuthorityGroup.getDescription()));
        assertThat(actualAuthorityGroup.getEnabled(), is(expectedAuthorityGroup.getEnabled()));
    }

    @Test
    public void testDelete() throws Exception {
        final AuthorityGroup authorityGroup = anAuthorityGroup();
        final AuthorityGroup authorityGroupInserted = authorityGroupRepository.save(authorityGroup);

        authorityGroupRepository.delete(authorityGroupInserted.getId());
        final AuthorityGroup actualAuthorityGroup = authorityGroupRepository.findOne(authorityGroupInserted.getId());

        assertThat(actualAuthorityGroup, is(nullValue()));
    }

    @Test
    public void testUpdate() throws Exception {
        final AuthorityGroup authorityGroup = anAuthorityGroup();
        final AuthorityGroup authorityGroupInserted = authorityGroupRepository.save(authorityGroup);

        final AuthorityGroup expectedAuthorityGroup = AuthorityGroup.anAuthorityGroup(authorityGroupInserted)
                .withCreatedBy("SYSTEM_TEST_2")
                .withName("ROLE_TEST_2")
                .withDescription("DESCRIPTION_TEST_2")
                .build();

        final AuthorityGroup actualAuthorityGroup = authorityGroupRepository.save(expectedAuthorityGroup);

        assertThat(actualAuthorityGroup, is(not(nullValue())));
        assertThat(actualAuthorityGroup.getId(), is(not(nullValue())));
        assertThat(actualAuthorityGroup.getCreatedBy(), is(expectedAuthorityGroup.getCreatedBy()));
        assertThat(actualAuthorityGroup.getName(), Is.is(expectedAuthorityGroup.getName()));
        assertThat(actualAuthorityGroup.getDescription(), Is.is(expectedAuthorityGroup.getDescription()));
        assertThat(actualAuthorityGroup.getEnabled(), is(expectedAuthorityGroup.getEnabled()));
    }

    private AuthorityGroup anAuthorityGroup() {
        return AuthorityGroup.anAuthorityGroup()
                .withId(1L)
                .withCreatedBy("SYSTEM_TEST")
                .withName("ROLE_TEST")
                .withDescription("DESCRIPTION_TEST")
                .withEnabled(TRUE)
                .build();
    }

}
