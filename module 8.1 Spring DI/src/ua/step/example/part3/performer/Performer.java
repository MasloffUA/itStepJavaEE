package ua.step.example.part3.performer;

/**
 *
 * Умеющий выступать на сцене с каким то номером
 *
 */
public interface Performer {
	// выступление
	void perform() throws PerformanceException;
}
