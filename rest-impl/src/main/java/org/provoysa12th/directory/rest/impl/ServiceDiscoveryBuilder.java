package org.provoysa12th.directory.rest.impl;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Feed;
import org.provoysa12th.directory.rest.LinkBuilder;
import org.provoysa12th.directory.rest.WellknownResources;
import org.provoysa12th.directory.rest.model.Link;

public class ServiceDiscoveryBuilder {

	private static final Date START_DATE = new Date();
	private String baseURI;

	public ServiceDiscoveryBuilder(String baseURI) {
		this.baseURI = baseURI;
	}

	public Feed build() {
		Feed feed = Abdera.getInstance().newFeed();
		feed.addAuthor("Provo Utah YSA 12th Stake");
		feed.setUpdated(START_DATE);
		feed.setId("urn:rel:relations/service");
		feed.setTitle("Service Discovery Document for Stake Directory");

		Link self = new LinkBuilder()
			.baseURI(baseURI)
			.path(WellknownResources.class)
			.path(WellknownResources.class, "serviceMeta")
			.method(WellknownResources.class, "serviceMeta")
			.type(MediaType.APPLICATION_ATOM_XML)
			.build();

		org.apache.abdera.model.Link selfLink = feed.addLink(self.getHref(), "self");
		selfLink.setMimeType(self.getType());

		return feed;
	}

}
