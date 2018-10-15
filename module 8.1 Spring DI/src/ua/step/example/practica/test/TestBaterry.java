package ua.step.example.practica.test;

import static org.junit.Assert.*;

import ua.step.example.practica.battery.Battery;
import ua.step.example.practica.battery.ChinaBattery;
import ua.step.example.practica.battery.DuracelBattery;
import ua.step.example.practica.flashlight.Flashlight;
import ua.step.example.practica.flashlight.SomeFlashlight;

import org.junit.Test;

public class TestBaterry {

	/**
	 * Одноразовая батарейка
	 */
	class DisposableBattery implements Battery {

		private boolean full = true;

		@Override
		public boolean getVoltage() {
			if (full) {
				full = false;
				return true;
			}
			return full;
		}
	}

	@Test
	public void testDischargeNewBattery() {
		Battery battery = new DisposableBattery();

		Flashlight flashlight = new SomeFlashlight(battery);
		// не светит так как не включен
		assertFalse(flashlight.isShines());

		// включаем светит
		flashlight.swithOn();
		assertTrue(flashlight.isShines());

		// вывключаем не светит
		flashlight.swithOff();
		assertFalse(flashlight.isShines());

		// включаем второй раз не светит
		flashlight.swithOn();
		assertFalse(flashlight.isShines());
	}

	@Test
	public void testBadBattery() {
		// создаем разряженную батарейку
		Battery battery = new Battery() {
			@Override
			public boolean getVoltage() {
				return false;
			}
		};

		Flashlight flashlight = new SomeFlashlight(battery);
		assertFalse(flashlight.isShines());

		flashlight.swithOn();
		assertFalse(flashlight.isShines());
	}

	@Test
	public void testNoGetPowerIfDoubleSwithOn() {
		Battery battery = new DisposableBattery();

		Flashlight flashlight = new SomeFlashlight(battery);
		assertFalse(flashlight.isShines());

		flashlight.swithOn();
		assertTrue(flashlight.isShines());

		flashlight.swithOn();
		assertTrue(flashlight.isShines());
	}

	@Test
	public void testNoBatteryNoLight() {
		Flashlight flashlight = new SomeFlashlight(null);

		assertFalse(flashlight.isShines());

		flashlight.swithOn();

		assertFalse(flashlight.isShines());
	}

	@Test
	public void integrationTestGetPowerFormNewChinaBattery() {
		Battery battery = new ChinaBattery();

		Flashlight flashlight = new SomeFlashlight(battery);

		assertFalse(flashlight.isShines());

		flashlight.swithOn();

		assertTrue(flashlight.isShines());
	}

	@Test
	public void integrationTestGetPowerFormNewDuracelBattery() {
		Battery battery = new DuracelBattery();

		Flashlight flashlight = new SomeFlashlight(battery);

		assertFalse(flashlight.isShines());

		for (int i = 0; i < DuracelBattery.MAX_POWER; i++) {
			flashlight.swithOn();
			assertTrue(flashlight.isShines());
			flashlight.swithOff();
		}
		flashlight.swithOn();
		assertFalse(flashlight.isShines());
	}
}
