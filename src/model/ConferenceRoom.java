package model;

public class ConferenceRoom extends Room {
	private int maxCapacity;
	private int minCapacity;
	
	public ConferenceRoom(int maxCapacity, int minCapacity) {
		super();
		this.maxCapacity = maxCapacity;
		this.minCapacity = minCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getMinCapacity() {
		return minCapacity;
	}

	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}
	
}
