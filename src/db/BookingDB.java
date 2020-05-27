package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import model.HotelRoom.ROOM_TYPE;
import model.Room;

public class BookingDB implements BookingDBIF {
	private static BookingDBIF instance;
	private Connection con;
	private ArrayList<Booking> bookings = new ArrayList<>();
	private static final String addBookingString = "INSERT INTO sp2_Booking(peopleQty,timeStart,timeEnd,totalPrice,comment,customerId,roomNumber,employeeId) VALUES(?,?,?,?,DEFAULT,?,?,?)";
	private PreparedStatement addBooking;
	
	public BookingDB() throws DataAccessException {
		this.con = DBConnection.getInstance().getConnection();
		try {
			addBooking= con.prepareStatement(addBookingString);
		} catch (SQLException e) {
			throw new DataAccessException("Could not create booking", e);
		}
	}
	
	public static BookingDBIF getInstance() throws DataAccessException {
		if(instance == null) {
			instance = new BookingDB();
		}
		return instance;
	}

	@Override
	public List<Booking> getAllBookings() throws DataAccessException {
		bookings.clear();
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM sp2_Booking INNER JOIN sp2_Customer ON sp2_Booking.customerId = sp2_Customer.customerId\r\n" + 
					"INNER JOIN sp2_Employee ON sp2_Booking.employeeId = sp2_Employee.employeeId\r\n" + 
					"INNER JOIN sp2_Room ON sp2_Booking.roomNumber = sp2_Room.roomNumber\r\n" + 
					"LEFT JOIN sp2_HotelRoom ON sp2_HotelRoom.roomNumber = sp2_Booking.roomNumber\r\n" + 
					"LEFT JOIN sp2_ConferenceRoom on sp2_Booking.roomNumber = sp2_ConferenceRoom.roomNumber \r\n" + 
					"LEFT JOIN sp2_EventRoom on sp2_Booking.roomNumber = sp2_EventRoom.roomNumber");
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
				Employee e = new Employee(rs.getString("employeeName"), rs.getInt("employeeId"), rs.getString("employeePhoneNo"));
				Customer c = new Customer(rs.getString("customerName"), rs.getString("customerPhoneNo"), rs.getString("email"), rs.getString("address"), rs.getString("country"), rs.getString("cardNo"), 9000, "Aalborg");
				c.setCustomerId(rs.getInt("customerId"));
				Booking b = new Booking(e);
				b.setC(c);
				b.setComment(rs.getString("comment"));
				b.setPeopleQty(rs.getInt("peopleQty"));
				b.setR(r);
				Calendar timeStart = Calendar.getInstance();
				Calendar timeEnd = Calendar.getInstance();
				long startDate = rs.getDate("timeStart").getTime();
				long startTime = rs.getTime("timeStart").getTime() + 3600000;
				timeStart.setTimeInMillis(startDate + startTime);
				long endDate = rs.getDate("timeEnd").getTime();
				long endTime = rs.getTime("timeEnd").getTime() + 3600000;
				timeEnd.setTimeInMillis(endDate + endTime);
				b.setTimeStart(timeStart);
				b.setTimeEnd(timeEnd);
				b.setTotalPrice(rs.getDouble("totalPrice"));
				b.setBookingId(rs.getInt("bookingId"));
				bookings.add(b);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all bookings", e);
		}
	return bookings;
	}
	
	public void addBooking(Booking currentBooking) throws DataAccessException {
		try {
			addBooking.setInt(1,currentBooking.getPeopleQty());
			addBooking.setTimestamp(2, new java.sql.Timestamp(currentBooking.getTimeStart().getTime().getTime()));
			addBooking.setTimestamp(3, new java.sql.Timestamp(currentBooking.getTimeEnd().getTime().getTime()));
			addBooking.setDouble(4, currentBooking.getTotalPrice());
			//addBooking.setString(5, null);
			addBooking.setInt(5, currentBooking.getC().getCustomerId());
			addBooking.setInt(6,currentBooking.getR().getRoomNumber());
			addBooking.setInt(7,currentBooking.getE().getEmployeeNo());
			addBooking.execute();
		} catch (SQLException e) {
			throw new DataAccessException("Could not create new booking", e);
		}
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
}
