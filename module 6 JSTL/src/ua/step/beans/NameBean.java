package ua.step.beans;

import java.util.Arrays;
import java.util.List;

public class NameBean {
	private List<String> names = Arrays.asList(getNamesAsArray());
	public List<String> getNames() {
		return names;
	}

	public final String[] getNamesAsArray() {
		return new String[] { "Василий", "Петр", "Елена", "Алена" };
	}
}