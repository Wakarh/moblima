import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RoomController {
	/*
	 * SORT THE PRINT ROOM STATUS STATISTICS REPORT ACCORDING TO LEVEL!!!
	 */
	
	private static final String SEPARATOR = "|";
	
	private static int dataloc;
	
	private static ArrayList<Room> roomDbList() {
		ArrayList<Room> roomList = new ArrayList<Room>();
		try {
			roomList=readRoom("room.txt");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	public static Room FindRoom(int roomNum) {
		Room room;
		ArrayList<Room> list = roomDbList();
		for (int j = 0 ; j < list.size() ; j++) {
			room = list.get(j);
			if(room.getRmNumber()==roomNum)
			{
				dataloc = j;
				return room;
			}
		}
		return null;//if cannot find room
	}
	
	public static int updateRoomDetails(int roomNum) {
		ArrayList<Room> writeList = roomDbList();
		Room room = FindRoom(roomNum);
		if(room == null) {
			System.out.println("Error! There is no room with such a number.");
			return 0;
		}
		room = changeDetails(room);
		System.out.println("Saving to database....");
		writeList.set(dataloc, room);
		try {
			saveRoom("room.txt", writeList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public static int createRoom(int roomNum, int rmtype, int bdtype) {
		ArrayList<Room> dbList=roomDbList();
		RoomType roomType=null;
		BedType bedType=null;
		int flag=0;
		switch(rmtype) {
		case 1: roomType=RoomType.SINGLE; break;
		case 2: roomType=RoomType.DOUBLE; break;
		case 3: roomType=RoomType.DELUXE; break;
		case 4: roomType=RoomType.VIP; break;
		default: flag=1;break;
		}
		switch(bdtype) {
		case 1: bedType=BedType.SINGLE; break;
		case 2: bedType=BedType.DOUBLE; break;
		case 3: bedType=BedType.MASTER; break;
		default: flag=1;break;
		}
		
		Room room = new Room(roomNum,roomType,bedType);
		System.out.print("\nCreated Room: ");
		room=changeDetails(room);
		dbList.add(room);
		try {
			saveRoom("room.txt",dbList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=1;
		}
		if(flag==0)
			return 1;
		else
			return 0;
	}
	
	private static void saveRoom(String filename, List alr) throws IOException {
		ArrayList<String> alw = new ArrayList<String>();
	    for (int i = 0 ; i < alr.size() ; i++) {
				Room room = (Room)alr.get(i);
				
				StringBuilder st =  new StringBuilder() ;
				st.append(room.getRm());
				st.append(SEPARATOR);
				st.append(room.getStatus());
				st.append(SEPARATOR);
				st.append(room.getBed());
				st.append(SEPARATOR);
				st.append(((Integer)room.getRmNumber()).toString().trim());
				st.append(SEPARATOR);
				st.append(Boolean.toString(room.getWifi()));
				st.append(SEPARATOR);
				st.append(Boolean.toString(room.getFacing()));
				st.append(SEPARATOR);
				st.append(Boolean.toString(room.getSmoking()));		
				alw.add(st.toString()) ;
			}
        try {
			DatabaseCreator.write(filename,alw);
        }catch(Exception e) {
        	System.out.println("DATA NOT SAVED DUE TO ERROR.");
        }
	}
	
	private static ArrayList<Room> readRoom(String filename) throws IOException{
		ArrayList stringArray=(ArrayList)DatabaseCreator.read(filename);
		ArrayList<Room> readList = new ArrayList<Room>();
		if(stringArray !=null) {
			for(int i = 0; i<stringArray.size();i++) {
				String st=(String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st, SEPARATOR);
				String roomType = star.nextToken().trim();
				String status = star.nextToken().trim();
				String bedType = star.nextToken().trim();
				int roomNum = Integer.parseInt(star.nextToken().trim());
				boolean wifi = Boolean.parseBoolean(star.nextToken().trim());
				boolean facing = Boolean.parseBoolean(star.nextToken().trim());
				boolean smoking = Boolean.parseBoolean(star.nextToken().trim());
				Room room = new Room(RoomType.valueOf(roomType),RoomStatus.valueOf(status),BedType.valueOf(bedType),wifi,facing,smoking,roomNum);
				readList.add(room);
			}
			return readList;
		}
		else
			return readList;
	}
	
	public static void printRoomStats() {
		ArrayList<Room> roomDbList = roomDbList();
		Room room;
		int single=0,duo=0,VIP=0,deluxe=0;
		int singleVacant=0,duoVacant=0,VIPVacant=0,deluxeVacant=0;
		String strSingle="",strDuo="",strVIP="",strDeluxe="";
		String strVac="",strOccu="",strMain="",strReserve="";
		
		for(int i=0;i<roomDbList.size();i++) {
			room=roomDbList.get(i);
			if(room.getRm() == RoomType.SINGLE) {
				single++;
				if(room.getStatus() == RoomStatus.VACANT) {
					strSingle+=room.getRmNumber()+",";
					singleVacant++;
				}
			}
			else if(room.getRm() == RoomType.DOUBLE) {
				duo++;
				if(room.getStatus() == RoomStatus.VACANT) {
					strDuo+=room.getRmNumber()+",";
					duoVacant++;
				}
			}
			else if(room.getRm() == RoomType.DELUXE) {
				deluxe++;
				if(room.getStatus() == RoomStatus.VACANT) {
					strDeluxe+=room.getRmNumber()+",";
					deluxeVacant++;
				}
			}
			else if(room.getRm() == RoomType.VIP) {
				VIP++;
				if(room.getStatus() == RoomStatus.VACANT) {
					strVIP+=room.getRmNumber()+",";
					VIPVacant++;
				}
			}
		}
		System.out.println("ROOM TYPE\n"
						 + "=========");
		System.out.println("Single:\t\tNumber:"+singleVacant+" out of "+single);
		System.out.println("\tRooms:"+strSingle);
		System.out.println("Double:\t\tNumber:"+duoVacant+" out of "+duo);
		System.out.println("\tRooms:"+strDuo);
		System.out.println("Deluxe:\t\tNumber:"+deluxeVacant+" out of "+deluxe);
		System.out.println("\tRooms:"+strDeluxe);
		System.out.println("VIP Suite:\tNumber:"+VIPVacant+" out of "+VIP);
		System.out.println("\tRooms:"+strVIP);
		
		for(int i=0;i<roomDbList.size();i++) {
			room=roomDbList.get(i);
			if(room.getStatus()==RoomStatus.VACANT) {
				strVac+=room.getRmNumber()+",";
			}
			if(room.getStatus()==RoomStatus.MAINTENANCE) {
				strMain+=room.getRmNumber()+",";
			}
			if(room.getStatus()==RoomStatus.OCCUPIED) {
				strOccu+=room.getRmNumber()+",";
			}
			if(room.getStatus()==RoomStatus.RESERVED) {
				strReserve+=room.getRmNumber()+",";
			}
		}
		System.out.println("\nROOM STATUS"
					   + "\n===========\n"
				+ "Vacant:");
		System.out.println("\tRooms:"+strVac);
		System.out.println("Occupied:");
		System.out.println("\tRooms:"+strOccu);
		System.out.println("Reserved:");
		System.out.println("\tRooms:"+strReserve);
		System.out.println("Maintenance:");
		System.out.println("\tRooms:"+strMain);
	}
	
	public static int resUpdateRoom(int roomNo) {
		ArrayList<Room> dbList = roomDbList(); 
		Room room=null;
		int flag=0;
		for(int i=0;i<roomDbList().size();i++) {
			room=dbList.get(i);
			if(room.getRmNumber() == roomNo) {
				room.setStatus(RoomStatus.OCCUPIED);
				dbList.set(i, room);
				flag=1;
			}
		}
		if(flag==0) {
			System.out.println("Room cannot be found. #resUpdateRoom#");
			return 0;
		}
		else {
			try {
				saveRoom("room.txt",dbList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}
	
	private static Room changeDetails(Room room){
		int statusflag=0,boolflag=0;
		char wifi, smoke, view;
		Scanner sc = new Scanner(System.in);
		System.out.println(room.getall());
		do {
			System.out.print("Is the room WIFI enabled?(Y/N) ");
			wifi = sc.next().charAt(0);
			if(wifi == 'Y'||wifi == 'y') {
				room.setWifi(true);
				boolflag=0;
			}
			else if((wifi == 'n'||wifi == 'N')) {
				room.setWifi(false);
				boolflag=0;
			}
			else {
				System.out.println("No such value, please try again.");
				boolflag=1;
			}
		}while(boolflag==1);
		
		//reset for others to use
		boolflag=0;
		do {
			System.out.print("Does the room have a view?(Y/N) ");
			view = sc.next().charAt(0);
			if(view == 'Y'||view == 'y') {
				room.setFacing(true);
				boolflag=0;
			}
			else if(view == 'N'||view == 'n') {
				room.setFacing(false);
				boolflag=0;
			}
			else {
				System.out.println("No such value, please try again.");
				boolflag=1;
			}
		}while(boolflag==1);
		
		//reset for others to use
		boolflag=0;
		do {
			System.out.print("Can you smoke in this room?(Y/N) ");
			smoke = sc.next().charAt(0);
			if(smoke == 'Y'||smoke == 'y') {
				room.setSmoking(true);
				boolflag=0;
			}
			else if(smoke == 'N'||smoke == 'n') {
				room.setSmoking(false);
				boolflag=0;
			}
			else {
				System.out.println("No such value, please try again.");
				boolflag=1;
			}
		}while(boolflag==1);

		do {
			System.out.print("Room Status:\n\t (1) RESERVED\n\t (2) VACANT\n\t (3) UNDER MAINTENANCE\n\t (4) OCCUPIED\nYour choice: ");
			int statusSel = sc.nextInt();
			switch(statusSel) {
				case 1:
					room.setStatus(RoomStatus.RESERVED);
					statusflag = 0;break;
				case 2:
					room.setStatus(RoomStatus.VACANT);
					statusflag = 0;break;
				case 3:
					room.setStatus(RoomStatus.MAINTENANCE);
					statusflag = 0;break;
				case 4:
					room.setStatus(RoomStatus.OCCUPIED);
					statusflag = 0;break;
				default:
					System.out.println("No such status, try again.\n");
					statusflag = 1;break;
				}

		}while(statusflag == 1);
		return room;
	}
	
}
