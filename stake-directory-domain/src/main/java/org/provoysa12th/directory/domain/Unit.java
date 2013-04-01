package org.provoysa12th.directory.domain;

public class Unit {

	public static enum Type {
		STAKE,
		WARD;
	}

	private String name;
	private int unitNumber;
	private Type unitType;

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

}
