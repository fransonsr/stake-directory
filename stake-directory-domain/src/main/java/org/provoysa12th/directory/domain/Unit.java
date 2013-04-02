package org.provoysa12th.directory.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Unit {

	public static enum Type {
		STAKE,
		WARD;
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

}
