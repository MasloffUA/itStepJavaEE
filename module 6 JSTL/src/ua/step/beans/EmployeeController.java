package ua.step.beans;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
	private List<Employee> employes = new ArrayList<>();
	private Integer totalSalary = 0;

	public List<Employee> getEmployes() {
		return employes;
	}
	
	public void addEmployee(Employee employee){
		employes.add(employee);
	}
	
	public void addSalary(int s) {
		synchronized (totalSalary) {
			totalSalary+=s;
		}
	}

	public Integer getTotalSalary() {
		return totalSalary;
	}
	
	
}