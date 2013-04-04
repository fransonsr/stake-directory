package org.provoysa12th.directory.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.neo4j.annotation.NodeEntity;

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
	private Organization organization;

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
				.append("]");
		return builder.toString();
	}


}
