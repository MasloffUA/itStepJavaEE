package ua.step.example.part3.performer;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

/**
 * Класс который заменяет вызов метода из источника должен реализовывать
 * интерфейс MethodReplacer.
 * 
 */
public class TigerReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method reimplement, Object[] args) throws Throwable {
		return "Свирепый тигр"; // Помещает тигра в ящик, вместо асистентки
	}
}