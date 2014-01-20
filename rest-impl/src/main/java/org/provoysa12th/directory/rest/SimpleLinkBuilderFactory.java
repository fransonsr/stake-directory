package org.provoysa12th.directory.rest;

import javax.servlet.http.HttpServletRequest;

public class SimpleLinkBuilderFactory implements LinkBuilderFactory {

	private HttpServletRequest request;

	public SimpleLinkBuilderFactory(HttpServletRequest request) {
		this.request = request;
	}

	public LinkBuilder newBuilder() {
		String baseURI = request.getRequestURL().toString();

		return new LinkBuilder().baseURI(baseURI);
	}

}
