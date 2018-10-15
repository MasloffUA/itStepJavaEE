package ua.step.example.model;

public class HelloWorld {
	private String message;
	private int value = 1;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessage(String message, int value) {
		this.value = value;
		this.message = message;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message + value);
	}
}
