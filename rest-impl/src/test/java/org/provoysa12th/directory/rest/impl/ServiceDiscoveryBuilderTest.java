package org.provoysa12th.directory.rest.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.apache.abdera.model.Feed;
import org.junit.Test;

public class ServiceDiscoveryBuilderTest {

	@Test
	public void testBuild() throws Exception {
		ServiceDiscoveryBuilder test = new ServiceDiscoveryBuilder();
		Feed feed = test.build();
		assertThat(feed , is(notNullValue()));
		assertThat(feed.getId(), is(notNullValue()));
		assertThat(feed.getTitle(), is(notNullValue()));
		assertThat(feed.getUpdated(), is(notNullValue()));
		assertThat(feed.getAuthor(), is(notNullValue()));
	}
}
