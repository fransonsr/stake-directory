package org.provoysa12th.directory.rest.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.core.MediaType;

import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Link;
import org.junit.Test;

public class ServiceDiscoveryBuilderTest {

	@Test
	public void testBuild() throws Exception {
		String baseURI = "http://localhost:8080/service";
		ServiceDiscoveryBuilder test = new ServiceDiscoveryBuilder(baseURI);
		Feed feed = test.build();
		assertThat(feed , is(notNullValue()));
		assertThat(feed.getId(), is(notNullValue()));
		assertThat(feed.getTitle(), is(notNullValue()));
		assertThat(feed.getUpdated(), is(notNullValue()));
		assertThat(feed.getAuthor(), is(notNullValue()));

		Link selfLink = feed.getLink("self");
		assertThat(selfLink.getHref().toString(), is("http://localhost:8080/service/.well-known/service-meta"));
		assertThat(selfLink.getMimeType().toString(), is(equalTo(MediaType.APPLICATION_ATOM_XML)));
	}
}
