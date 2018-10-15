package ua.step.example.di.constructor.battery;

public class DuracelBattery implements Battery {

	public static final int MAX_POWER = 50;
	private int power = MAX_POWER;

	@Override
	public boolean getVoltage() {
		if (power > 0) {
			power--;
			return true;
		}
		return false;
	}

}
