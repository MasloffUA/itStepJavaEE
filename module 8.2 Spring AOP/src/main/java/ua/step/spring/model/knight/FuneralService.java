package ua.step.spring.model.knight;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * 
 * Похоронное агенство
 *
 */
@Aspect
public class FuneralService {
	@AfterThrowing(pointcut = "execution(* ua.step.spring.model.knight.*.*(..))", throwing = "exception")
	public void afterThrowing(Exception exception) {
		System.out.println("Похоронное бюро: здесь покоится неизвестный рыцарь");
	}
}