package org.provoysa12th.directory.rest.impl;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;

public class ServiceDiscoveryBuilder {

	private static final Date START_DATE = new Date();

	public Feed build() {
		Feed feed = Abdera.getInstance().newFeed();
		feed.addAuthor("Provo Utah YSA 12th Stake");
		feed.setUpdated(START_DATE);
		feed.setId("urn:rel:relations/service");
		feed.setTitle("Service Discovery Document for Stake Directory");

		return feed;
	}

}
