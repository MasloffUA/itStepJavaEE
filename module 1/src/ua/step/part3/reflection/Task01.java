package ua.step.part3.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Task01 {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Test test = new Test();
		Class clazz = test.getClass();
		System.out.println(clazz.getName());
		// получение модификаторов класса
		int mods = clazz.getModifiers();
		printModifier(mods);
		System.out.println("\nFields info:");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// получение модификаторов поля
			mods = field.getModifiers();
			printModifier(mods);
			System.out.print(field.getType() + " ");
			System.out.println(field.getName());
		}
	}

	private static void printModifier(int mods) {
		if (Modifier.isPublic(mods)) {
			System.out.print("public ");
		} else if (Modifier.isProtected(mods)) {
			System.out.print("protected ");
		} else if (Modifier.isPrivate(mods)) {
			System.out.print("private ");
		} else {
			System.out.print("package-private ");
		}
		if (Modifier.isAbstract(mods)) {
			System.out.print("abstract ");
		}
		if (Modifier.isFinal(mods)) {
			System.out.print("final ");
		}
		if (Modifier.isStatic(mods)) {
			System.out.print("static ");
		}
	}
	/**
	 * 
	 * Исследуемый класс
	 *
	 */
	@SuppressWarnings("unused")
	static class Test {
		private static short s = 3;
		private int value = 3;
		String name = "Leo";
		public boolean test;
	}
}