package ua.step.part3.reflection;

import java.util.ArrayList;

public class Task00 {
	public static void main(String[] args)  {
		Class<ArrayList> clazz = ArrayList.class;
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getName());
		System.out.println(clazz.getSuperclass());
	}
}