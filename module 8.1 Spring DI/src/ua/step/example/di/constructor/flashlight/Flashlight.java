package ua.step.example.di.constructor.flashlight;

import ua.step.example.di.constructor.battery.Battery;

/**
 * Интерфейс фонарика
 * 
 *
 */
public interface Flashlight {

	/**
	 * Включение
	 */
	void swithOn();
	
	/**
	 * Выключение
	 */
	void swithOff();
	
	/**
	 * Возвращает истину если светит
	 * @return
	 */
	boolean isShines();
	
	void setBattery(Battery battery);

	public void charging(int time);
}
