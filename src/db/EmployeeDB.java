package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ctrl.DataAccessException;
import gui.Item;
import model.Employee;

public class EmployeeDB implements CustomerDBIF, EmployeeDBIF {
	private ArrayList<Employee> employees = new ArrayList<>();
	private static EmployeeDBIF instance;
	private Connection con;
	
	public EmployeeDB() {
		this.con = DBConnection.getInstance().getConnection();
		}
	
	public static EmployeeDBIF getInstance() throws DataAccessException{
		if(instance == null) {
			instance = new EmployeeDB();
		}
		return instance;
		
	}
	
	
	@Override
	public ArrayList<Employee> getAllEmployees() throws DataAccessException {
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Employee");
			while(rs.next()) {
				Employee e = new Employee(rs.getString("employeeName"), rs.getInt("employeeId"), rs.getString("employeePhoneNo"));
				employees.add(e);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Can't find all customers", e);
		}
		return employees;
	}
}
