package ua.step.part0.newdata;

import java.time.Clock;
import java.util.Date;

/**
 * 
 * Clock - содержит в себе текущее время и дату в зависимости от локали. Можно
 * получить время в миллисекундах, а также инстанс даты.
 *
 */
public class Task02 {
	public static void main(String[] args) {

		Clock clock = Clock.systemDefaultZone();
		Date date = Date.from(clock.instant());
		System.out.println(date);
		long millis = clock.millis();
		System.out.println(millis);
	}
}
