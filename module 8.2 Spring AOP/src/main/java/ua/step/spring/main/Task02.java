package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Добавление минстреля используя стандартный подход
 * @see ua.step.spring.model.knight.StupidKnight
 */
public class Task02 {
	public static void main(String[] args) throws QuestException {
		// Загрузка контекста Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights2.xml");
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
	}
}