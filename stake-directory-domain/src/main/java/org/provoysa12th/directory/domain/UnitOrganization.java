package org.provoysa12th.directory.domain;

import java.util.Comparator;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Represents a relationship between Unit and Organization. This
 * relationship can be flagged 'presiding' in that the Organization
 * represents the presiding organization of the Unit. The 'orderIndex'
 * allows the relationships to be ordered.
 * <p>
 * Business key: unit, organization
 * </p>
 * @author FransonSR
 *
 */
@RelationshipEntity(type = UnitOrganization.TYPE_UNIT_ORGANIZATION)
public class UnitOrganization {

	public static final String TYPE_UNIT_ORGANIZATION = "UNIT_ORGANIZATION";

	public static final Comparator<? super UnitOrganization> SORT_ASC = new Comparator<UnitOrganization>() {
		@Override
		public int compare(UnitOrganization org1, UnitOrganization org2) {
			return org1.getOrderIndex() - org2.getOrderIndex();
		}
	};

	public static final Comparator<? super UnitOrganization> SORT_DESC = new Comparator<UnitOrganization>() {
		@Override
		public int compare(UnitOrganization org1, UnitOrganization org2) {
			return org2.getOrderIndex() - org1.getOrderIndex();
		}
	};

	@GraphId
	private Long relationshipId;
	@StartNode
	private Unit unit;
	@EndNode
	private Organization organization;
	private int orderIndex = -1;
	private boolean presiding;

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public boolean isPresiding() {
		return presiding;
	}

	public void setPresiding(boolean presiding) {
		this.presiding = presiding;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		UnitOrganization other = (UnitOrganization) obj;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnitOrganization [unit=").append(unit)
				.append(", organization=").append(organization)
				.append(", presiding=").append(presiding)
				.append(", orderIndex=").append(orderIndex)
				.append("]");
		return builder.toString();
	}


}
