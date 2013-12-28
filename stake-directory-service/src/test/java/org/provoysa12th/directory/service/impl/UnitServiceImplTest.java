package org.provoysa12th.directory.service.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.provoysa12th.directory.domain.repository.UnitRepository;
import org.provoysa12th.directory.service.OrganizationService;
import org.provoysa12th.directory.service.impl.UnitServiceImpl;
import org.springframework.data.neo4j.conversion.EndResult;

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
		unitService.init();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindAll() throws Exception {
		EndResult<Unit> endResult = mock(EndResult.class);
		List<Unit> list = new ArrayList<Unit>();

		when(unitRepository.findAll()).thenReturn(endResult);
		when(endResult.as(List.class)).thenReturn(list);

		List<Unit> actual = unitService.findAll();
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindById() throws Exception {
		when(unitRepository.findOne(1234L)).thenReturn(new Unit());

		Unit actual = unitService.findById(1234L);

		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindByUUID() throws Exception {
		UUID uuid = UUID.randomUUID();

		when(unitRepository.findByPropertyValue("uuid", uuid)).thenReturn(new Unit());

		Unit actual = unitService.findByUUID(uuid);
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate() throws Exception {
		Unit unit = new Unit();

		when(unitRepository.saveEntity(unit)).thenReturn(unit);

		Unit actual = unitService.createOrUpdate(unit);
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
	public void testAddOrganization() throws Exception {
		Unit unit = new Unit();
		Organization organization = new Organization();

		when(organizationService.createOrUpdate(organization)).thenReturn(organization);

		unitService.addOrganization(unit, organization, false, -1);

		Set<UnitOrganization> organizations = unit.getUnitOrganizations();
		assertThat(organizations, is(notNullValue()));
		assertThat(organizations, is(not(empty())));

		assertThat(organization.getUnit(), is(unit));
		assertThat(organization.getUnitOrganization().getUnit(), is(unit));

		verify(unitRepository).saveEntity(unit);
	}
}
