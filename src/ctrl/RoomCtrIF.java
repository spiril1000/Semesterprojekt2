package ctrl;

import java.util.List;

import model.ConferenceRoom;
import model.EventRoom;
import model.HotelRoom;
import model.Room;

public interface RoomCtrIF {

	HotelRoom getHotelRoomById(int id);

	List<Room> getAllRooms();

	List<ConferenceRoom> getAllConferenceRooms();

	List<EventRoom> getAllEventRooms();

	List<HotelRoom> getAllHotelRooms();

	ConferenceRoom getConferenceRoomById(int id) throws DataAccessException;

	EventRoom getEventRoomById(int id) throws DataAccessException;

}