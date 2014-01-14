package org.provoysa12yh.directory.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	public abstract Response heartbeat();

}