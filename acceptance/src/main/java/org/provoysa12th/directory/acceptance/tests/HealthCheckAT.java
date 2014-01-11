package org.provoysa12th.directory.acceptance.tests;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.acceptance.AcceptanceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@AcceptanceTest("healthcheck")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseAcceptanceTestConfiguration.class})
public class HealthCheckAT {

	@Autowired
	Client jerseyClient;

	@Autowired
	String baseURI;

	@Test
	public void testHeartbeat() {
		String uri = baseURI + "/.well-known/heartbeat";

		System.out.println("URI: " + uri);

		WebTarget target = jerseyClient.target(uri);
		Response response = target.request().get();

		assertThat(response.getStatus(), is(equalTo(200)));
	}

}
