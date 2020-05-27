package ctrl;

import java.util.ArrayList;
import java.util.List;

import db.BookingDB;
import db.BookingDBIF;
import db.CustomerDB;
import db.EmployeeDB;
import db.EmployeeDBIF;
import gui.Item;
import model.Booking;
import model.ConferenceRoom;
import model.Customer;
import model.Employee;
import model.EventRoom;

public class BookingCtr implements BookingCtrIF{
	private Booking currentBooking;
	private CustomerCtrIF cctr = new CustomerCtr();
	private RoomCtrIF rctr = new RoomCtr();
	private BookingDBIF bdb;
	
	public BookingCtr() {
		try {
			bdb = BookingDB.getInstance();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createBooking(Employee e) {
		currentBooking = new Booking(e);
	}
	
	@Override
	public ArrayList<Employee> getAllEmployees() {
		EmployeeDBIF edb = new EmployeeDB();
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			employees = edb.getAllEmployees();
		} catch (DataAccessException e) {
			System.out.println("Could not find all Employees");
		}
		return employees;
	}
	
	@Override
	public Booking getCurrentBooking() {
		return currentBooking;
	}

	@Override
	public Customer createCustomer(String name, String phoneNo, String email, String address, String country, String cardNo, int zipCode, String city) throws DataAccessException {
		return cctr.createCustomer(name, phoneNo, email, address, country, cardNo, zipCode, city);
	}

	@Override
	public void setCustomer(Customer c) {
		currentBooking.setC(c);
		System.out.println(currentBooking.getC().getEmail());
	}

	@Override
	public List<Booking> getAllBookings() {
		List<Booking> bookings = new ArrayList<>();
		try {
			bookings = bdb.getAllBookings();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookings;
	}

	@Override
	public ConferenceRoom getConferenceRoomById(int id) throws DataAccessException {
		return rctr.getConferenceRoomById(id);
	}
	
	@Override
	public EventRoom getEventRoomById(int id) throws DataAccessException {
		return rctr.getEventRoomById(id);
	}

	@Override
	public void addBooking(Booking currentBooking) throws DataAccessException {
		bdb.addBooking(currentBooking);
	}
}