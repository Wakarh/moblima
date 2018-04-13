import java.io.IOException;
import java.util.*;

enum RoomStatus {
	/*
	 * RESERVED WILL NEVER BE USED IN PRINT STATISTIC REPORT BECAUSE RESERVED WILL ONLY BE SHOWN
	 * WHEN USER IS TRYING TO BOOK A DATE INBETWEEN THE DATES OF A PREVIOUS PERSON
	 */
	VACANT, OCCUPIED, RESERVED, MAINTENANCE
}

enum RoomType {
	SINGLE, DOUBLE, DELUXE, VIP
}

enum BedType{
	SINGLE, DOUBLE, MASTER
}

public class Room {

	private RoomType roomType;
	private RoomStatus roomStatus;
	private BedType bedType;
	private int roomNumber;
	private boolean wifi, facing, smoking;
	private Reservation[] res;
	
	Room(RoomType room, RoomStatus status, BedType bed, boolean wifi, boolean facing, boolean smoking, int roomNumber)
	{
		roomType = room;
		roomStatus = status;
		bedType = bed;
		this.wifi = wifi;
		this.facing = facing;
		this.smoking = smoking;
		this.roomNumber = roomNumber;
	}
	
	Room(int roomNumber, RoomType roomType, BedType bedType){
		this.roomNumber=roomNumber;
		this.roomType=roomType;
		roomStatus=RoomStatus.MAINTENANCE;
		this.bedType=bedType;
		wifi=false;
		smoking=false;
		facing=false;
	}
	
	public String getall()
	{
		return roomType+"|"+roomStatus+"|"+bedType+"|"+((Integer)roomNumber).toString()+"|"+wifi+"|"+facing+"|"+smoking;
	}
	
	public RoomType getRm()
	{
		return roomType;
	}
	
	public RoomStatus getStatus()
	{
		return roomStatus;
	}
	
	public BedType getBed()
	{
		return bedType;
	}
	public int getRmNumber()
	{
		return roomNumber;
	}
	
	public boolean getWifi()
	{
		return wifi;
	}
	
	public boolean getFacing()
	{
		return facing;
	}
	
	public boolean getSmoking()
	{
		return smoking;
	}
	
	public Reservation[] getRes() 
	{
		return res;
	}
	
	public void setRm(RoomType roomType)
	{
		this.roomType = roomType;
	}
	
	public void setStatus(RoomStatus status)
	{
		this.roomStatus = status;
	}
	
	public void getBed(BedType bedType)
	{
		this.bedType = bedType;
	}
	
	public void setWifi(boolean wifi)
	{
		this.wifi = wifi;
	}
	
	public void setFacing(boolean facing)
	{
		this.facing = facing;
	}
	
	public void setSmoking(boolean smoking)
	{
		this.smoking = smoking;
	}
	
	public void setRes(Reservation[] res)
	{
		this.res=res;
	}
	
}
