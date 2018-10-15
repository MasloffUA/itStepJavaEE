package ua.step.example.practica.flashlight;

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

}
