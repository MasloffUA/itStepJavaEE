package ua.step.spring.model.knight;

public class StupidQuest implements Quest {
	public int embark(String name) {
		System.out.println(name + " Пошел туда сам не знаю куда.");
		System.out.println(name + " Нашел что-то не знаю что.");
		System.out.println(name + " Пропил все...");
		System.out.println(name + " Лег обратно на печь");
		return 4;
	}
}