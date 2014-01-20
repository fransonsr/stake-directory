package org.provoysa12th.directory.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

public class SimpleLinkBuilderFactory implements LinkBuilderFactory {

	private HttpServletRequest request;

	public SimpleLinkBuilderFactory(HttpServletRequest request) {
		this.request = request;
	}

	public LinkBuilder newBuilder() {
		try {
			String scheme = request.getScheme();
			String userInfo = null;
			String host = request.getServerName();
			int port = request.getServerPort();
			String path = request.getServletPath();
			String query = null;
			String fragment = null;
			URI uri = new URI(scheme, userInfo, host, port, path, query, fragment);
			return new LinkBuilder().baseURI(uri.toString());

		} catch (URISyntaxException e) {
			throw new RuntimeException("Error creating base URI", e);
		}
	}

}
