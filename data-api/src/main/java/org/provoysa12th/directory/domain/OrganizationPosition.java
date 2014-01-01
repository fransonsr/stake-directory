package org.provoysa12th.directory.domain;

import java.util.Comparator;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Represents a relationship betwen an Organization and a Position.
 * This relationship can be flagged 'presiding' in that the position
 * represents the presiding position in the organization. The 'orderIndex'
 * allows the relationships to be ordered.
 * @author FransonSR
 *
 */
@RelationshipEntity(type = OrganizationPosition.TYPE_ORGANIZATION_POSITION)
public class OrganizationPosition {

	public static final String TYPE_ORGANIZATION_POSITION = "ORGANIZATION_POSITION";

	public static final Comparator<? super OrganizationPosition> SORT_ASC = new Comparator<OrganizationPosition>() {
		@Override
		public int compare(OrganizationPosition position1, OrganizationPosition position2) {
			return position1.getOrderIndex() - position2.getOrderIndex();
		}
	};

	public static final Comparator<? super OrganizationPosition> SORT_DESC = new Comparator<OrganizationPosition>() {
		@Override
		public int compare(OrganizationPosition position1, OrganizationPosition position2) {
			return position2.getOrderIndex() - position1.getOrderIndex();
		}
	};

	@GraphId
	private Long relationshipId;
	@StartNode
	private Organization organization;
	@EndNode
	private Position position;
	private boolean presiding;
	private int orderIndex = -1;

	public OrganizationPosition() {}

	public OrganizationPosition(Organization organization, Position position) {
		this(organization, position, false, -1);
	}

	public OrganizationPosition(Organization organization, Position position, boolean presiding) {
		this(organization, position, presiding, -1);
	}

	public OrganizationPosition(Organization organization, Position position, boolean presiding, int orderIndex) {
		this.organization = organization;
		this.position = position;
		this.presiding = presiding;
		this.orderIndex = orderIndex;
	}

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isPresiding() {
		return presiding;
	}

	public void setPresiding(boolean presiding) {
		this.presiding = presiding;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganizationPosition [organization=").append(organization)
				.append(", position=").append(position)
				.append(", presiding=").append(presiding)
				.append(", orderIndex=").append(orderIndex)
				.append("]");
		return builder.toString();
	}

}
