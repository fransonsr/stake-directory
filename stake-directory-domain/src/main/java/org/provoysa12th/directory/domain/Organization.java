package org.provoysa12th.directory.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Represents a organization associated with a Unit.
 * <p>
 * Business key: name, type, unit
 * </p>
 * @author FransonSR
 *
 */
@NodeEntity
public class Organization extends BaseEntity {

	public static enum Type {
		Presidency,
		Bishopric,
		Council,
		HighCouncil;
	}

	private String name;
	private Type type;
	private Unit unit;

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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(name)
			.append(type)
			.append(unit)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Organization))
			return false;
		Organization other = (Organization) obj;
		return new EqualsBuilder()
			.append(name, other.getName())
			.append(type, other.getType())
			.append(unit, other.getUnit())
			.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organization [name=").append(name)
				.append(", type=").append(type)
				.append(", unit=").append(unit)
				.append("]");
		return builder.toString();
	}

}
