package example;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
@Path("/echo")
public class EchoResource {

	@GET
	@Produces("text/plain")
	public Response echo(@Context UriInfo uriInfo) {
		URI uri = uriInfo.getRequestUri();

		return Response.ok("URI: " + uri.toString()).build();
	}
}
