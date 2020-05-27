package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ctrl.DataAccessException;
import model.Booking;
import model.ConferenceRoom;
import model.Customer;
import model.Employee;
import model.EventRoom;
import model.HotelRoom;
import model.Room;
import model.HotelRoom.ROOM_TYPE;

public class RoomDB implements RoomDBIF {
	private static RoomDBIF instance;
	private ArrayList<Room> rooms = new ArrayList<>();
	private ArrayList<ConferenceRoom> conferenceRooms = new ArrayList<>();
	private ArrayList<EventRoom> eventRooms = new ArrayList<>();
	private ArrayList<HotelRoom> hotelRooms = new ArrayList<>();
	private static final String findHotelRoomByIdString = "SELECT * FROM sp2_Room INNER JOIN sp2_HotelRoom ON sp2_Room.roomNumber = sp2_HotelRoom.roomNumber where sp2_Room.roomNumber = ?";
	private static final String findConferenceRoomByIdString = "SELECT * FROM sp2_Room INNER JOIN sp2_ConferenceRoom ON sp2_Room.roomNumber = sp2_ConferenceRoom.roomNumber where sp2_Room.roomNumber = ?";
	private static final String findEventRoomByIdString = "SELECT * FROM sp2_Room INNER JOIN sp2_EventRoom ON sp2_Room.roomNumber = sp2_EventRoom.roomNumber where sp2_Room.roomNumber = ?";
	private PreparedStatement findHotelRoomById;
	private PreparedStatement findConferenceRoomById;
	private PreparedStatement findEventRoomById;
	private Connection con;
	
	// static method to create instance of Singleton in CustomerDB
	
	public RoomDB() throws DataAccessException, SQLException {
		this.con = DBConnection.getInstance().getConnection();
		findHotelRoomById = con.prepareStatement(findHotelRoomByIdString);
		findConferenceRoomById = con.prepareStatement(findConferenceRoomByIdString);
		findEventRoomById = con.prepareStatement(findEventRoomByIdString);
	}
	
	public static RoomDBIF getInstance() throws DataAccessException, SQLException {
		if(instance == null) {
			instance = new RoomDB();
		}
		return instance;
	}
	

	@Override
	public List<Room> getAllRooms() throws DataAccessException {
		rooms.clear();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Room LEFT JOIN sp2_HotelRoom ON sp2_Room.roomNumber = sp2_HotelRoom.roomNumber \r\n" + 
					"					LEFT JOIN sp2_ConferenceRoom on sp2_Room.roomNumber = sp2_ConferenceRoom.roomNumber\r\n" + 
					"					LEFT JOIN sp2_EventRoom on sp2_Room.roomNumber = sp2_EventRoom.roomNumber");
			while(rs.next()) {
				Room r;
				if(rs.getString("roomType") != null) {
					r = new HotelRoom(rs.getInt("singleBedQty"), rs.getInt("doubleBedQty"), false, stringToEnum(rs.getString("roomType")));
				}
				else if(rs.getString("numberOfBuffets") != null) {
					r = new EventRoom(rs.getInt("eventMaxCapacity"), rs.getInt("eventMinCapacity"), rs.getInt("numberOfBuffets"));
				}
				else {
					r = new ConferenceRoom(rs.getInt("confMaxCapacity"), rs.getInt("confMinCapacity"));
				}
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
				rooms.add(r);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all rooms", e);
		}
	return rooms;
	}
	
	@Override
	public HotelRoom getHotelRoomById(int id) throws SQLException {
		HotelRoom r = null;
		this.findHotelRoomById.setInt(1, id);
		ResultSet rs = findHotelRoomById.executeQuery();
		while(rs.next()) {
			r = new HotelRoom(rs.getInt("singleBedQty"), rs.getInt("doubleBedQty"), false, stringToEnum(rs.getString("roomType")));
			r.setRoomNumber(rs.getInt("roomNumber"));
			r.setPrice(rs.getDouble("price"));
		}
		return r;
	}

	@Override
	public ROOM_TYPE stringToEnum(String s) {
		switch (s) {
			case "SUITE":
			return ROOM_TYPE.SUITE;
			
			case "APARTMENT":
				return ROOM_TYPE.APARTMENT;
			
			case "COMFORT":
				return ROOM_TYPE.COMFORT;

			case "HF_ROOM":
				return ROOM_TYPE.HF_ROOM;
				
			case "STANDARD":
				return ROOM_TYPE.STANDARD;
				
			case "SUPERIOR":
				return ROOM_TYPE.SUPERIOR;
		default:
			return null;
		
		}
	}

	@Override
	public List<ConferenceRoom> getAllConferenceRooms() throws DataAccessException {
		conferenceRooms.clear();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Room INNER JOIN sp2_ConferenceRoom on sp2_Room.roomNumber = sp2_ConferenceRoom.roomNumber");
			while(rs.next()) {
				ConferenceRoom r = new ConferenceRoom(rs.getInt("confMaxCapacity"), rs.getInt("confMinCapacity"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
				conferenceRooms.add(r);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all Conferencerooms", e);
		}
		return conferenceRooms;
	}
	
	@Override
	public List<EventRoom> getAllEventRooms() throws DataAccessException {
		eventRooms.clear();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Room INNER JOIN sp2_EventRoom on sp2_Room.roomNumber = sp2_EventRoom.roomNumber");
			while(rs.next()) {
				EventRoom r = new EventRoom(rs.getInt("eventMaxCapacity"), rs.getInt("eventMinCapacity"), rs.getInt("numberOfBuffets"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
				eventRooms.add(r);
			} 
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all Eventrooms", e);
		}
		return eventRooms;
	}
	
	@Override
	public List<HotelRoom> getAllHotelRooms() throws DataAccessException {
		hotelRooms.clear();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Room INNER JOIN sp2_HotelRoom on sp2_Room.roomNumber = sp2_HotelRoom.roomNumber");
			while(rs.next()) {
				HotelRoom r = new HotelRoom(rs.getInt("singleBedQty"), rs.getInt("doubleBedQty"),false, stringToEnum(rs.getString("roomType")));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
				hotelRooms.add(r);
			} 
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all Hotelrooms", e);
		}
		return hotelRooms;
		
	}

	@Override
	public ConferenceRoom getConferenceRoomById(int id) throws DataAccessException {
		ConferenceRoom r = null;
		try {
			this.findConferenceRoomById.setInt(1, id);
			ResultSet rs = findConferenceRoomById.executeQuery();
			while(rs.next()) {
				r = new ConferenceRoom(rs.getInt("confMaxCapacity"), rs.getInt("confMinCapacity"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Can't find ConferenceRoom", e);
		}
		return r;
	}
	
	@Override
	public EventRoom getEventRoomById(int id) throws DataAccessException {
		EventRoom r = null;
		try {
			this.findEventRoomById.setInt(1, id);
			ResultSet rs = findEventRoomById.executeQuery();
			while(rs.next()) {
				r = new EventRoom(rs.getInt("eventMaxCapacity"), rs.getInt("eventMinCapacity"), rs.getInt("numberOfBuffets"));
				r.setRoomNumber(rs.getInt("roomNumber"));
				r.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Can't find EventRoom", e);
		}
		return r;
	}

}
