package ua.step.spring.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.step.spring.model.knight.Knight;
import ua.step.spring.model.knight.QuestException;

/**
 *
 * Сокращение xml конфигурации с использованием аннотаций
 * @see ua.step.spring.model.knight.LegendMinstrel
 * @see ua.step.spring.model.knight.NormalKnight
 * @see ua.step.spring.model.knight.NormalQuest 
 */
public class Task07 {
	public static void main(String[] args) throws QuestException {
		// Загрузка нескольких контекстов
		String[] configs = {"knights7.xml"};
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configs);
		// Получение компонента knight
		Knight knight = context.getBean(Knight.class);
		// Использование компонента knight
		knight.embarkOnQuest();
		context.close();
		//FIXME добавь оруженосца
		//FIXME поменяй приключение, на убиение дракона 
	}
}