package org.provoysa12th.directory.rest.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkTest {

	private static final Logger LOG = LoggerFactory.getLogger(LinkTest.class);

	private Link link;

	@Before
	public void setup() {
		link = new Link();
		link.setHref("http://localhost/entity");
		link.setRel("relation");
		link.setType("application/xml");
		link.setMethod("GET,POST,PUT,DELETE");
		link.setTitle("Link to an entity");
	}

	@Test
	public void testLink() throws Exception {
		assertThat(link.getHref(), is("http://localhost/entity"));
		assertThat(link.getRel(), is("relation"));
		assertThat(link.getType(), is("application/xml"));
		assertThat(link.getMethod(), is("GET,POST,PUT,DELETE"));
		assertThat(link.getTitle(), is("Link to an entity"));
	}

	@Test
	public void testMarshalling() throws Exception {
		JAXBContext ctx = JAXBContext.newInstance(Link.class);
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

		StringWriter writer = new StringWriter();
		marshaller.marshal(link, writer);

		LOG.info(writer.toString());
	}
}
