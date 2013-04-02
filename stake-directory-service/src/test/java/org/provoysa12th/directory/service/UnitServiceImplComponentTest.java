package org.provoysa12th.directory.service;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.Unit.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@TransactionConfiguration
@ContextConfiguration(classes = {ServiceComponentTestConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitServiceImplComponentTest {

	@Autowired
	UnitService unitService;

	@Test
	public void testFindById() throws Exception {
		Unit unit = unitService.createOrUpdate(new Unit());

		Unit actual = unitService.findById(unit.getNodeId());
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindById_notFound() throws Exception {
		Unit actual = unitService.findById(1234L);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testFindByUnitNumber() throws Exception {
		Unit newUnit = new Unit();
		newUnit.setUnitNumber(1234);

		unitService.createOrUpdate(newUnit);

		Unit actual = unitService.findByUnitNumber(1234);
		assertThat(actual, is(notNullValue()));
	}

	@Test
	public void testFindByUnitNumber_notFound() throws Exception {
		Unit actual = unitService.findByUnitNumber(3456);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testCreateOrSave() throws Exception {
		Unit newUnit = new Unit();
		newUnit.setName("Test Unit");
		newUnit.setType(Type.Ward);
		newUnit.setUnitNumber(1234);

		Unit actual = unitService.createOrUpdate(newUnit);
		assertThat(actual, is(notNullValue()));
		assertThat(actual, equalTo(newUnit));
	}
}
