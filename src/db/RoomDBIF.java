package db;

import java.sql.SQLException;
import java.util.List;

import ctrl.DataAccessException;
import model.ConferenceRoom;
import model.EventRoom;
import model.HotelRoom;
import model.Room;
import model.HotelRoom.ROOM_TYPE;

public interface RoomDBIF {

	List<Room> getAllRooms() throws DataAccessException;

	HotelRoom getHotelRoomById(int id) throws SQLException;

	ROOM_TYPE stringToEnum(String s);

	List<ConferenceRoom> getAllConferenceRooms() throws DataAccessException;

	List<EventRoom> getAllEventRooms() throws DataAccessException;

	List<HotelRoom> getAllHotelRooms() throws DataAccessException;

	ConferenceRoom getConferenceRoomById(int id) throws DataAccessException;

	EventRoom getEventRoomById(int id) throws DataAccessException;

}