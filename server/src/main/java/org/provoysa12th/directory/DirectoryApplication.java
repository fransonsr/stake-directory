package org.provoysa12th.directory;

import org.glassfish.jersey.server.ResourceConfig;
import org.provoysa12th.directory.rest.impl.WellknownResourcesImpl;

public class DirectoryApplication extends ResourceConfig {

	public DirectoryApplication() {
		register(WellknownResourcesImpl.class);
	}
}
