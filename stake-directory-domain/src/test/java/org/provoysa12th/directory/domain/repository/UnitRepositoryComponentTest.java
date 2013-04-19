package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.Unit.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {RepositoryComponentTestConfiguration.class})
@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitRepositoryComponentTest {

	@Autowired
	UnitRepository unitRepository;

	@Test
	public void testSaveEntity_create() throws Exception {
		Unit actual = unitRepository.saveEntity(new Unit());
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(notNullValue()));
	}

	@Test
	public void testSaveEntity_update() throws Exception {
		Unit unit = new Unit();
		unit.setName("Test Unit");

		unit = unitRepository.saveEntity(unit);
		UUID uuid = unit.getUuid();
		assertThat(uuid, is(notNullValue()));

		unit.setName("Updated Unit");

		Unit actual = unitRepository.saveEntity(unit);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
		assertThat(actual.getName(), is("Updated Unit"));
	}

	@Test
	public void testFindByUuid() throws Exception {
		Unit unit = new Unit();

		unit = unitRepository.saveEntity(unit);
		UUID uuid = unit.getUuid();

		Unit actual = unitRepository.findByUuid(uuid);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindByName() throws Exception {
		Unit unit = new Unit();
		unit.setName("Unit for FindByName");

		unit = unitRepository.saveEntity(unit);
		UUID uuid = unit.getUuid();

		List<Unit> actual = unitRepository.findByName("Unit for FindByName");
		assertThat(actual, is(notNullValue()));

		Unit actualUnit = actual.get(0);
		assertThat(actualUnit.getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindByNameLike() throws Exception {
		Unit unit = new Unit();
		unit.setName("Test Unit for FindByNameLike");

		unit = unitRepository.saveEntity(unit);
		UUID uuid = unit.getUuid();

		String regex = ".*FindByNameLike";
		List<Unit> actual = unitRepository.findByNameLike(regex);
		assertThat(actual, is(notNullValue()));

		Unit actualUnit = actual.get(0);
		assertThat(actualUnit.getUuid(), is(equalTo(uuid)));
	}

	@Test
	public void testFindByType() throws Exception {
		Unit ward = new Unit();
		ward.setType(Type.Ward);

		ward = unitRepository.saveEntity(ward);

		Unit stake = new Unit();
		stake.setType(Type.Stake);

		stake = unitRepository.saveEntity(stake);
		UUID uuid = stake.getUuid();

		List<Unit> actual = unitRepository.findByType(Type.Stake);
		assertThat(actual.size(), is(1));

		Unit actualUnit = actual.get(0);
		assertThat(actualUnit.getUuid(), is(equalTo(uuid)));
	}
}
