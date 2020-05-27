package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Booking;
import model.HotelRoom.ROOM_TYPE;

public interface BookingDBIF {

	List<Booking> getAllBookings() throws DataAccessException;

	ROOM_TYPE stringToEnum(String s);

	void addBooking(Booking currentBooking) throws DataAccessException;

}