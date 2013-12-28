package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Organization.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {RepositoryComponentTestConfiguration.class})
@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationRepositoryComponentTest {

	@Autowired
	OrganizationRepository organizationRepository;

	@Test
	public void testSaveEntity_create() throws Exception {
		Organization org = new Organization();

		Organization actual = organizationRepository.saveEntity(org);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testSaveEntity_update() throws Exception {
		Organization org = new Organization();
		org.setName("Test Org");

		org = organizationRepository.saveEntity(org);
		UUID uuid = org.getUuid();

		assertThat(org, is(notNullValue()));
		assertThat(uuid, is(notNullValue()));

		org.setName("Updated Name");

		Organization actual = organizationRepository.saveEntity(org);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
		assertThat(actual.getName(), is("Updated Name"));
	}

	@Test
	public void testFindByUuid() throws Exception {
		Organization org = new Organization();

		org = organizationRepository.saveEntity(org);
		UUID uuid = org.getUuid();

		Organization actual = organizationRepository.findByUuid(uuid);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByName() throws Exception {
		Organization org = new Organization();
		org.setName("Organization for FindAllByName");

		org = organizationRepository.saveEntity(org);
		UUID uuid = org.getUuid();

		List<Organization> actual = organizationRepository.findAllByName(org.getName());
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByNameLike() throws Exception {
		Organization org = new Organization();
		org.setName("Organization for FindAllByNameLike");

		org = organizationRepository.saveEntity(org);
		UUID uuid = org.getUuid();

		String regex = ".*Like";
		List<Organization> actual = organizationRepository.findAllByNameLike(regex);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByType() throws Exception {
		Organization org = new Organization();
		org.setType(Type.Presidency);

		org = organizationRepository.saveEntity(org);
		UUID uuid = org.getUuid();

		List<Organization> actual = organizationRepository.findAllByType(org.getType());
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}
}
