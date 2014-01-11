package org.provoysa12th.directory.acceptance;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Harness {

	public void start() {

		List<Class<?>> classes = new ArrayList<>();

		try {
			Package atPackage = Package.getPackage("org.provoysa12th.directory.acceptance");
			String packagePath = atPackage.getName().replace('.', File.separatorChar);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> resources = classLoader.getResources(packagePath);
			while(resources.hasMoreElements()) {
				String resource = resources.nextElement().getFile();
				File file = new File(URLDecoder.decode(resource, "UTF-8"));

				addClassFiles(atPackage.getName(), file, classes);
			}

			Result result = JUnitCore.runClasses(classes.toArray(new Class<?>[]{}));

			System.out.println(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addClassFiles(String thePackage, File file, List<Class<?>> classes) {
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				addClassFiles(thePackage, subFile, classes);
			}
		}
		else {
			String filename = file.getName();
			if(filename.endsWith(".class")) {
				String name = filename.substring(0, filename.indexOf(".class"));
				String className = thePackage + "." + name;

				try {
					Class<?> theClass = Class.forName(className);

					if(theClass.isAnnotationPresent(AcceptanceTest.class)) {
						classes.add(theClass);
					}

				} catch (ClassNotFoundException e) {
					// do nothing here
				}
			}
		}
	}

}
