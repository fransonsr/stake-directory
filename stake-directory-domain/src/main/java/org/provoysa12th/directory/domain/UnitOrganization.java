package org.provoysa12th.directory.domain;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

	public UnitOrganization() {}

	public UnitOrganization(Unit unit, Organization organization) {
		this(unit, organization, false, -1);
	}

	public UnitOrganization(Unit unit, Organization organization, boolean presiding) {
		this(unit, organization, presiding, -1);
	}

	public UnitOrganization(Unit unit, Organization organization, boolean presiding, int orderIndex) {
		this.unit = unit;
		this.organization = organization;
		this.presiding = presiding;
		this.orderIndex = orderIndex;
	}

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
		return new HashCodeBuilder()
			.append(organization)
			.append(unit)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnitOrganization))
			return false;
		UnitOrganization other = (UnitOrganization) obj;
		return new EqualsBuilder()
			.append(organization, other.getOrganization())
			.append(unit, other.getUnit())
			.isEquals();
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
