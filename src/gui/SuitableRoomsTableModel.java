package gui;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ctrl.BookingCtr;
import ctrl.CustomerCtr;
import ctrl.EmployeeCtr;
import ctrl.RoomCtr;
import ctrl.RoomCtrIF;
import model.Booking;
import model.Customer;
import model.HotelRoom;
import model.Room;

public class SuitableRoomsTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoomCtrIF roomctr = new RoomCtr();
	private List<HotelRoom> allRooms = roomctr.getAllHotelRooms(); //HUSK UPDATE BOOKINGER PÅ ROOMS
	private List<HotelRoom> data = allRooms;
	private static final String[] COL_NAMES = {"Værelsesnummer", "Type" , "Enkeltsenge", "Dobbeltsenge", "Pris"};
	
	public SuitableRoomsTableModel() {
		roomctr.updateRoomBookings();
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
		HotelRoom r = (HotelRoom) data.get(row);	
		
		switch (col) {
			case 0:
				return r.getRoomNumber();
			case 1:
				return r.getRoomType();
			case 2:
				return r.getSingleBedQty();
			case 3:
				return r.getDoubleBedQty();
			case 4:
				return r.getPrice();
				
			default:
				return null;
		}
	}
}
