package ua.step.example.di.constructor.battery;

public class Accum implements Battery{
	
	private int power = 0;
	boolean inFlashing = true;
	
	@Override
	public boolean getVoltage() {
		if (power > 0) {
			power--;
			System.out.println("Power : " + power);
			return true;
		}
		return false;
	}
	
	public void charging(int time) {
		if (isInFlashing()) {
			return;
		}
		power+=time;
		if (power>5) {
			power=5;
		}	
	}

	public boolean isInFlashing() {
		return inFlashing;
	}

	public void setInFlashing(boolean inFlashing) {
		this.inFlashing = inFlashing;
	}
	
	
	

}
