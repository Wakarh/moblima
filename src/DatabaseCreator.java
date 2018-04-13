import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseCreator {

	public static void fileChecker() {
		int x;
		String fileName = "room.txt";
		File file = new File(fileName);
		if (!file.exists()){
			CreateFile(fileName);
			addroom();
			System.out.println("Room database does not exist. Creating one now....");
		}
		fileName = "guest.txt";
		file = new File(fileName);
		if (!file.exists()){
			CreateFile(fileName);
			System.out.println("Guest database does not exist. Creating one now....");
		}
		fileName = "reservation.txt";
		file = new File(fileName);
		if (!file.exists()){
			CreateFile(fileName);
			System.out.println("Reservation database does not exist. Creating one now....");
		}
	}
	
	private static void addroom()
	{
		List alw = new ArrayList();
		StringBuilder st =  new StringBuilder();
		
		for(int y = 0; y<5; y++)
		{
	
			for(int x = 0; x<3; x++)
			{
				Room rm = new Room(RoomType.SINGLE, RoomStatus.VACANT, BedType.SINGLE, false, false, false, (201+x)+(y*100));
				st = new StringBuilder();
				st.append(rm.getall());
				alw.add(st.toString());
				
			}
			for (int x = 0; x<3;  x++)
			{
				Room rm = new Room(RoomType.DOUBLE, RoomStatus.VACANT, BedType.SINGLE, false, false, false, (204+x)+(y*100));
				st = new StringBuilder();
				st.append(rm.getall());
				alw.add(st.toString());
			}
			
			for (int x = 0; x<3; x++)
			{
				Room rm = new Room(RoomType.DELUXE, RoomStatus.VACANT, BedType.SINGLE, false, false, false, (207+x)+(y*100));
				st = new StringBuilder();
				st.append(rm.getall());
				alw.add(st.toString());
			}
		}
		for(int x = 0; x<3; x++)
		{
			Room rm = new Room(RoomType.VIP, RoomStatus.VACANT, BedType.SINGLE, false, false, false, 701+x);
			st = new StringBuilder();
			st.append(rm.getall());
			alw.add(st.toString());
		}
		try {
			write("room.txt",alw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void CreateFile(String file)
	{
			try {
				PrintWriter writer = new PrintWriter(file, "UTF-8");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void write(String fileName, List data) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i =0; i < data.size() ; i++) {
		      	out.println((String)data.get(i));
			}
		}
		finally {
		    out.close();
		}
	}
	
	public static List read(String fileName) throws IOException {
		List data = new ArrayList() ;
		Scanner scanner = new Scanner(new FileInputStream(fileName));
			try {
			  while (scanner.hasNextLine()){
			    data.add(scanner.nextLine());
			  }
			}
			finally{
			  scanner.close();
			}
			return data;
		}
	

}
