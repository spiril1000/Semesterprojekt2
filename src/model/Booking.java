package model;

import java.util.Calendar;

public class Booking {
	private int bookingId;
	private int peopleQty;
	private Calendar timeStart;
	private Calendar timeEnd;
	private double totalPrice;
	private String comment;
	private Customer c;
	private Employee e;
	private Room r;
	
	public Booking(Employee e) {
		super();
		this.e = e;
	}

	public int getPeopleQty() {
		return peopleQty;
	}

	public void setPeopleQty(int peopleQty) {
		this.peopleQty = peopleQty;
	}

	public Calendar getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Calendar timeStart) {
		this.timeStart = timeStart;
	}

	public Calendar getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Calendar timeEnd) {
		this.timeEnd = timeEnd;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public Employee getE() {
		return e;
	}

	public void setE(Employee e) {
		this.e = e;
	}

	public Room getR() {
		return r;
	}

	public void setR(Room r) {
		this.r = r;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
}
