package ua.step.spring.test;

import static org.mockito.Mockito.*;
import org.junit.Test;

import ua.step.spring.model.knight.BraveKnight;
import ua.step.spring.model.knight.Quest;
import ua.step.spring.model.knight.QuestException;

public class BraveKnightTest {
	@Test
	public void knightShouldEmbarkOnQuest() throws QuestException {
		// Создание фиктивного объекта Quest
		Quest mockQuest = mock(Quest.class); 
		// Внедрение через конструктор
		BraveKnight knight = new BraveKnight("", mockQuest); 
		knight.embarkOnQuest(); // выполнение задания
		
		// проверяем что был вызван метод embark у объекта mockQuest ровно один раз
		verify(mockQuest, times(1)).embark("");
	}
}