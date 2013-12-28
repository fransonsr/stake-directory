package org.provoysa12th.directory.domain;

import java.util.UUID;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;

/**
 * Base class for entities.
 * @author FransonSR
 *
 */
public abstract class BaseEntity {

	@GraphId
	private Long nodeId;
	@Indexed(level = Indexed.Level.INSTANCE)
	private UUID uuid;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
