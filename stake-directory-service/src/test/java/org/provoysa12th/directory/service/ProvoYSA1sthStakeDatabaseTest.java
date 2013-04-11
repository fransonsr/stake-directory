package org.provoysa12th.directory.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.business.templates.ProvoYSA12thStakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(classes = {ServiceComponentTestConfiguration.class, ProvoYSA1sthStakeDatabaseTest.AspectConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
public class ProvoYSA1sthStakeDatabaseTest {

	@Configuration
	@EnableAspectJAutoProxy()
	@ComponentScan({"org.provoysa12th.directory.aspect"})
	public static class AspectConfiguration {
	}

	@Autowired
	private UnitService unitService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private PositionService positionService;

	@Test
	public void testLoadDatabase() throws Exception {
		ProvoYSA12thStakeDatabase provoYSA12thStakeDatabase = new ProvoYSA12thStakeDatabase(unitService, organizationService, positionService);
		provoYSA12thStakeDatabase.loadDatabase();
	}
}
