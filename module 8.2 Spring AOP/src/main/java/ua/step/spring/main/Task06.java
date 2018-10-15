package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Выполнение действий после возврата значения из метода
 * @see ua.step.spring.model.knight.Accountant
 *
 */
public class Task06 {
	public static void main(String[] args) throws QuestException {
		// Загрузка  контекста
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights7.xml");
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
	}
}