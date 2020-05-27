package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ctrl.DataAccessException;
import model.Customer;

public class CustomerDB implements CustomerDBIF {
	private static CustomerDB instance;
	private Connection con;
	private ArrayList<Customer> customers = new ArrayList<>();
	private static final String createCustomerString = "INSERT INTO sp2_Customer(customerName, customerPhoneNo, email, address, country, cardNo, zipcode) VALUES(?,?,?,?,?,?,?)";
	private PreparedStatement createCustomer;
	
	public CustomerDB() throws DataAccessException {
		this.con = DBConnection.getInstance().getConnection();
		try {
			createCustomer = con.prepareStatement(createCustomerString, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new DataAccessException("Could not create customer", e);
		}
	}
	
	public static CustomerDB getInstance() throws DataAccessException {
		if(instance == null) {
			instance = new CustomerDB();
		}
		return instance;
	}

	public void createCustomer(Customer c) throws DataAccessException {
		try {
			createCustomer.setString(1, c.getName());
			createCustomer.setString(2, c.getPhoneNo());
			createCustomer.setString(3, c.getEmail());
			createCustomer.setString(4, c.getAddress());
			createCustomer.setString(5, c.getCountry());
			createCustomer.setString(6, c.getCardNo());
			createCustomer.setInt(7, c.getZipCode());
			int customerId = DBConnection.getInstance().executeInsertWithIdentity(createCustomer);
			c.setCustomerId(customerId);
		} catch (SQLException e) {
			throw new DataAccessException("Could not Create Customer", e);
		}
		
		
	}
	public ArrayList<Customer> getAllCustomers() throws DataAccessException {
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Customer INNER JOIN sp2_Zipcode on sp2_Customer.zipcode = sp2_Zipcode.zipcode");
			while(rs.next()) {
				Customer c = new Customer(rs.getString("customerName"), rs.getString("customerPhoneNo"), rs.getString("email"), rs.getString("address"), rs.getString("country"), rs.getString("cardNo"), rs.getInt("zipcode"), rs.getString("city"));
				c.setCustomerId(rs.getInt("customerId"));
				customers.add(c);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all customers", e);
		}
		return customers;
	}

}
