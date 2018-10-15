package ua.step.spring.model.knight;

public class BraveKnight implements Knight {
	private String name;
	private Quest quest;

	public BraveKnight() {
	}

	public BraveKnight(String name, Quest quest) {
		this.name = name;
		this.quest = quest;
	}

	public void embarkOnQuest() throws QuestException {
		quest.embark(name);
	}
}