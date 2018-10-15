package ua.step.example.part0;

import ua.step.example.part0.mailer.Mailer;

/**
 * 
 * Inversion of Control (IoC) инверсия управления — это некий абстрактный
 * принцип, набор рекомендаций для написания слабо связанного кода. Суть
 * которого в том, что каждый компонент системы должен быть как можно более
 * изолированным от других, не полагаясь в своей работе на детали конкретной
 * реализации других компонентов.
 *
 */
public class Task01 {

	public static void main(String[] args) {
		Mailer mailer = new Mailer();
		mailer.send("Hello World");
	}
}