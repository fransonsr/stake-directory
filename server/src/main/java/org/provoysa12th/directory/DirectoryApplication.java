package org.provoysa12th.directory;

import org.glassfish.jersey.server.ResourceConfig;

import example.EchoResource;

public class DirectoryApplication extends ResourceConfig {

	public DirectoryApplication() {
		register(EchoResource.class);
	}
}
