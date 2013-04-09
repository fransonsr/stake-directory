package org.provoysa12th.directory.business.templates;

import java.util.EnumSet;
import java.util.Set;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Unit;

public class OrganizationTemplate {

	public static enum Type {
		STAKE_PRESIDENCY(new OrganizationTemplate("Stake Presidency", Unit.Type.Stake, Organization.Type.Presidency, true, OrganizationPositionsTemplate.STAKE_PRESIDENCY)),
		STAKE_RELIEF_SOCIETY_PRESIDENCY(new OrganizationTemplate("Stake Relief Society", Unit.Type.Stake, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.STAKE_RELIEF_SOCIETY)),
		STAKE_HIGH_COUNCIL(new OrganizationTemplate("Stake High Council", Unit.Type.Stake, Organization.Type.HighCouncil, false,  OrganizationPositionsTemplate.STAKE_HIGH_COUNCIL)),
		WARD_BISHOPRIC(new OrganizationTemplate("Bishopric", Unit.Type.Ward, Organization.Type.Bishopric, true,  OrganizationPositionsTemplate.BISHOPRIC)),
		WARD_ELDERS_1(new OrganizationTemplate("Elders Quorum 1", Unit.Type.Ward, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.ELDERS_QUORUM)),
		WARD_ELDERS_2(new OrganizationTemplate("Elders Quorum 2", Unit.Type.Ward, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.ELDERS_QUORUM)),
		WARD_RS_1(new OrganizationTemplate("Relief Society 1", Unit.Type.Ward, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.RELIEF_SOCIETY)),
		WARD_RS_2(new OrganizationTemplate("Relief Society 2", Unit.Type.Ward, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.RELIEF_SOCIETY)),
		WARD_RS_3(new OrganizationTemplate("Relief Society 3", Unit.Type.Ward, Organization.Type.Presidency, false,  OrganizationPositionsTemplate.RELIEF_SOCIETY)),
		WARD_COUNCIL_INSTITUTE(new OrganizationTemplate("Institute Council", Unit.Type.Ward, Organization.Type.Council, false,  OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_H_E(new OrganizationTemplate("Home Evening Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_H_S(new OrganizationTemplate("Humanitarian Service Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_T_S_W(new OrganizationTemplate("Temporal and Spiritual Welfare Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_T_F_H(new OrganizationTemplate("Temple and Family History Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_M_F(new OrganizationTemplate("Missionary and Fellowship Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_MUSIC(new OrganizationTemplate("Music Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_G_T_L(new OrganizationTemplate("Gospel Teaching and Leadership Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_FRIENDSHIP(new OrganizationTemplate("Friendshiop Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL)),
		WARD_COUNCIL_PUBLICITY(new OrganizationTemplate("Publicity Council", Unit.Type.Ward, Organization.Type.Council, false, OrganizationPositionsTemplate.COUNCIL));

		private final OrganizationTemplate template;

		private Type(OrganizationTemplate template) {
			this.template = template;
		}

		public OrganizationTemplate getTemplate() {
			return template;
		}

		public static Set<Type> forUnitType(Unit.Type unitType) {
			Set<Type> set = EnumSet.noneOf(Type.class);

			for(Type type : Type.values()) {
				if(type.getTemplate().getUnitType().equals(unitType)) {
					set.add(type);
				}
			}

			return set;
		}
	}

	private final String name;
	private final Unit.Type unitType;
	private final Organization.Type organizationType;
	private final OrganizationPositionsTemplate positionsTemplate;
	private final boolean presiding;

	public OrganizationTemplate(String name, Unit.Type unitType, Organization.Type organizationType, boolean presiding, OrganizationPositionsTemplate template) {
		this.name = name;
		this.unitType = unitType;
		this.organizationType = organizationType;
		this.presiding = presiding;
		this.positionsTemplate = template;
	}

	public String getName() {
		return name;
	}

	public Unit.Type getUnitType() {
		return unitType;
	}

	public Organization.Type getOrganizationType() {
		return organizationType;
	}

	public boolean isPresiding() {
		return presiding;
	}

	public OrganizationPositionsTemplate getPositionsTemplate() {
		return positionsTemplate;
	}

}