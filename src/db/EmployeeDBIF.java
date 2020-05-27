package db;

import java.util.ArrayList;

import ctrl.DataAccessException;
import model.Employee;

public interface EmployeeDBIF {

	ArrayList<Employee> getAllEmployees() throws DataAccessException;

}