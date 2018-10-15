package ua.step.example.di.constructor.flashlight;

import ua.step.example.di.constructor.battery.Accum;
import ua.step.example.di.constructor.battery.Battery;
// Доп дом задание:
/* 
 * 
 * Переделать аккумулятор, чтобы заряжался не в фонарике
 * Добавить радио
 * 
 * 
 * 
 */


public class SomeFlashlight implements Flashlight {
	private Battery battery;
	private boolean swithOn;

	public SomeFlashlight(Battery battery) {
		this.battery = battery;
		this.swithOn = false;
	}
	
	public SomeFlashlight() {
		this(null);
	}
	



	@Override
	public boolean isShines() {
		return (battery != null) && swithOn;
	}

	@Override
	public void swithOn() {
		if (!swithOn && battery != null) {
			swithOn = battery.getVoltage();
		}
	}

	@Override
	public void swithOff() {
		swithOn = false;
	}

	public void setBattery(Battery battery) {
		if (battery == null && battery instanceof Accum) {
		}
		this.battery = battery;
	}
	
	@Override
	public void charging(int time) {
		if (battery instanceof Accum) {
			((Accum) battery).charging(time);
		}
	}

	
	

}
