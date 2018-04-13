import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class ReservationController {
	private static final String SEPARATOR = "|";
	private static final Format TIMEFORMATTER = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	private static final Format DATEFORMATTER = new SimpleDateFormat("dd-MM-yyyy");
	
	private static ArrayList<Reservation> reservationDbList() {
		ArrayList<Reservation> resList = new ArrayList<Reservation>();
		try {
			resList=readRes("reservation.txt");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resList;
	}
	
	private static ArrayList<Reservation> readRes(String filename) throws IOException, ParseException {
		ArrayList stringArray = (ArrayList)DatabaseCreator.read(filename);
		ArrayList<Reservation> readList = new ArrayList<Reservation>();
		int flag=0;//check if the table is not empty
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				if (star.hasMoreTokens() == true)
				{
					int resId = Integer.parseInt(star.nextToken().trim());
					String guestIdentity = star.nextToken().trim();
					int roomNum = Integer.parseInt(star.nextToken().trim());
					int partyNo = Integer.parseInt(star.nextToken().trim());
					Date checkIn = new SimpleDateFormat("dd-MM-yyyy").parse(star.nextToken().trim()); 
					Date checkOut = new SimpleDateFormat("dd-MM-yyyy").parse(star.nextToken().trim());
					Date timeExpire = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").parse(star.nextToken());
					String resStatus = star.nextToken().trim();
					Reservation res = new Reservation(resId, partyNo, checkIn, checkOut, timeExpire,ReservationStatus.valueOf(resStatus), guestIdentity, roomNum);
					readList.add(res) ;
				}
				else
				{
					i = stringArray.size();
					flag=1;
				}
        }
        if(flag==0) {
        	return readList;
        	
        }       
       	else {
        	System.out.println("Reservation database is Empty.");
        	return readList;
        }
	}
	
	public static ArrayList<Reservation> searchConfirmedRes(String guestId) {
		ArrayList<Reservation> dbList = reservationDbList();
		ArrayList<Reservation> confirmedList = new ArrayList<Reservation>();
		String curDate = DATEFORMATTER.format(MainController.getCurDate());
		Reservation res=null;
		String guestCheckInDate;
		if(guestId.equals("*")) {
			for(int i=0;i<dbList.size();i++) {
				res=dbList.get(i);
				guestCheckInDate = DATEFORMATTER.format(res.getCheckIn());
				/*LEFT OUT EXPIRE TIME CHECK BECAUSE AT THE START OF CHECKIN ALREADY
				 * AUTOMATIC CHANGE ALL THE EXPIRED RESERVATIONS TO STATUS: EXPIRED
				 */
				if(res.getResStatus()==ReservationStatus.CONFIRMED && guestCheckInDate.equals(curDate)) {
					confirmedList.add(res);
				}
			}
			return confirmedList;
		}
		else {
			for(int i=0;i<dbList.size();i++) {
				res = dbList.get(i);
				guestCheckInDate = DATEFORMATTER.format(res.getCheckIn());
				if(res.getGuestIden().equals(guestId) && curDate.equals(guestCheckInDate) && res.getResStatus()==ReservationStatus.CONFIRMED) {
					confirmedList.add(res);
				}
			}
			return confirmedList;
		}
	}
	
	private static void saveRes(String filename, ArrayList al) throws IOException {
		ArrayList<String> alw = new ArrayList<String>();
        for (int i = 0 ; i < al.size() ; i++) {
				Reservation res = (Reservation)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(((Integer)res.getResId()).toString());
				st.append(SEPARATOR);
				st.append(res.getGuestIden().trim());
				st.append(SEPARATOR);
				st.append(((Integer)res.getRoomNum()).toString());
				st.append(SEPARATOR);
				st.append(((Integer)res.getPartyNum()).toString());
				st.append(SEPARATOR);
				String checkIn = DATEFORMATTER.format(res.getCheckIn());
				st.append(checkIn);
				st.append(SEPARATOR);
				String checkOut = DATEFORMATTER.format(res.getCheckOut());
				st.append(checkOut);
				st.append(SEPARATOR);
				String expiry = TIMEFORMATTER.format(res.getTimeExpire());
				st.append(expiry);
				st.append(SEPARATOR);
				st.append(res.getResStatus());
				alw.add(st.toString());
			}
        try {
			DatabaseCreator.write(filename,alw);
        }catch(Exception e) {
        	System.out.println("DATA NOT SAVED DUE TO ERROR.");
        }
    }
	
	public static Reservation updateStatus(int resId, ReservationStatus status) {
		ArrayList<Reservation> resList = reservationDbList();
		int dataLoc=0,flag=0;
		Reservation res=null;
		for(int i=0;i<resList.size();i++) {
			res = resList.get(i);
			if(resId==res.getResId()) {
				dataLoc=i;
				flag=1;
			}
		}
		if(flag==1) {
			res=resList.get(dataLoc);
			res.setResStatus(status);
			resList.set(dataLoc, res);
			try {
				saveRes("reservation.txt",resList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
		else return null;
	}
	
	public static void checkExpiry() {
		Date curTime = MainController.getCurDate();
		Reservation res=null;
		ArrayList<Reservation> resList = reservationDbList();
		for(int i=0;i<resList.size();i++) {
			res=resList.get(i);
			if(curTime.compareTo(res.getTimeExpire())>0 && res.getResStatus()!=ReservationStatus.EXPIRED) {//if currTime is after timeExpire
				res.setResStatus(ReservationStatus.EXPIRED);
				resList.set(i, res);
			}
		}
		try {
			saveRes("reservation.txt",resList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
