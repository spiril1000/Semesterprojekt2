package model;

import java.util.ArrayList;


public class tryMe {
	private ArrayList<Room> rooms = new ArrayList<Room>();

	public tryMe() {
		Room e = (Room) new EventRoom(20, 4, 6);
		Room c = (Room)  new ConferenceRoom(20, 2);
		
		rooms.add(e);
		rooms.add(c);
		System.out.println(e.getClass());
		System.out.println(c.getClass());
		go();
	}

	public static void main(String[] args) {
		tryMe t = new tryMe();
	}

	private void go() {
		System.out.println(rooms.get(0).getPrice());
		System.out.println(rooms.get(1).getPrice());
	}
	

}
