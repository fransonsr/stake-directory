package org.provoysa12th.directory.rest;

import javax.ws.rs.core.UriInfo;

public class UriInfoLinkBuilderFactory implements LinkBuilderFactory {

	private UriInfo uriInfo;

	public UriInfoLinkBuilderFactory(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	@Override
	public LinkBuilder newBuilder() {
		String baseURI = uriInfo.getBaseUri().toString();

		return new LinkBuilder().baseURI(baseURI);
	}


}
