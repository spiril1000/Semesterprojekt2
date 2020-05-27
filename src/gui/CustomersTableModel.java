package gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import ctrl.CustomerCtr;
import ctrl.CustomerCtrIF;
import ctrl.RoomCtr;
import ctrl.RoomCtrIF;
import model.ConferenceRoom;
import model.Customer;
import model.Room;

public class CustomersTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerCtrIF cctr = new CustomerCtr();
	private List<Customer> allRooms = cctr.getAllCustomers(); //HUSK UPDATE BOOKINGER PÅ ROOMS
	private List<Customer> data = allRooms;
	private static final String[] COL_NAMES = {"Navn", "Email", "Telefon"};
	
	public CustomersTableModel() {
		
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
		Customer c = data.get(row);	
		
		switch (col) {
			case 0:
				return c.getName();
			case 1:
				return c.getEmail();
			case 2:
				return c.getPhoneNo();
			default:
				return null;
		}
	}
}
