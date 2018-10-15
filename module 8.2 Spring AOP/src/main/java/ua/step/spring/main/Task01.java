package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Рыцарь (Knight) умеет выполнять (embarkOnQuest) поручения Quest
 */
public class Task01 {
	public static void main(String[] args) throws QuestException {
		// Загрузка контекста Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights1.xml");
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
	}
}