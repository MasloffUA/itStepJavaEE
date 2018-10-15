package ua.step.example.part3.performer;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class DuckReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method reimplement, Object[] args) throws Throwable {
		return "Свирепый утка";
	}
}
