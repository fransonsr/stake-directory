package org.provoysa12th.directory.acceptance;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JUnitResultWriter {

	private Result result;

	public JUnitResultWriter(Result result) {
		this.result = result;
	}

	public void writeTo(PrintStream ps) {
		writeTo(new PrintWriter(ps));
	}

	public void writeTo(PrintWriter out) {
		out.println("=======================================================================");
		out.println("Results: count: " + result.getRunCount() + "; time(ms): " + result.getRunTime());
		out.println("         failed: " + result.getFailureCount() + "; ignored: " + result.getIgnoreCount());

		if(result.getFailureCount() > 0) {
			out.println("--Failures-------------------------------------------------------------");
			for(Failure failure : result.getFailures()) {
				out.println(failure);
			}
		}

		out.println("=======================================================================");
		out.flush();
	}
}
