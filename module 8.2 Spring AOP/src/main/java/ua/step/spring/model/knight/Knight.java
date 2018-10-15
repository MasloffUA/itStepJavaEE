package ua.step.spring.model.knight;

public interface Knight {
	/**
	 * Рыцарь умеет выполнять задания
	 * @throws QuestException
	 */
	public void embarkOnQuest() throws QuestException;
}
