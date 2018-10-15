package ua.step.example.practica.battery;

public class ChinaBattery implements Battery {

	private int power = 5;

	@Override
	public boolean getVoltage() {
		if (power > 0) {
			power--;
			return true;
		}
		return false;
	}

}
