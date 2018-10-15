package ua.step.spring.model.knight;

public class Minstrel {
	public void singBeforeQuest() { // Вызывается перед выполнением задания
		System.out.println("Минстрель: Тра-та-та. Очень смелый рыцарь ступил на тяжелый путь!");
	}

	public void singAfterQuest() { // Вызывается после выполнения задания
		System.out.println("Минстрель: Тю-тю-тю; Успешно закончил тяжелое путешествие!");
	}
}