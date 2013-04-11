package org.provoysa12th.directory.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.business.templates.OrganizationPositionsTemplate;
import org.provoysa12th.directory.business.templates.OrganizationTemplate;
import org.provoysa12th.directory.business.templates.PositionTemplate;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.OrganizationPosition;
import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.Unit.Type;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(classes = {ServiceComponentTestConfiguration.class, TheTest.AspectConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
public class TheTest {

	private static final Logger log = Logger.getLogger(TheTest.class);


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
	public void testLoad() throws Exception {
		createUnit("Provo Utah YSA 12th Stake", 518468, Unit.Type.Stake);

		List<Unit> units = unitService.findAll();
		for(Unit unit : units) {
			log.info("Found unit: " + unit);
			ArrayList<UnitOrganization> unitOrganizations = new ArrayList<UnitOrganization>(unit.getUnitOrganizations());
			Collections.sort(unitOrganizations, UnitOrganization.SORT_ASC);
			for(UnitOrganization unitOrg : unitOrganizations) {
				Organization org = unitOrg.getOrganization();
				org = organizationService.findById(org.getNodeId());
				log.info("\tFound organization: " + org);
				List<OrganizationPosition> organizationPositions = new ArrayList<OrganizationPosition>(org.getOrganizationPositions());
				Collections.sort(organizationPositions, OrganizationPosition.SORT_ASC);
				for(OrganizationPosition orgPosition : organizationPositions) {
					Position position = orgPosition.getPosition();
					position = positionService.findById(position.getNodeId());
					log.info("\t\tFound position: " + position);
				}
			}
		}
	}

	private void createUnit(String name, int unitNumber, Type type) {
		Unit unit = new Unit();
		unit.setName(name);
		unit.setUnitNumber(unitNumber);
		unit.setType(type);

		log.info("Creating unit: " + unit);
		unit = unitService.createOrUpdate(unit);

		createOrganizations(unit);
	}

	private void createOrganizations(Unit unit) {
		Set<OrganizationTemplate.Type> stakeOrgs = OrganizationTemplate.Type.forUnitType(unit.getType());

		int order = 0;

		for(OrganizationTemplate.Type stakeOrg : stakeOrgs) {
			OrganizationTemplate template = stakeOrg.getTemplate();
			Organization org = new Organization();
			org.setName(template.getName());
			org.setType(template.getOrganizationType());

			log.info("\tCreating organization: " + org);
			org = organizationService.createOrUpdate(org);

			UnitOrganization unitOrg = unitService.addOrganization(unit, org, template.isPresiding(), order++);
			unit = unitOrg.getUnit();
			org = unitOrg.getOrganization();

			unitService.createOrUpdate(unit);

			createPositions(org, template.getPositionsTemplate());
		}
	}

	private void createPositions(Organization org, OrganizationPositionsTemplate positionsTemplate) {
		Set<PositionTemplate.Type> positionTemplates = positionsTemplate.getPositionTemplates();

		int order = 0;

		for(PositionTemplate.Type postionTemplateType : positionTemplates) {
			PositionTemplate template = postionTemplateType.getTemplate();
			Position position = new Position();
			position.setName(template.getName());
			position.setType(template.getPositionType());

			log.info("\t\tCreating position: " + position);

			position = positionService.createOrUpdate(position);

			OrganizationPosition orgPosition = organizationService.addPosition(org, position, template.isPresiding(), order++);
			org = orgPosition.getOrganization();
		}
	}
}
