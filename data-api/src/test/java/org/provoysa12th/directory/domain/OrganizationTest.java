package org.provoysa12th.directory.domain;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.provoysa12th.directory.domain.Position.Type;

public class OrganizationTest {

	@Test
	public void testPositions() throws Exception {
		Organization test = new Organization();

		Position p1 = new Position();
		Position p2 = new Position();
		Position p3 = new Position();

		p1.setName("Elders Quorum President");
		p1.setType(Type.President);

		p2.setName("Elders Quorum 1st Counselor");
		p2.setType(Type.Counselor_1st);

		p3.setName("Elders Quorum 2nd Counselor");
		p3.setType(Type.Counselor_2nd);

		test.getOrganizationPositions().add(new OrganizationPosition(test, p1, true, 1));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p2, false, 2));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p3, false, 3));

		List<Position> positions = test.positions();
		assertThat(positions, is(notNullValue()));
		assertThat(positions.get(0), is(p1));
		assertThat(positions.get(1), is(p2));
		assertThat(positions.get(2), is(p3));
	}

	@Test
	public void testPositions_comparator() throws Exception {
		Organization test = new Organization();

		Position p1 = new Position();
		Position p2 = new Position();
		Position p3 = new Position();

		p1.setName("Elders Quorum President");
		p1.setType(Type.President);

		p2.setName("Elders Quorum 1st Counselor");
		p2.setType(Type.Counselor_1st);

		p3.setName("Elders Quorum 2nd Counselor");
		p3.setType(Type.Counselor_2nd);

		test.getOrganizationPositions().add(new OrganizationPosition(test, p1, true, 1));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p2, false, 2));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p3, false, 3));

		List<Position> positions = test.positions(OrganizationPosition.SORT_ASC);
		assertThat(positions, is(notNullValue()));
		assertThat(positions.get(0), is(p1));
		assertThat(positions.get(1), is(p2));
		assertThat(positions.get(2), is(p3));
	}

	@Test
	public void testPresidingPosition() throws Exception {
		Organization test = new Organization();

		Position p1 = new Position();
		Position p2 = new Position();
		Position p3 = new Position();

		p1.setName("Elders Quorum President");
		p1.setType(Type.President);

		p2.setName("Elders Quorum 1st Counselor");
		p2.setType(Type.Counselor_1st);

		p3.setName("Elders Quorum 2nd Counselor");
		p3.setType(Type.Counselor_2nd);

		test.getOrganizationPositions().add(new OrganizationPosition(test, p1, true, 1));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p2, false, 2));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p3, false, 3));

		Position presiding = test.presidingPosition();
		assertThat(presiding, is(p1));
	}

	@Test
	public void testPresidingPosition_none() throws Exception {
		Organization test = new Organization();

		Position p1 = new Position();
		Position p2 = new Position();
		Position p3 = new Position();

		p1.setName("Elders Quorum President");
		p1.setType(Type.President);

		p2.setName("Elders Quorum 1st Counselor");
		p2.setType(Type.Counselor_1st);

		p3.setName("Elders Quorum 2nd Counselor");
		p3.setType(Type.Counselor_2nd);

		test.getOrganizationPositions().add(new OrganizationPosition(test, p1, false, 1));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p2, false, 2));
		test.getOrganizationPositions().add(new OrganizationPosition(test, p3, false, 3));

		Position presiding = test.presidingPosition();
		assertThat(presiding, is(nullValue()));
	}
}
