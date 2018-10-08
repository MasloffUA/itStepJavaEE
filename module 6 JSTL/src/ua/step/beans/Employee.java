package ua.step.beans;

public class Employee {
	private int id;
	private String lastName;
	private String firstName;
	private String depart;
	private int salary;
	
	public Employee() {
	}
	
	public Employee(int id, String lastName, String firstName, int salary, String depart) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.salary = salary;
		this.depart = depart;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}