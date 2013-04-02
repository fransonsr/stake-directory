package org.provoysa12th.directory.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Represents a Church unit (i.e., stake or ward).
 * <p>
 * Business key: unitNumber
 * </p>
 * @author FransonSR
 *
 */
@NodeEntity
public class Unit {

	public static enum Type {
		Stake,
		Ward;
	}

	@GraphId
	private Long nodeId;

	@NotNull
	private String name;
	@Indexed
	private Integer unitNumber;
	private Type type;

	@RelatedToVia(type = UnitOrganization.TYPE_UNIT_ORGANIZATION, direction = Direction.OUTGOING)
	private Set<UnitOrganization> organizations = new HashSet<UnitOrganization>();

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(Integer unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<UnitOrganization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<UnitOrganization> organizations) {
		this.organizations = organizations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((unitNumber == null) ? 0 : unitNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (unitNumber == null) {
			if (other.unitNumber != null)
				return false;
		} else if (!unitNumber.equals(other.unitNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Unit [name=").append(name)
				.append(", unitNumber=").append(unitNumber)
				.append(", unitType=").append(type)
				.append("]");
		return builder.toString();
	}
}
