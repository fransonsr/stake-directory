package org.provoysa12th.directory.rest.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.provoysa12yh.directory.rest.WellknownResources;

public class WellknownResourcesImplTest {

	@Test
	public void testHeartbeat() throws Exception {
		WellknownResources test = new WellknownResourcesImpl();;
		Response response = test.heartbeat();
		assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
	}
}
