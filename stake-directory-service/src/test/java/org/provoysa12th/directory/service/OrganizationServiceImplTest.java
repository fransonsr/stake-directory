package org.provoysa12th.directory.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.repository.OrganizationRepository;

public class OrganizationServiceImplTest {

	private OrganizationServiceImpl organizationService;
	private OrganizationRepository organizationRepository;

	@Before
	public void setup() {
		organizationRepository = mock(OrganizationRepository.class);
		organizationService = new OrganizationServiceImpl();
		organizationService.organizationRepository = organizationRepository;
	}

	@Test
	public void testFindById() throws Exception {
		Long id = 1234L;

		when(organizationRepository.findOne(id)).thenReturn(new Organization());

		Organization actual = organizationService.findById(id);
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate_create() throws Exception {
		Organization organization = new Organization();

		when(organizationRepository.save(organization)).thenReturn(organization);

		Organization actual = organizationService.createOrUpdate(organization);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate_update() throws Exception {
		Organization organization = new Organization();
		organization.setNodeId(1234L);

		UUID uuid = UUID.randomUUID();
		organization.setUuid(uuid);

		when(organizationRepository.save(organization)).thenReturn(organization);

		Organization actual = organizationService.createOrUpdate(organization);
		assertThat(actual.getUuid(), is(equalTo(uuid)));
	}

}
