package ua.step.example.part3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part3.performer.PerformanceException;
import ua.step.example.part3.performer.Performer;

/**
 * Если у нас есть несколько исполнителей которые играют одну и ту же мелодию на
 * одном и том же инструменте, можно создать абстрактный бин
 *
 */
public class Task05 {
	public static void main(String[] args) throws PerformanceException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring5.xml");
		Performer performer = context.getBean("kenny", Performer.class);
		performer.perform();

		performer = context.getBean("kail", Performer.class);
		performer.perform();
		
		performer = context.getBean("david", Performer.class);
		performer.perform();
		
		performer = context.getBean("frank", Performer.class);
		performer.perform();
	}
}