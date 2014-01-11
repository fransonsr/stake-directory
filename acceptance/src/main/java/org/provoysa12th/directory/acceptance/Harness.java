package org.provoysa12th.directory.acceptance;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Harness {

	private Map<String, List<Class<?>>> classMap = new HashMap<>();

	public Harness() {
		this("org.provoysa12th.directory.acceptance.tests");
	}

	public Harness(String thePackage) {
		try {
			String packagePath = thePackage.replace('.', File.separatorChar);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> resources = classLoader.getResources(packagePath);
			while(resources.hasMoreElements()) {
				String resource = resources.nextElement().getFile();
				File file = new File(URLDecoder.decode(resource, "UTF-8"));

				addClassFiles(thePackage, file);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<String> discoveredGroups() {
		return classMap.keySet();
	}

	public void start(Set<String> groups) {

		// TODO execute tests on another thread.

		Set<Class<?>> classes = new TreeSet<>(new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> c1, Class<?> c2) {
				return c1.getSimpleName().compareTo(c2.getSimpleName());
			}
		});

		for(String group : groups) {
			classes.addAll(classMap.get(group));
		}

		Result result = JUnitCore.runClasses(classes.toArray(new Class<?>[]{}));

		new JUnitResultWriter(result).writeTo(System.out);

	}

	private void addClassFiles(String thePackage, File file) {
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				addClassFiles(thePackage, subFile);
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
						AcceptanceTest acceptanceTest = theClass.getAnnotation(AcceptanceTest.class);
						String group = acceptanceTest.value();

						addClassToMap(group, theClass);
					}

				} catch (ClassNotFoundException e) {
					// do nothing here
				}
			}
		}
	}

	private void addClassToMap(String group, Class<?> theClass) {
		List<Class<?>> list = classMap.get(group);
		if(list == null) {
			list = new ArrayList<>();
			classMap.put(group, list);
		}

		list.add(theClass);
	}

}
