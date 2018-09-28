package ua.step.part0.newdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * DateTimeFormatter - замена SimpleDateFormat
 *
 */
public class Task04 {
	public static void main(String[] args) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
		 
		LocalDateTime dateTime = LocalDateTime.parse("21.03.2014 - 12:00", formatter);
		 
		String formatted = formatter.format(dateTime);
		System.out.println(formatted);
	}
}