package org.provoysa12th.directory.business.templates;

import static org.provoysa12th.directory.business.templates.PositionTemplate.Type.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import org.provoysa12th.directory.business.templates.PositionTemplate.Type;

public enum OrganizationPositionsTemplate {
	STAKE_PRESIDENCY(STAKE_PRESIDENT, COUNCILOR_1, COUNCILOR_2, STAKE_CLERK, ASSISTANT_STAKE_CLERK, STAKE_SECRETARY, ASSISTANT_STAKE_SECRETARY),
	STAKE_RELIEF_SOCIETY(PRESIDENT, COUNCILOR_1, COUNCILOR_2, SECRETARY, ASSISTANT_SECRETARY, STAKE_R_S_SPECIALIST),
	STAKE_HIGH_COUNCIL(HIGH_COUNCILOR),
	BISHOPRIC(BISHOP, COUNCILOR_1, COUNCILOR_2, WARD_CLERK, ASSISTANT_WARD_CLERK, WARD_SECRETARY, ASSISTANT_WARD_SECRETARY),
	ELDERS_QUORUM(PRESIDENT, COUNCILOR_1, COUNCILOR_2, SECRETARY, ASSISTANT_SECRETARY),
	RELIEF_SOCIETY(PRESIDENT, COUNCILOR_1, COUNCILOR_2, SECRETARY, ASSISTANT_SECRETARY),
	COUNCIL(COUNCIL_CHAIR, COUNCIL_CO_CHAIR, COUNCIL_MEMBER);

	private Set<Type> positionTemplates;

	private OrganizationPositionsTemplate(Type... templates) {
		positionTemplates = EnumSet.copyOf(Arrays.asList(templates));
	}

	public Set<Type> getPositionTemplates() {
		return positionTemplates;
	}

	public void setPositionTemplates(Set<Type> positionTemplates) {
		this.positionTemplates = positionTemplates;
	}

}
