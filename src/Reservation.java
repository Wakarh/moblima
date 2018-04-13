import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

enum ReservationStatus
{
	CONFIRMED, WAITLIST, CHECKEDIN, EXPIRED
}

public class Reservation {

	
	private int reservationID, roomNumber, partyNo;
	private String guestIDen;
	private Date checkIn, checkOut, timeExpire;
	private ReservationStatus status;
	
	Reservation(int reservationID, int partyNo, Date checkIn, Date checkOut, Date timeExpire, ReservationStatus status , String guestIden, int rmNo) throws IOException
	{
		this.reservationID = reservationID;
		this.partyNo = partyNo;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.timeExpire = timeExpire;
		this.status = status;
		guestIDen = guestIden;
		this.roomNumber = rmNo;
	}
	
	public String getall() {
		Format dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Format timeFormatter = new SimpleDateFormat("hh:mm a");
		return " "+reservationID+" |"+guestIDen+"| "+roomNumber+" |  "+partyNo+"  |"+dateFormatter.format(checkIn)+"|"+dateFormatter.format(checkOut)+"|"+timeFormatter.format(timeExpire)+"|"+status+"|";
	}
	
	public String getGuestIden() {
		return guestIDen;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public int getRoomNum() {
		return roomNumber;
	}
	
	public int getPartyNum() {
		return partyNo;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public Date getTimeExpire() {
		return timeExpire;
	}
	
	public ReservationStatus getResStatus() {
		return status;
	}
	
	public int getResId()
	{
		return reservationID;
	}
	
	public void setResStatus(ReservationStatus status) {
		this.status = status;
	}
}
