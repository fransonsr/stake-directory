package org.provoysa12th.directory.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

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

	@Fetch
	private Unit unit;

	@RelatedToVia(type = UnitOrganization.TYPE_UNIT_ORGANIZATION, direction = Direction.INCOMING)
	private UnitOrganization unitOrganization;

	@RelatedToVia(type = OrganizationPosition.TYPE_ORGANIZATION_POSITION, direction = Direction.OUTGOING)
	private Set<OrganizationPosition> organizationPositions = new HashSet<OrganizationPosition>();

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

	public UnitOrganization getUnitOrganization() {
		return unitOrganization;
	}

	public void setUnitOrganization(UnitOrganization unitOrganization) {
		this.unitOrganization = unitOrganization;
	}

	public Set<OrganizationPosition> getOrganizationPositions() {
		return organizationPositions;
	}

	public void setOrganizationPositions(
			Set<OrganizationPosition> organizationPositions) {
		this.organizationPositions = organizationPositions;
	}

	public Position presidingPosition() {
		for(OrganizationPosition op : organizationPositions) {
			if(op.isPresiding()) {
				return op.getPosition();
			}
		}

		return null;
	}

	public List<Position> positions() {
		return positions(OrganizationPosition.SORT_ASC);
	}

	public List<Position> positions(Comparator<? super OrganizationPosition> comparator) {
		List<OrganizationPosition> list = new ArrayList<OrganizationPosition>(organizationPositions);
		Collections.sort(list, comparator);

		List<Position> positions = new ArrayList<Position>();
		for(OrganizationPosition op : list) {
			positions.add(op.getPosition());
		}

		return positions;
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
				.append(", id=").append(getNodeId())
				.append("]");
		return builder.toString();
	}

}
