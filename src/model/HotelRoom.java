package model;

public class HotelRoom extends Room {

public enum ROOM_TYPE {
	SUITE,
	SUPERIOR,
	COMFORT,
	HF_ROOM,
	STANDARD,
	APARTMENT
	};		
private ROOM_TYPE roomType;
private boolean isFamily;
private int singleBedQty;
private int doubleBedQty;

public HotelRoom(int singleBedQty, int doubleBedQty, boolean isFamily, ROOM_TYPE roomType) {
	super();
	this.singleBedQty = singleBedQty;
	this.doubleBedQty = doubleBedQty;
	this.isFamily = isFamily; // Family = +én køjeseng, dvs to ekstra sengepladser
	this.roomType = roomType;
	
//	switch (roomType) {
//		case STANDARD:
//			if (singleBedQty == 1) {
//				setPrice(775);
//			}
//			if (doubleBedQty == 1 || (doubleBedQty + singleBedQty >= 2)) { //HVIS enten dobbeltseng eller ekstra senge = 845
//				setPrice(845);
//			}
//			break;
//		case SUPERIOR:
//			setPrice(1045);
//			break;
//		case COMFORT:
//			setPrice(1045);
//			break;
//		case HF_ROOM:
//			setPrice(1045);
//			break;
//		case APARTMENT:
//			setPrice(1945);
//			break;
//		case SUITE:
//			setPrice(2445);
//			break;	
//		default:
//			setPrice(0); 
//	}
}

public ROOM_TYPE getRoomType() {
	return roomType;
}

public void setRoomType(ROOM_TYPE roomType) {
	this.roomType = roomType;
}

public int getSingleBedQty() {
	return singleBedQty;
}

public void setSingleBedQty(int singleBedQty) {
	this.singleBedQty = singleBedQty;
}

public int getDoubleBedQty() {
	return doubleBedQty;
}

public void setDoubleBedQty(int doubleBedQty) {
	this.doubleBedQty = doubleBedQty;
}

public boolean getIsFamily() {
	return isFamily;
}

public void setIsFamily(boolean isFamily) {
	this.isFamily = isFamily;
}
	

}
