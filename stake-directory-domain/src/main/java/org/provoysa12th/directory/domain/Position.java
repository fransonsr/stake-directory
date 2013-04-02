package org.provoysa12th.directory.domain;

import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
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
public class Position {

	public static enum Type {
		President,
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
		HighCouncilorRepresentative,
		ReliefSocietySpecialist;
	}

	@GraphId
	private Long nodeId;

	@Indexed
	private UUID uuid;
	private String name;
	private Type type;
	private Organization organization;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

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
}
