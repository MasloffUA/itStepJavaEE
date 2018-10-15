package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 * Добавление аспектов с использованием аннотаций
 * @see ua.step.spring.model.knight.Armiger
 */
public class Task04 {
	public static void main(String[] args) throws QuestException {
		// Загрузка нескольких контекстов
		String[] configs = {"knights1.xml", "knights4.xml"};
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configs);
		// Получение компонента knight
		Knight knight = (Knight) context.getBean("knight");
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
	}
}