package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.Position.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {RepositoryComponentTestConfiguration.class})
@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class PositionRepositoryComponentTest {

	@Autowired
	PositionRepository positionRepository;

	@Test
	public void testSaveEntity_create() throws Exception {
		Position actual = positionRepository.saveEntity(new Position());

		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testSaveEntity_update() throws Exception {
		Position position = new Position();
		position.setName("Test Position");

		position = positionRepository.saveEntity(position);
		UUID uuid = position.getUuid();
		position.setName("Updated Position");

		Position actual = positionRepository.saveEntity(position);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
		assertThat(actual.getName(), is("Updated Position"));
	}

	@Test
	public void testFindByUuid() throws Exception {
		Position position = new Position();
		position.setName("Test Position");

		position = positionRepository.saveEntity(position);
		UUID uuid = position.getUuid();

		Position actual = positionRepository.findByUuid(uuid);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByName() throws Exception {
		Position position = new Position();
		position.setName("Position for FindByName");

		position = positionRepository.saveEntity(position);
		UUID uuid = position.getUuid();

		List<Position> actual = positionRepository.findAllByName(position.getName());
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByNameLike() throws Exception {
		Position position = new Position();
		position.setName("Position for FindByNameLike");

		position = positionRepository.saveEntity(position);
		UUID uuid = position.getUuid();

		String regex = ".*Like";
		List<Position> actual = positionRepository.findAllByNameLike(regex);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindAllByType() throws Exception {
		Position position = new Position();
		position.setType(Type.Clerk);

		position = positionRepository.saveEntity(position);
		UUID uuid = position.getUuid();

		List<Position> actual = positionRepository.findAllByType(position.getType());
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), is(1));
		assertThat(actual.get(0).getUuid(), is(equalTo(uuid)));
	}
}
