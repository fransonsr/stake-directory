package org.provoysa12th.directory.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Represents and individual position for an Organization.
 * <p>
 * Business key: name, type, organization
 * </p>
 * @author FransonSR
 *
 */
@NodeEntity
public class Position extends BaseEntity {

	public static enum Type {
		President,
		Bishop,
		Counselor_1st,
		Counselor_2nd,
		Secretary,
		AssistantSecretary,
		Clerk,
		AssistantClerk,
		CouncilChair,
		CouncilCoChair,
		CouncilMember,
		HighCouncilor,
		ReliefSocietySpecialist;
	}

	private String name;
	private Type type;

	@Fetch
	private Organization organization;

	@RelatedToVia(type = OrganizationPosition.TYPE_ORGANIZATION_POSITION, direction = Direction.INCOMING)
	private OrganizationPosition organizationPosition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public OrganizationPosition getOrganizationPosition() {
		return organizationPosition;
	}

	public void setOrganizationPosition(OrganizationPosition organizationPosition) {
		this.organizationPosition = organizationPosition;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(name)
			.append(organization)
			.append(type)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		return new EqualsBuilder()
			.append(name, other.getName())
			.append(organization, other.getOrganization())
			.append(type, other.getType())
			.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Position [name=").append(name)
				.append(", type=").append(type)
				.append(", organization=").append(organization)
				.append(", id=").append(getNodeId())
				.append("]");
		return builder.toString();
	}


}
