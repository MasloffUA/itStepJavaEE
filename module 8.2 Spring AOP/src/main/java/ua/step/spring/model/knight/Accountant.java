package ua.step.spring.model.knight;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Accountant {
	@AfterReturning(pointcut = "execution(* ua.step.spring.model.knight.Quest.*(..))",
			returning="val")
	public void logAfterReturning(Object val){
		System.out.println("Задание было выполнено за "+ val +" золтых");
		
	}
}