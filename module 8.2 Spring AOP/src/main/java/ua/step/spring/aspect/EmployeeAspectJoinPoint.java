package ua.step.spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeAspectJoinPoint {

	@Before("execution(public void ua.step.spring.model..set*(*))")
	public void loggingAdvice(JoinPoint joinPoint) {
		System.err.println("До запуска совета loggingAdvice на методе=" + joinPoint.toString());

		System.err.println("Установлен аргумент = " + Arrays.toString(joinPoint.getArgs()));

	}

	// Совет, будует применяться к методам с одним параметром
	// типа String
	@Before("args(name)")
	public void logStringArguments(String name) {
		System.err.println("Строковый параметер передан = " + name);
	}
}
