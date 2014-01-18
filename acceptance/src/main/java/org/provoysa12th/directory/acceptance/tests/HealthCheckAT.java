package org.provoysa12th.directory.acceptance.tests;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.provoysa12th.directory.acceptance.AcceptanceTest;
import org.provoysa12th.directory.util.XMLResource;
import org.provoysa12th.directory.util.XMLResourceBuilder;
import org.provoysa12th.directory.util.XMLResourceBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Element;

@AcceptanceTest("healthcheck")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseAcceptanceTestConfiguration.class})
public class HealthCheckAT {

	private static final Logger LOG = LoggerFactory.getLogger(HealthCheckAT.class);

	@Autowired
	XMLResourceBuilderFactory xmlResourceBuilderFactory;

	@Autowired
	Client jerseyClient;

	@Autowired
	String baseURI;

	@Test
	public void testHeartbeat() {
		String uri = baseURI + "/.well-known/heartbeat";

		LOG.info("URI: " + uri);

		WebTarget target = jerseyClient.target(uri);
		Response response = target.request().get();

		assertThat(response.getStatus(), is(equalTo(200)));
	}

	@Test
	public void testServiceMeta() {
		String uri = baseURI + "/.well-known/service-meta";

		XMLResourceBuilder xmlResourceBuilder = xmlResourceBuilderFactory.newBuilder(uri);
		XMLResource serviceDiscovery = xmlResourceBuilder
			.accept(MediaType.APPLICATION_ATOM_XML_TYPE)
			.build();

		Element xml = serviceDiscovery.rootElement();
		assertThat(xml, hasXPath("/feed"));
		assertThat(xml, hasXPath("/feed/id"));
		assertThat(xml, hasXPath("/feed/updated"));
		assertThat(xml, hasXPath("/feed/title"));
		assertThat(xml, hasXPath("/feed/author"));
	}

}
