package ua.step.example.part3.performer;

public class Juggler implements Performer {
	private int beanBags = 3;
	private String name = "Дюк";
	public Juggler() {
	}

	public Juggler(int beanBags) {
		this.beanBags = beanBags;
	}

	public void perform() throws PerformanceException {
		System.out.println(name + " жонглирует " + beanBags + " шарами");
	}
}
