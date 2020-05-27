package ctrl;

import java.util.ArrayList;

import model.Customer;

public interface CustomerCtrIF {

	Customer createCustomer(String name, String phoneNo, String email, String address, String country, String cardNo,
			int zipcode, String city) throws DataAccessException;

	ArrayList<Customer> getAllCustomers();

}