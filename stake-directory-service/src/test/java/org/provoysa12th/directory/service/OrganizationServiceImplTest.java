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
		organizationService.init();
	}

	@Test
	public void testFindById() throws Exception {
		Long id = 1234L;

		when(organizationRepository.findOne(id)).thenReturn(new Organization());

		Organization actual = organizationService.findById(id);
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindByUUID() throws Exception {
		UUID uuid = UUID.randomUUID();
		
		when(organizationRepository.findByPropertyValue("uuid", uuid)).thenReturn(new Organization());
		
		Organization actual = organizationService.findByUUID(uuid);
		assertThat(actual, is(notNullValue()));
	}
	
	@Test
	public void testCreateOrUpdate() throws Exception {
		Organization organization = new Organization();

		when(organizationRepository.saveEntity(organization)).thenReturn(organization);

		Organization actual = organizationService.createOrUpdate(organization);
		assertThat(actual, is(notNullValue()));
	}

}
