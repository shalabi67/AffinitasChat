package com.affinitas.chat.logging;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * LoggerExceptionAspect enables Exception logging. 
 *
 *
 * @author mohammad
 */
@Component
@Aspect
public class LoggerExceptionAspect {
	
	@AfterThrowing(value = "execution (* com.affinitas..*.*(..))", throwing="ex")
	public void logException(Exception ex) {
		Logger.LogException("Exception: " + ex);
	}
}
