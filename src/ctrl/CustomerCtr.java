package ctrl;

import java.sql.SQLException;
import java.util.ArrayList;

import db.CustomerDB;
import db.RoomDB;
import model.Customer;

public class CustomerCtr implements CustomerCtrIF {
	private CustomerDB cDB;
	
	public CustomerCtr() {
			try {
				cDB = CustomerDB.getInstance();
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public Customer createCustomer(String name, String phoneNo, String email, String address, String country, String cardNo, int zipcode, String city) throws DataAccessException {
		Customer c = new Customer(name, phoneNo, email, address, country, cardNo, zipcode, city);
		cDB.createCustomer(c);
		return c;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customers = null;
		try {
			customers = cDB.getAllCustomers();
		} catch (DataAccessException e) {
			System.out.println("Could not find all customers");
		}
		return customers;
	}

}
