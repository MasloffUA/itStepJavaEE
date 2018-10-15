package ua.step.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * 
 * Совет данный после выполнения методов
 *
 */
@Aspect
public class EmployeeAfterAspect {

	/**
	 * Использование аннотации @After можно сравнить с директивой finally языка
	 * Java, то есть метод, помеченный этой аннотацией, будет выполнен
	 * независимо от того, завершился ли метод целевого класса нормально или
	 * исключительной ситуацией. Однако используя эту аннотацию, нет возможности
	 * обращаться к возвращаемому значению целевой функции или к исключительной
	 * ситуации, возникшей в ходе ее исполнения.
	 * 
     */
	@After("args(name)")
	public void logStringArguments(String name) {
		System.err.println("Запущено после Advice. Установлен параметр = " + name);
	}

	/**
	 * Использование аннотации @AfterThrowing определяет, что сквозная
	 * функциональность будет выполнена в том случае, если выполнение целевой
	 * метода закончилось исключительной ситуацией. Тип исключения определяется
	 * аргументом метода, помеченного этой аннотацией
	 */
	@AfterThrowing("within(ua.step.spring.model.Employee)")
	public void logExceptions(JoinPoint joinPoint) {
		System.err.println("Исключение брошено в Employee в методе = " + joinPoint.toString());
	}

	/**
	 * Метод аспекта, помеченный аннотацией @AfterReturning, будет вызван после
	 * нормального завершения работы метода целевого класса. Под «нормальным
	 * завершением» понимается, что в ходе выполнения не было сгенерировано
	 * исключительной ситуации (exception)
	 * 
	 * @param returnString
	 */
	@AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
	public void getNameReturningAdvice(String returnString) {
		System.err.println("метод getNameReturningAdvice выполнен. Возвращена строка = " + returnString);
	}

}
