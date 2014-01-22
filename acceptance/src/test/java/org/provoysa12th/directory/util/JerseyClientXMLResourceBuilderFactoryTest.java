package org.provoysa12th.directory.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

public class JerseyClientXMLResourceBuilderFactoryTest {

	private Client client;
	private String uri = "http://localhost:8080/resource";
	private WebTarget target;
	private Builder builder;
	private Response response;

	@Before
	public void setup() throws Exception {


		client = mock(Client.class);
		target = mock(WebTarget.class);
		builder = mock(Builder.class);
		response = mock(Response.class);

		when(client.target(uri)).thenReturn(target);
		when(target.request()).thenReturn(builder);
		when(builder.get()).thenReturn(response);

		TestClass testClass = new TestClass();
		testClass.setId("test class id");
		testClass.setValue("test");

		JAXBContext ctx = JAXBContext.newInstance(TestClass.class);
		Marshaller marshaller = ctx.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.marshal(testClass, writer);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(writer.toString().getBytes());

		when(response.getEntity()).thenReturn(inputStream);
	}

	@Test
	public void testBuild() throws Exception {
		XMLResourceBuilderFactory xmlResourceBuilder = new JerseyClientXMLResourceBuilderFactory(client);

		XMLResourceBuilder test = xmlResourceBuilder.newBuilder(uri);

		test.accept("application/xml")
			.authorization("Bearer", "credential");

		XMLResource resource = test.build();
		assertThat(resource.exists("/testClass/value"), is(true));

		verify(builder).accept("application/xml");
		verify(builder).header(HttpHeaders.AUTHORIZATION, "Bearer credential");
	}
}
