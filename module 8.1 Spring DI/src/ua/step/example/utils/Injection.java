package ua.step.example.utils;

import java.lang.reflect.Field;

/**
 * 
 * Смотри тетс в классе Task03Test
 *
 */
public class Injection {
	/**
	 * Метод служит для инъекции одного объекта в соответствующие поля другого.
	 * Все поля, которые могут принять этот объект да заполнятся им (окромя
	 * Object).
	 * 
	 * @param object
	 *            Кого укалываем.
	 * @param injectible
	 *            Что вводим.
	 * @return вернули то, что дали на вход (удобства ради).
	 */
	public static <T> T injectAll(T object, Object injectible) {
		final Field[] declaredFields = object.getClass().getDeclaredFields();
		final Class<? extends Object> injectibleClass = injectible.getClass();

		boolean injected = false;

		for (Field field : declaredFields) {
			if (Object.class.equals(field.getType())) {
				continue;
			}

			if (!field.getType().isAssignableFrom(injectibleClass)) {
				continue;
			}

			field.setAccessible(true); // разрешаем записывать в поле объект

			try {
				field.set(object, injectible); // записываем объект в поле
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Неудача, по причине:", e);
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException("Неудача, по причине:", e);
			}

			field.setAccessible(false);

			injected = true;
		}

		if (!injected) {
			throw new IllegalArgumentException("В исходном объекте не нашлось ни одного поля по образу и подобию.");
		}
		return object;
	}
}