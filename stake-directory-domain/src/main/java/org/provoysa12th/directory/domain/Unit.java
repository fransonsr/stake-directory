package org.provoysa12th.directory.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

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

	private String name;
	@Indexed
	private int unitNumber;
	private Type unitType;

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

	public int getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Type getUnitType() {
		return unitType;
	}

	public void setUnitType(Type unitType) {
		this.unitType = unitType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + unitNumber;
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
		if (unitNumber != other.unitNumber)
			return false;
		return true;
	}

}
