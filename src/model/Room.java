package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class Room {
	protected double price;
	protected int roomNumber;
	protected Calendar lastCleaned;
	
	
	public Room() {
		
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
	
	
}
