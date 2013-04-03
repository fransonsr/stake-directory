package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Unit;
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
}
