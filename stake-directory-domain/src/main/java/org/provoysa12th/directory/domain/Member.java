package org.provoysa12th.directory.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Represents a member. The 'mrnHash' is a secure hash of the member's
 * LDS Membership Record Number (MRN).
 * <p>
 * Business key: mrnHash
 * </p>
 * @author FransonSR
 *
 */
@NodeEntity
public class Member extends BaseEntity {

	@Indexed
	private String firstName;
	@Indexed
	private String lastName;
	@Indexed
	private String mrnHash;
	private String email;
	private String phone;

	@RelatedTo(type = Unit.TYPE_UNIT_MEMBER, direction = Direction.OUTGOING)
	private Unit unit;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMrnHash() {
		return mrnHash;
	}

	public void setMrnHash(String mrnHash) {
		this.mrnHash = mrnHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
			.append(mrnHash)
			.build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return new EqualsBuilder()
			.append(mrnHash, other.getMrnHash())
			.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [firstName=").append(firstName)
				.append(", lastName=").append(lastName)
				.append(", email=").append(email)
				.append(", phone=").append(phone)
				.append(", unit=").append(unit)
				.append("]");
		return builder.toString();
	}


}
