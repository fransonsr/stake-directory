package org.provoysa12th.directory.acceptance;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
@Path("/")
public class HarnessResources {

	@GET
	@Path("/echo")
	public Response echo(@Context UriInfo uriInfo) {
		URI uri = uriInfo.getRequestUri();

		return Response.ok(uri.toString()).build();
	}

	@POST
	@Path("/test")
	public Response startTest(@QueryParam("uri") String uri) {
		Harness harness = new Harness();

		harness.start(harness.discoveredGroups());

		return null;
	}
}
