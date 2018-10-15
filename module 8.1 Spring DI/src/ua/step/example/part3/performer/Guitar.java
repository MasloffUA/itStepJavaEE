package ua.step.example.part3.performer;

import org.springframework.stereotype.Component;

public class Guitar implements Instrument {

	
	
	@Override
	public void play() {
		System.out.println("Брынди-брынди-балалайка");
	}
}
