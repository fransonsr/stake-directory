package org.provoysa12th.directory.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
	public void testSave() throws Exception {
		Unit unit = new Unit();
		unit.setType(Type.Ward);

		Unit savedUnit = unitRepository.save(unit);

		Unit actual = unitRepository.findOne(savedUnit.getNodeId());
		assertThat(actual, is(notNullValue()));
	}

}
