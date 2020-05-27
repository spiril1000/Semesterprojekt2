package ctrl;

import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.ConferenceRoom;
import model.Customer;
import model.Employee;
import model.EventRoom;

public interface BookingCtrIF {

	void createBooking(Employee e);

	ArrayList<Employee> getAllEmployees();

	Booking getCurrentBooking();

	Customer createCustomer(String name, String phoneNo, String email, String address, String country, String cardNo,
			int zipCode, String city) throws DataAccessException;

	void setCustomer(Customer c);

	List<Booking> getAllBookings();

	ConferenceRoom getConferenceRoomById(int id) throws DataAccessException;

	EventRoom getEventRoomById(int id) throws DataAccessException;

	void addBooking(Booking currentBooking) throws DataAccessException;

}