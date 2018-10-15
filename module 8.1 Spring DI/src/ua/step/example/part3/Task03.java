package ua.step.example.part3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.example.part3.performer.PerformanceException;
import ua.step.example.part3.performer.Performer;

/**
 * 
 * Чтобы помочь Кенни избежать заражения чужими микробами от чужих саксофонов,
 * мы воспользуемся технологией Spring, известной как внутренние компоненты.
 * Создавая объект бина Саксофон, перед использованием
 */
public class Task03 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring3.xml");
		Performer performer = context.getBean("kenny", Performer.class);
		performer.perform();
	}
}