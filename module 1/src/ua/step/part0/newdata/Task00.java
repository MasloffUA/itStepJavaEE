package ua.step.part0.newdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Новый API по работе с датами
 *
 */
public class Task00 {

	public static void main(String[] args) {
		// получение текущего времени
		LocalDate date = LocalDate.now();

		/*
		 * Проводятся операции по смещению времени в сторону увеличения
		 */
		date = date.plusYears(1).plusMonths(1).plusWeeks(2).plusDays(5);
		System.out.println(date);
		
		/* Проводим смещение времени в сторону уменьшения */
		date = date.minusYears(1).minusMonths(1).minusWeeks(2).minusDays(5);

		/* Форматирование вывода */
		date.format(DateTimeFormatter.BASIC_ISO_DATE);

		System.out.println(date);

		/* Задаем дату используя строку */
		LocalDate dateFromString = LocalDate.parse("2014-04-16");

		System.out.println(dateFromString);

	}
}
