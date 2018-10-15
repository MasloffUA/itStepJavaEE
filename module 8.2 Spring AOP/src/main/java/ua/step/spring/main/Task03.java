package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Добавление сказителя используя АОП
 * @see ua.step.spring.model.knight.Minstrel
 */
public class Task03 {

	public static void main(String[] args) throws QuestException {
		// Загрузка контекста Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights3.xml");
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
	}
}