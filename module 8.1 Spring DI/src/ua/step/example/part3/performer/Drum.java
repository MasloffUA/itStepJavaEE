package ua.step.example.part3.performer;

import org.springframework.stereotype.Component;

@Component
public class Drum implements Instrument {
	@Override
	public void play() {
		System.out.println("Бум-бум-бамц.");
	}
}