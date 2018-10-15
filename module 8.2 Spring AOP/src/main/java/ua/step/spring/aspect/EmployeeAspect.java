package ua.step.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Метод аспекта, помеченный аннотацией @Before, будет вызван перед выполнением
 * метода целевого класса. В отличие от аннотации @Around, избежать вызова
 * метода целевого класса возможно только генерацией исключительной ситуации
 * (exception) внутри аспекта.
 * 
 * @author VUnguryan
 *
 */
@Aspect
public class EmployeeAspect {

	@Before("execution(public String getName())")
	public void getNameAdvice() {
		System.err.println("Запушен метод getName");
		//throw new RuntimeException();
	}

	@Before("execution(* ua.step.spring.service.*.get*())")
	public void getAllAdvice() {
		System.err.println("Запушен геттер у сервиса");
	}
}
