package ua.step.example.part3.performer;

import java.util.Collection;

public class OneManBand implements Performer {
	private String name = "Человек оркестр";
	private Collection<Instrument> instruments;

	public OneManBand() {
	}

	@Override
	public void perform() throws PerformanceException {
		for (Instrument instrument : instruments) {
			instrument.play();
		}
	}

	public void setInstruments(Collection<Instrument> instruments) {
		this.instruments = instruments; // Внедрение коллекции инструментов
	}
	
	public String getName() {
		return name;
	}
}