package ua.step.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 
 * Связывание совета и аннотации
 *
 */
@Aspect
public class EmployeeAnnotationAspect {

	@Before("@annotation(ua.step.spring.aspect.Loggable)")
	public void myAdvice(){
		System.err.println("Выполнение совета для анотации Loggable");
	}
}