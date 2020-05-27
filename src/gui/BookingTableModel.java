package gui;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.DateUtil;

import ctrl.BookingCtr;
import ctrl.BookingCtrIF;
import model.Booking;

public class BookingTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingCtrIF bctr = new BookingCtr();
	private List<Booking> allBookings = bctr.getAllBookings();
	private List<Booking> data = allBookings;
	private static final String[] COL_NAMES = {"Booking nr", "Type","Navn", "Email" ,"Start","Slut","Total"};
	
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
	
	public void filterOngoing() {
		Date now = java.util.Calendar.getInstance().getTime();
		ArrayList<Booking> filteredList = new ArrayList<>();
		for(Booking b : allBookings) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if((b.getTimeStart().getTime().before(now)||fmt.format(now.getTime()).equals(fmt.format(b.getTimeStart().getTime())))&&(b.getTimeEnd().getTime().after(now)||fmt.format(now.getTime()).equals(fmt.format(b.getTimeEnd().getTime())))) {
				filteredList.add(b);
			}
		}
		data = filteredList;
	}
	
	public void filterForward() {
		Date now = java.util.Calendar.getInstance().getTime();
		ArrayList<Booking> filteredList = new ArrayList<>();
		for(Booking b : allBookings) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if(b.getTimeStart().getTime().after(now)||fmt.format(now.getTime()).equals(fmt.format(b.getTimeStart().getTime()))) {
				filteredList.add(b);
			}
		}
		data = filteredList;
	}
	
	public void filterWeek() {
		Date now = java.util.Calendar.getInstance().getTime();
		ArrayList<Booking> filteredList = new ArrayList<>();
		for(Booking b : allBookings) {
			long daysBetween = ChronoUnit.DAYS.between(now.toInstant(), b.getTimeStart().toInstant());
			if(daysBetween <= 7 && daysBetween>-1) {
				filteredList.add(b);
			}
		}
		data = filteredList;
	}
	
	public void filterMonth() {
		Date now = java.util.Calendar.getInstance().getTime();
		ArrayList<Booking> filteredList = new ArrayList<>();
		for(Booking b : allBookings) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			long daysBetween = ChronoUnit.DAYS.between(now.toInstant(), b.getTimeStart().toInstant());
			if(daysBetween <= 31 && daysBetween>-1) {
				filteredList.add(b);
			}
		}
		data = filteredList;
	}
	
	public void filterCustom(Date startDate, Date endDate) {
		ArrayList<Booking> filteredList = new ArrayList<>();
		for(Booking b : allBookings) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if((b.getTimeStart().getTime().after(startDate)||fmt.format(startDate).equals(fmt.format(b.getTimeStart().getTime()))) && (b.getTimeStart().getTime().before(endDate)||fmt.format(startDate).equals(fmt.format(b.getTimeEnd().getTime())))) {
				filteredList.add(b);
			}
		}
		data = filteredList;
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Booking b = data.get(row);
		SimpleDateFormat days = 
				       new SimpleDateFormat("dd-MM-yyyy k:mm");		
		
		switch (col) {
			case 0:
				return b.getBookingId();
			case 1:
				return b.getR().getClass().getName().substring(6);
			case 2:
				return b.getC().getName();
			case 3:
				return b.getC().getEmail();
			case 4:
				return days.format(b.getTimeStart().getTime());
			case 5:
				return days.format(b.getTimeEnd().getTime());
			case 6:
				return b.getTotalPrice();
			default:
				return null;
		}
	}
}
