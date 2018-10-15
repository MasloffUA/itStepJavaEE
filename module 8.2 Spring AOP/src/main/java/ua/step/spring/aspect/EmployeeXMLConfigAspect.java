package ua.step.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class EmployeeXMLConfigAspect {

	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.err.println("EmployeeXMLConfigAspect:: Перед вызовом метода getName()");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.err.println("EmployeeXMLConfigAspect:: После вызова getName() method. Возвращено значение ="+value);
		return value;
	}
}
