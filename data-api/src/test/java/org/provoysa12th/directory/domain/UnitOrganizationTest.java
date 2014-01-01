package org.provoysa12th.directory.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class UnitOrganizationTest {

	@Test
	public void testSORT_ASC() throws Exception {
		UnitOrganization org1 = new UnitOrganization();
		UnitOrganization org2 = new UnitOrganization();
		UnitOrganization org3 = new UnitOrganization();

		org1.setOrderIndex(5);
		org2.setOrderIndex(7);
		org3.setOrderIndex(1);

		List<UnitOrganization> list  = Arrays.asList(org1, org2, org3);
		assertThat(org1, is(list.get(0)));
		assertThat(org2, is(list.get(1)));
		assertThat(org3, is(list.get(2)));

		Collections.sort(list, UnitOrganization.SORT_ASC);

		assertThat(org3, is(list.get(0)));
		assertThat(org1, is(list.get(1)));
		assertThat(org2, is(list.get(2)));
	}

	@Test
	public void testSORT_DESC() throws Exception {
		UnitOrganization org1 = new UnitOrganization();
		UnitOrganization org2 = new UnitOrganization();
		UnitOrganization org3 = new UnitOrganization();

		org1.setOrderIndex(5);
		org2.setOrderIndex(7);
		org3.setOrderIndex(1);

		List<UnitOrganization> list  = Arrays.asList(org1, org2, org3);
		assertThat(org1, is(list.get(0)));
		assertThat(org2, is(list.get(1)));
		assertThat(org3, is(list.get(2)));

		Collections.sort(list, UnitOrganization.SORT_DESC);

		assertThat(org2, is(list.get(0)));
		assertThat(org1, is(list.get(1)));
		assertThat(org3, is(list.get(2)));
	}

}
