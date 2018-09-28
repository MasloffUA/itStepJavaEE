package ua.step.part0.newdata;

import java.time.ZoneId;

/**
 * 
 * Timezones
 * 
 * Доступ к временным зонам представлен через специальный идентификатор и
 * фабрику по созданию.
 *
 */
public class Task03 {
	public static void main(String[] args) {

		ZoneId zoneId1 = ZoneId.of("Europe/Paris");
		ZoneId zoneId2 = ZoneId.of("Brazil/East");

		System.out.println(zoneId1.getRules());
		System.out.println(zoneId2.getRules());
	}
}
