package org.provoysa12th.directory.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.graphdb.Direction;
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
public class Unit extends BaseEntity {

	public static enum Type {
		Stake,
		Ward;
	}

	@NotNull
	private String name;
	@Indexed
	private Integer unitNumber;
	private Type type;

	@RelatedToVia(type = UnitOrganization.TYPE_UNIT_ORGANIZATION, direction = Direction.OUTGOING)
	private Set<UnitOrganization> unitOrganizations = new HashSet<UnitOrganization>();

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

	public Set<UnitOrganization> getUnitOrganizations() {
		return unitOrganizations;
	}

	public void setUnitOrganizations(Set<UnitOrganization> unitOrganizations) {
		this.unitOrganizations = unitOrganizations;
	}

	public Organization presidingOrganization() {
		for(UnitOrganization unitOrganization : unitOrganizations) {
			if(unitOrganization.isPresiding()) {
				return unitOrganization.getOrganization();
			}
		}

		return null;
	}

	public List<Organization> organizations() {
		return organizations(UnitOrganization.SORT_ASC);
	}

	public List<Organization> organizations(Comparator<? super UnitOrganization> comparator) {
		List<UnitOrganization> list = new ArrayList<UnitOrganization>(unitOrganizations);
		Collections.sort(list, comparator);

		List<Organization> organizations = new ArrayList<Organization>();
		for(UnitOrganization unitOrg : list) {
			organizations.add(unitOrg.getOrganization());
		}

		return organizations;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(unitNumber)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Unit))
			return false;
		Unit other = (Unit) obj;
		return new EqualsBuilder()
			.append(unitNumber, other.getUnitNumber())
			.isEquals();
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
