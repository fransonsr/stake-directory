package org.provoysa12yh.directory.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
 * Well-known resources (see RFC 5785: http://tools.ietf.org/html/rfc5785).
 * @author FransonSR
 *
 */
@Component
@Path("/.well-known")
public interface WellknownResources {

	/**
	 * Heart-beat end-point. This merely returns "HTTP/1.1 200 OK"
	 * @return
	 */
	@Path("/heartbeat")
	@GET
	Response heartbeat();

	/**
	 * Return the Service Discovery document (Atom feed).
	 * @return
	 */
	@Path("/service-meta")
	@GET
	@Produces({MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
	Response serviceMeta();

}