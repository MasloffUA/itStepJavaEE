package ua.step.spring.model.knight;

public class AbortiveKnight implements Knight {
	public void embarkOnQuest() throws QuestException {
		System.out.println("Рыцарь: подскальзывается и падает в яму с ядовитыми змеями");
		System.out.println("... и с шипами");
		System.out.println("... и заполненную кислотой");
		throw new QuestException(); // бросаем исключительную ситуацию
	}
}
