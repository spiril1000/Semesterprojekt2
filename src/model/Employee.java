package model;

public class Employee {
	private String name;
	private int employeeNo;
	private String phoneNo;
	
	public Employee(String name, int employeeNo, String phoneNo) {
		super();
		this.name = name;
		this.employeeNo = employeeNo;
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}
	
	
}
