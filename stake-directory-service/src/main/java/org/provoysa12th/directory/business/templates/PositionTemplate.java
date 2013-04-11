package org.provoysa12th.directory.business.templates;

import org.provoysa12th.directory.domain.Organization;
import org.provoysa12th.directory.domain.Position;


public class PositionTemplate {

	public static enum Type {
		STAKE_PRESIDENT(new PositionTemplate("Stake President", Organization.Type.Presidency, Position.Type.President, true)),
		STAKE_RELIEF_SOCIETY_PRESIDENT(new PositionTemplate("Stake Relief Society President", Organization.Type.Presidency, Position.Type.President, true)),
		STAKE_CLERK(new PositionTemplate("Stake Clerk", Organization.Type.Presidency, Position.Type.Clerk, false)),
		ASSISTANT_STAKE_CLERK(new PositionTemplate("Assistant Stake Clerk", Organization.Type.Presidency, Position.Type.AssistantClerk, false)),
		STAKE_SECRETARY(new PositionTemplate("Stake Executive Secretary", Organization.Type.Presidency, Position.Type.Secretary, false)),
		ASSISTANT_STAKE_SECRETARY(new PositionTemplate("Assistant Stake Executive Secretary", Organization.Type.Presidency, Position.Type.AssistantSecretary, false)),
		HIGH_COUNCILOR_1(new PositionTemplate("High Councilor 1", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_2(new PositionTemplate("High Councilor 2", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_3(new PositionTemplate("High Councilor 3", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_4(new PositionTemplate("High Councilor 4", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_5(new PositionTemplate("High Councilor 5", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_6(new PositionTemplate("High Councilor 6", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_7(new PositionTemplate("High Councilor 7", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_8(new PositionTemplate("High Councilor 8", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_9(new PositionTemplate("High Councilor 9", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_10(new PositionTemplate("High Councilor 10", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_11(new PositionTemplate("High Councilor 11", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		HIGH_COUNCILOR_12(new PositionTemplate("High Councilor 12", Organization.Type.HighCouncil, Position.Type.HighCouncilor, false)),
		STAKE_R_S_SPECIALIST(new PositionTemplate("Stake Relief Society Specialist", Organization.Type.Presidency, Position.Type.ReliefSocietySpecialist, false)),
		BISHOP(new PositionTemplate("Bishop", Organization.Type.Bishopric, Position.Type.Bishop, true)),
		BISHOP_COUNCILOR_1(new PositionTemplate("1st Councilor", Organization.Type.Bishopric, Position.Type.Counselor_1st, false)),
		BISHOP_COUNCILOR_2(new PositionTemplate("2nd Councilor", Organization.Type.Bishopric, Position.Type.Counselor_2nd, false)),
		WARD_CLERK(new PositionTemplate("Ward Clerk", Organization.Type.Bishopric, Position.Type.Clerk, false)),
		ASSISTANT_WARD_CLERK(new PositionTemplate("Assistant Ward Clerk", Organization.Type.Bishopric, Position.Type.AssistantClerk, false)),
		WARD_SECRETARY(new PositionTemplate("Ward Executive Secretary", Organization.Type.Bishopric, Position.Type.Secretary, false)),
		ASSISTANT_WARD_SECRETARY(new PositionTemplate("Assistant Ward Executive Secretary", Organization.Type.Bishopric, Position.Type.AssistantSecretary, false)),
		PRESIDENT(new PositionTemplate("President", Organization.Type.Presidency, Position.Type.President, true)),
		COUNCILOR_1(new PositionTemplate("1st Councilor", Organization.Type.Presidency, Position.Type.Counselor_1st, false)),
		COUNCILOR_2(new PositionTemplate("2nd Councilor", Organization.Type.Presidency, Position.Type.Counselor_2nd, false)),
		SECRETARY(new PositionTemplate("Secretary", Organization.Type.Presidency, Position.Type.Secretary, false)),
		ASSISTANT_SECRETARY(new PositionTemplate("Assistant Secretary", Organization.Type.Presidency, Position.Type.AssistantSecretary, false)),
		COUNCIL_CHAIR(new PositionTemplate("Council Chair", Organization.Type.Council, Position.Type.CouncilChair, true)),
		COUNCIL_CO_CHAIR(new PositionTemplate("Council Co-Chair", Organization.Type.Council, Position.Type.CouncilCoChair, false)),
		COUNCIL_MEMBER(new PositionTemplate("Council Member", Organization.Type.Council, Position.Type.CouncilMember, false));

		private final PositionTemplate template;

		private Type(PositionTemplate template) {
			this.template = template;
		}

		public PositionTemplate getTemplate() {
			return template;
		}
	}

	private final String name;
	private final Organization.Type organizationType;
	private final Position.Type positionType;
	private final boolean presiding;

	public PositionTemplate( String name, Organization.Type organizationType, Position.Type positionType, boolean presiding) {
		this.name = name;
		this.organizationType = organizationType;
		this.positionType = positionType;
		this.presiding = presiding;
	}

	public String getName() {
		return name;
	}

	public Organization.Type getOrganizationType() {
		return organizationType;
	}

	public Position.Type getPositionType() {
		return positionType;
	}

	public boolean isPresiding() {
		return presiding;
	}

}
