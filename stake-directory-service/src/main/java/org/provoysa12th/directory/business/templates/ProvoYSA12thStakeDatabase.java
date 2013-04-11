package org.provoysa12th.directory.business.templates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.OrganizationPosition;
import org.provoysa12th.directory.domain.Position;
import org.provoysa12th.directory.domain.Unit;
import org.provoysa12th.directory.domain.UnitOrganization;
import org.provoysa12th.directory.service.OrganizationService;
import org.provoysa12th.directory.service.PositionService;
import org.provoysa12th.directory.service.UnitService;

public class ProvoYSA12thStakeDatabase {

	private static final Logger log = Logger.getLogger(ProvoYSA12thStakeDatabase.class);

	private UnitService unitService;
	private OrganizationService organizationService;
	private PositionService positionService;

	public ProvoYSA12thStakeDatabase(UnitService unitService, OrganizationService organizationService, PositionService positionService) {
		this.unitService = unitService;
		this.organizationService = organizationService;
		this.positionService = positionService;
	}

	public void loadDatabase() {
		for(UnitTemplate.Type type : UnitTemplate.Type.values()) {
			createUnit(type.getTemplate());
		}

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

	private void createUnit(UnitTemplate template) {
		Unit unit = new Unit();
		unit.setName(template.getName());
		unit.setUnitNumber(template.getUnitNumber());
		unit.setType(template.getType());

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