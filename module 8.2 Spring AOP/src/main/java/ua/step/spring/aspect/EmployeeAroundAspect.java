package ua.step.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class EmployeeAroundAspect {

	/**
	 * Помеченный аннотацией @Around метод как бы «обворачивает» вызов метода
	 * целевого класса. Данная аннотация является наиболее мощным средством для
	 * создания аспектов, но при этом такой подход требует наибольших
	 * трудозатрат.
	 * 
	 */
	@Around("execution(* ua.step.spring.model.Employee.getName())")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		System.err.println("До выполненеия getName() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.err.println("После выполнение getName() method. Return value=" + value);
		return value;
	}
}
