package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class Room {
	protected double price;
	protected int roomNumber;
	protected Calendar lastCleaned;
	protected ArrayList<Booking> connectedBookings = new ArrayList<>();
	
	
	public Room() {
		
	}
	
	public void addBooking(Booking b) {
		connectedBookings.add(b);
	}
	
	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Calendar getLastCleaned() {
		return lastCleaned;
	}



	public void setLastCleaned(Calendar lastCleaned) {
		this.lastCleaned = lastCleaned;
	}



	public int getRoomNumber() {
		return roomNumber;
	}



	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public ArrayList<Booking> getConnectedBookings() {
		return connectedBookings; //Skal returnere originalen for .clear()
	}
	
}
