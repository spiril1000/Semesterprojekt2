package gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ctrl.RoomCtr;
import ctrl.RoomCtrIF;
import model.EventRoom;
import model.Room;

public class EventRoomsTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoomCtrIF roomctr = new RoomCtr();
	private List<EventRoom> allRooms = roomctr.getAllEventRooms(); //HUSK UPDATE BOOKINGER PÅ ROOMS
	private List<EventRoom> data = allRooms;
	private static final String[] COL_NAMES = {"V�relsesnummer", "Min Kapacitet", "Max Kapacitet", "Pris pr. time"};
	
	public EventRoomsTableModel() {
		
	}
	
	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int col) {
		return COL_NAMES[col];
	}
	
	@Override
	public int getRowCount() {
		int res = 0;
		if (data != null) {
			res = data.size();
		}
		return res;
	}
	

	
//	public void filterWeek() {
//		Date now = java.util.Calendar.getInstance().getTime();
//		ArrayList<Booking> filteredList = new ArrayList<>();
//		for(Booking b : allBookings) {
//			long daysBetween = ChronoUnit.DAYS.between(now.toInstant(), b.getTimeStart().toInstant()); // MÅSKE
//			if(daysBetween <= 7 && daysBetween>-1) {
//				filteredList.add(b);
//			}
//		}
//		data = filteredList;
//	}
	
	
	
	@Override
	public Object getValueAt(int row, int col) {
		EventRoom r = (EventRoom) data.get(row);	
		
		switch (col) {
			case 0:
				return r.getRoomNumber();
			case 1:
				return r.getMinCapacity();
			case 2:
				return r.getMaxCapacity();
			case 3:
				return r.getPrice();
				
			default:
				return null;
		}
	}
}
