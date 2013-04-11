package org.provoysa12th.directory.aspect;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {

	private static final Logger log = Logger.getLogger(TraceAspect.class);

	@Pointcut("execution(* org.provoysa12th.directory.service.*Service.*(..))")
	public void serviceMethod() {}

	@Pointcut("execution(* org.provoysa12th.directory.domain.repository.*Repository.*(..))")
	public void repositoryMethod() {}

	@Before("serviceMethod() || repositoryMethod()")
	public void traceAdvice(JoinPoint jp) {
		if(log.isTraceEnabled()) {
			String signature = jp.getSignature().toShortString();
			log.trace("Enter: " + signature);
			NDC.push("  ");
		}
	}

	@After("serviceMethod() || repositoryMethod()")
	public void traceAfterAspect() {
		if(log.isTraceEnabled()) {
			NDC.pop();
		}
	}
}
