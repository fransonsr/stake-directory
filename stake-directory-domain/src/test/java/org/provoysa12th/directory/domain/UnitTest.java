package org.provoysa12th.directory.domain;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;

public class UnitTest {

	@Test
	public void testPresidingOrganization() throws Exception {
		Organization org1 = new Organization();
		Organization org2 = new Organization();
		Organization org3 = new Organization();

		org1.setName("org1");
		org2.setName("org2");
		org3.setName("org3");

		Unit unit = new Unit();
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org1, false, 5));
		UnitOrganization presidingOrganization = new UnitOrganization(unit, org2, false, 7);
		presidingOrganization.setPresiding(true);
		unit.getUnitOrganizations().add(presidingOrganization);
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org3, false, 1));

		Organization actual = unit.presidingOrganization();
		assertThat(actual, is(org2));
	}

	@Test
	public void testPresidingOrganization_none() throws Exception {
		Organization org1 = new Organization();
		Organization org2 = new Organization();
		Organization org3 = new Organization();

		org1.setName("org1");
		org2.setName("org2");
		org3.setName("org3");

		Unit unit = new Unit();
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org1, false, 5));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org2, false, 7));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org3, false, 1));

		Organization actual = unit.presidingOrganization();
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testOrganizations() throws Exception {
		Organization org1 = new Organization();
		Organization org2 = new Organization();
		Organization org3 = new Organization();

		org1.setName("org1");
		org2.setName("org2");
		org3.setName("org3");

		Unit unit = new Unit();
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org1, false, 5));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org2, false, 7));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org3, false, 1));

		List<Organization> organizations = unit.organizations();
		assertThat(organizations, is(notNullValue()));
		assertThat(organizations, is(not(empty())));

		assertThat(organizations.get(0), is(org3));
		assertThat(organizations.get(1), is(org1));
		assertThat(organizations.get(2), is(org2));
	}

	@Test
	public void testOrganizationsComparator() throws Exception {
		Organization org1 = new Organization();
		Organization org2 = new Organization();
		Organization org3 = new Organization();

		org1.setName("org1");
		org2.setName("org2");
		org3.setName("org3");

		Unit unit = new Unit();
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org1, false, 5));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org2, false, 7));
		unit.getUnitOrganizations().add(new UnitOrganization(unit, org3, false, 1));

		List<Organization> organizations = unit.organizations(UnitOrganization.SORT_DESC);
		assertThat(organizations, is(notNullValue()));
		assertThat(organizations, is(not(empty())));

		assertThat(organizations.get(0), is(org2));
		assertThat(organizations.get(1), is(org1));
		assertThat(organizations.get(2), is(org3));
	}

}
