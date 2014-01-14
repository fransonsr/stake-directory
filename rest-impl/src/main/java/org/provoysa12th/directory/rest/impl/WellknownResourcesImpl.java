package org.provoysa12th.directory.rest.impl;

import javax.ws.rs.core.Response;

import org.provoysa12yh.directory.rest.WellknownResources;

public class WellknownResourcesImpl implements WellknownResources {

	public Response heartbeat() {
		return Response.ok().build();
	}

}
