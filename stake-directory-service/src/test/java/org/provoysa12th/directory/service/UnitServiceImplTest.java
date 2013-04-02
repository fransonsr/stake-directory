package org.provoysa12th.directory.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.provoysa12th.directory.domain.repository.UnitRepository;

public class UnitServiceImplTest {

	private UnitServiceImpl unitService;
	private UnitRepository unitRepository;
	private OrganizationService organizationService;

	@Before
	public void setup() {
		unitRepository = mock(UnitRepository.class);
		organizationService = mock(OrganizationService.class);

		unitService = new UnitServiceImpl();
		unitService.unitRepository = unitRepository;
		unitService.organizationService = organizationService;
	}

	@Test
	public void testFindById() throws Exception {
		when(unitRepository.findOne(1234L)).thenReturn(new Unit());

		Unit actual = unitService.findById(1234L);

		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindByUnitNumber() throws Exception {
		Integer unitNumber = 1234;
		when(unitRepository.findByPropertyValue(eq("unitNumber"), eq(unitNumber))).thenReturn(new Unit());

		Unit actual = unitService.findByUnitNumber(unitNumber);

		assertThat(actual, is(notNullValue()));

	}

	@Test
	public void testCreateOrUpdate() throws Exception {
		Unit unit = new Unit();

		unitService.createOrUpdate(unit);
		verify(unitRepository).save(unit);
	}

	@Test
	public void testAddOrganization() throws Exception {
		Unit unit = new Unit();
		Organization organization = new Organization();

		when(organizationService.createOrUpdate(organization)).thenReturn(organization);

		unitService.addOrganization(unit, organization);

		Set<UnitOrganization> organizations = unit.getUnitOrganizations();
		assertThat(organizations, is(notNullValue()));
		assertThat(organizations, is(not(empty())));

		verify(unitRepository).save(unit);
	}
}
