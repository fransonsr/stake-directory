package org.provoysa12th.directory.domain;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class OrganizationPositionTest {

	@Test
	public void testSORT_ASC() throws Exception {
		OrganizationPosition pos1 = new OrganizationPosition();
		OrganizationPosition pos2 = new OrganizationPosition();
		OrganizationPosition pos3 = new OrganizationPosition();

		pos1.setOrderIndex(5);
		pos2.setOrderIndex(1);
		pos3.setOrderIndex(3);

		List<OrganizationPosition> list = Arrays.asList(pos1, pos2, pos3);

		Collections.sort(list, OrganizationPosition.SORT_ASC);
		assertThat(list.get(0), is(pos2));
		assertThat(list.get(1), is(pos3));
		assertThat(list.get(2), is(pos1));
	}

	@Test
	public void testSORT_DESC() throws Exception {
		OrganizationPosition pos1 = new OrganizationPosition();
		OrganizationPosition pos2 = new OrganizationPosition();
		OrganizationPosition pos3 = new OrganizationPosition();

		pos1.setOrderIndex(5);
		pos2.setOrderIndex(1);
		pos3.setOrderIndex(3);

		List<OrganizationPosition> list = Arrays.asList(pos1, pos2, pos3);

		Collections.sort(list, OrganizationPosition.SORT_ASC);
		assertThat(list.get(0), is(pos1));
		assertThat(list.get(1), is(pos3));
		assertThat(list.get(2), is(pos2));
	}

}
