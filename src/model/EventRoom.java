package model;

public class EventRoom extends Room {
	private int maxCapacity;
	private int minCapacity;
	private int noOfBuffets;

	public EventRoom(int maxCapacity, int minCapacity, int noOfBuffets) {
		super();
		this.maxCapacity = maxCapacity;
		this.minCapacity = minCapacity;
		this.noOfBuffets = noOfBuffets;
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

	public int getnoOfBuffets() {
		return noOfBuffets;
	}

	public void setnoOfBuffets(int noOfBuffets) {
		this.noOfBuffets = noOfBuffets;
	}
	
	
}
