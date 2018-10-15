package ua.step.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class EmployeeAspectPointcut {

	@Pointcut("execution(public String getName())")
	public void getNamePointcut(){}
	
	@Before("getNamePointcut()")
	public void loggingAdvice(){
		System.err.println("Выполнение первого совета для getName()");
	}
	
	@Before("getNamePointcut()")
	public void secondAdvice(){
		System.out.println("Выполнение втрогого совета для getName()");
	}

	@Before("allMethodsPointcut()")
	public void allServiceMethodsAdvice(){
		System.err.println("До выполнения сервисного метода");
	}
	
	//Pointcut выполнить для всех методов в классах в указанном пакете
	@Pointcut("within(ua.step.spring.service.*)")
	public void allMethodsPointcut(){}
	
}
