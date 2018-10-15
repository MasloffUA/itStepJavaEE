package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Выполнение действий после возникновения исключительных ситуаций
 * @see ua.step.spring.model.knight.AbortiveKnight
 * @see ua.step.spring.model.knight.FuneralService
 */
public class Task05 {
	public static void main(String[] args)  {
		// Загрузка контекста Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights5.xml");
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		try {
			knight.embarkOnQuest();
		} catch (QuestException e) {
			System.out.println("Приключение прервано");
		}
		context.close();
	}
}