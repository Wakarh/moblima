import java.util.Date;

public class MainController {
	
	public static Date getCurDate() {
        Date curDate = new Date();
        return curDate;
    }
	
	public static void syncDatabase() {
		ReservationController.checkExpiry();
		//update room with reservation as of current date, have to be done ever so often to sync
	}

}
