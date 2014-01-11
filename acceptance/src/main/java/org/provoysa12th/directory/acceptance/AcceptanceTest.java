package org.provoysa12th.directory.acceptance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as an acceptance test. If given a String value, the
 * acceptance test is part of the specified group.
 * @author FransonSR
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AcceptanceTest {
	String value() default "";
}
