package ua.step.part0.newdata;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * Новый API по работе с датами. Периоды
 *
 */
public class Task01 {
	public static void main(String[] args) {

		// значение даты без учета врем енной зоны.
		LocalDate currentDate = LocalDate.now();

		// создание периода
		Period period = Period.ofDays(15);
		// добавление 15 дней к текущей дате
		
		LocalDate newDate = currentDate.plus(period);
		printDate(newDate);
		
		newDate = currentDate.minus(period);
		printDate(newDate);
	}
	private static void printDate(LocalDate newDate){
		System.out.println("year:" + newDate.getYear());
		System.out.println("month:" + newDate.getMonth());
		System.out.println("day:" + newDate.getDayOfMonth());
	}
}
