import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GuestController {
	
	private static final String SEPARATOR = "|";

	private static ArrayList<Guest> readGuest(String filename) throws IOException {
		ArrayList stringArray = (ArrayList)DatabaseCreator.read(filename);
		ArrayList<Guest> readList = new ArrayList<Guest>();
		int flag=0;//check if the table is not empty
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				if (star.hasMoreTokens() == true)
				{
					String name = star.nextToken().trim();
					String creditcard = star.nextToken().trim();
					String address = star.nextToken().trim();
					String country = star.nextToken().trim();
					Gender gender = Gender.valueOf(star.nextToken().trim());
					String identity = star.nextToken().trim();
					String nationality = star.nextToken().trim();
					int  contact = Integer.parseInt(star.nextToken().trim());
					Guest guest = new Guest(name, creditcard, address, country, gender, identity, nationality, contact);
					readList.add(guest) ;
					
				}
				else
				{
					i = stringArray.size();
					flag=1;
				}
        }
        if(flag==0)
        	return readList;
        else {
        	System.out.println("Guest database is Empty.");
        	return readList;
        }
	}
	
	public static ArrayList<Guest> searchGuest(String name) {
		
		int flag=0;
		ArrayList<Guest> guestDbList = new ArrayList<Guest>();
		try {
			guestDbList=readGuest("guest.txt");
		}catch(Exception e) {
			e.printStackTrace();
		}
		Guest guest;
		ArrayList<Guest> searchResults = new ArrayList<Guest>();
		if(!name.equals("*")) {
			for(int i=0;i<guestDbList.size();i++) {
				guest=(Guest)guestDbList.get(i);
				if(name.equals(guest.getName())) {
					searchResults.add(guest);
					flag=1;
				}
			}
		}
		else {
			for(int i=0;i<guestDbList.size();i++) {
				guest=(Guest)guestDbList.get(i);
				searchResults.add(guest);
			}
			flag=1;
		}
		if(flag==0 || searchResults == null)
			System.out.println("No Guest match Found!");
			return searchResults;
	}
	
	public static Guest searchGuestIden(String identity) {
		
		int flag=0, guestLoc=0;
		ArrayList<Guest> guestDbList = new ArrayList<Guest>();
		try {
			guestDbList=readGuest("guest.txt");
		}catch(Exception e) {
			e.printStackTrace();
		}
		Guest guest = null;
			for(int i=0;i<guestDbList.size();i++) {
				guest=(Guest)guestDbList.get(i);
				if(identity.equals(guest.getIdentity())) {
					guestLoc=i;
					i=guestDbList.size();
					flag=1;
				}
				else 
					flag=0;		
			}
		if(flag!=1) {
			return null;
		}
		else {
			guest=(Guest)guestDbList.get(guestLoc);
			return guest;
		}
	}
	
	private static void saveGuest(String filename, ArrayList al) throws IOException {
		ArrayList<String> alw = new ArrayList<String>();
        for (int i = 0 ; i < al.size() ; i++) {
				Guest guest = (Guest)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(guest.getName().trim());
				st.append(SEPARATOR);
				st.append(guest.getCreditcard().trim());
				st.append(SEPARATOR);
				st.append(guest.getAddress().trim());
				st.append(SEPARATOR);
				st.append(guest.getCountry().trim());
				st.append(SEPARATOR);
				st.append(guest.getGender());
				st.append(SEPARATOR);
				st.append(guest.getIdentity().trim());
				st.append(SEPARATOR);
				st.append(guest.getNationality().trim());
				st.append(SEPARATOR);
				st.append(((Integer)guest.getContact()).toString());
				alw.add(st.toString()) ;
			}
        try {
			DatabaseCreator.write(filename,alw);
        }catch(Exception e) {
        	System.out.println("DATA NOT SAVED DUE TO ERROR.");
        }
	}
	
	public static Guest enterGuestDetails () {
		Scanner sc = new Scanner(System.in);
		Gender gender;
		Guest guest;
		System.out.println("Enter Name");
		 String guestName = sc.nextLine();
		 System.out.println("Enter Credit Card");
		 String creditcard = sc.nextLine();
		 System.out.println("Enter Address");
		 String address = sc.nextLine();
		 System.out.println("Enter Country");
		 String country = sc.nextLine();
		 System.out.println("Enter Gender: 1 for male 2 for female");
		 int intGender = sc.nextInt();
		 if (intGender == 1)
			 gender = Gender.MALE;
		 else
			 gender = Gender.FEMALE;
		 System.out.println("Enter Identity Number");
		 sc.nextLine();
		 String identity = sc.nextLine();
		 System.out.println("Enter Nationality");
		 String nationality = sc.nextLine();
		 System.out.println("Enter Contact Number");
		 int contact = (int)sc.nextLong();
		 guest = new Guest(guestName, creditcard, address, country, gender, identity, nationality, contact);
		 sc.close();
		 return guest;
		
	}
}
