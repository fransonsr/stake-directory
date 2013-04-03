package org.provoysa12th.directory.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@TransactionConfiguration
@ContextConfiguration(classes = {ServiceComponentTestConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PositionServiceComponentTest {

	@Autowired
	PositionService positionService;

	@Test
	public void testFindById() throws Exception {
		Position position = positionService.createOrUpdate(new Position());

		Long id = position.getNodeId();

		Position actual = positionService.findById(id);
		assertThat(actual, is(equalTo(position)));
	}

	@Test
	public void testFindById_notFound() throws Exception {
		Position actual = positionService.findById(1234L);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testFindByUUID() throws Exception {
		Position position = positionService.createOrUpdate(new Position());

		UUID uuid = position.getUuid();

		Position actual = positionService.findByUUID(uuid);
		assertThat(actual, is(equalTo(position)));
	}

	@Test
	public void testFindByUUID_notFound() throws Exception {
		Position actual = positionService.findByUUID(UUID.randomUUID());
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testCreateOrUpdate_create() throws Exception {
		Position actual = positionService.createOrUpdate(new Position());

		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testCreateOrUpdate_update() throws Exception {
		Position position = positionService.createOrUpdate(new Position());

		position.setName("Updated Position");

		Position actual = positionService.createOrUpdate(position);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getName(), is("Updated Position"));
	}
}
