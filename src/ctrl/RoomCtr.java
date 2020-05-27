package ctrl;

import java.sql.SQLException;

import java.util.List;

import db.RoomDB;
import db.RoomDBIF;
import model.Booking;
import model.ConferenceRoom;
import model.EventRoom;
import model.Room;
import model.HotelRoom;

public class RoomCtr implements RoomCtrIF {
	RoomDBIF rDB; 
	
	public RoomCtr() {
		try {
			rDB = RoomDB.getInstance();
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public HotelRoom getHotelRoomById(int id) {
		HotelRoom room = null;
		try {
			room = rDB.getHotelRoomById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return room;
	}
	
	@Override
	public List<Room> getAllRooms() {
		List<Room> rooms = null;
		try {
			rooms = rDB.getAllRooms();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms; 
	}
	
	/*
	public List<HotelRoom> getAllHotelRooms() {
		List<Room> rooms = getAllRooms();
		List<HotelRoom> filtered = new ArrayList<HotelRoom>();
		
		for (Room r : rooms) {
			if (r instanceof HotelRoom) {
				filtered.add((HotelRoom) r);
			}
		}
		return filtered;
	}
	*/ 
	
	@Override
	public void updateRoomBookings() {
		List<Booking> bookings=  new BookingCtr().getAllBookings();
		try {
		List<Room> rooms;
		rooms = rDB.getAllRooms();
		for (Room r : rooms) {
			r.getConnectedBookings().clear();
			for (Booking b : bookings) {
				if (r == b.getR()) {
					r.addBooking(b);
				}
			} 
		 }
		
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
	}

	@Override
	public List<ConferenceRoom> getAllConferenceRooms() {
		List<ConferenceRoom> rooms = null;
		try {
			rooms = rDB.getAllConferenceRooms();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms;
	}
	
	@Override
	public List<EventRoom> getAllEventRooms() {
		List<EventRoom> rooms = null;
		try {
			rooms = rDB.getAllEventRooms();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms;
	}
	
	@Override
	public List<HotelRoom> getAllHotelRooms() {
		List<HotelRoom> rooms = null;
		try {
			rooms = rDB.getAllHotelRooms();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public ConferenceRoom getConferenceRoomById(int id) throws DataAccessException {
		return rDB.getConferenceRoomById(id);
	}
	
	@Override
	public EventRoom getEventRoomById(int id) throws DataAccessException {
		return rDB.getEventRoomById(id);
	}
	
}



/*
public List<HotelRoom> getAllHotelRooms() {
	List<Room> rooms = getAllRooms();
	List<HotelRoom> filtered = new ArrayList<HotelRoom>();
	
	for (Room r : rooms) {
		if (r instanceof HotelRoom) {
			filtered.add((HotelRoom) r);
		}
	}
	return filtered;
*/