package org.provoysa12th.directory.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@TransactionConfiguration
@ContextConfiguration(classes = {ServiceComponentTestConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationServiceImplComponentTest {

	@Autowired
	OrganizationService organizationService;

	@Test
	public void testFindById() throws Exception {
		Organization organization = organizationService.createOrUpdate(new Organization());

		Organization actual = organizationService.findById(organization.getNodeId());
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindById_notFound() throws Exception {
		Organization actual = organizationService.findById(1234L);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testCreateOrUpdate_create() throws Exception {
		Organization organization = new Organization();

		Organization actual = organizationService.createOrUpdate(organization);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getNodeId(), is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate_update() throws Exception {
		Organization organization = new Organization();
		organization.setName("Test Organization");

		Organization createdOrg = organizationService.createOrUpdate(organization);

		Long nodeId = createdOrg.getNodeId();
		UUID uuid = createdOrg.getUuid();
		createdOrg.setName("Modified Test Organization");

		Organization actual = organizationService.createOrUpdate(createdOrg);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getNodeId(), is(equalTo(nodeId)));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
		assertThat(actual.getName(), is(equalTo("Modified Test Organization")));
	}
}
