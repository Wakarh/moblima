import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelApp {
	private static final int EXIT_VALUE = 6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseCreator.fileChecker();
		Reservation res;
		Guest guest;
		int trycatchflag=0;
		int choice, choice2 = 0;
		Scanner sc = new Scanner(System.in);
		/*ROOM DATABASE ONLY SHOW THE CURRENT STATUS OF ROOM. 
		 * EVERYTIME YOU WANT TO CHECK FOR AVAILABILITY OF ROOM
		 * DO LOGIC IN RESERVATION TO EDIT ROOM FOR THAT SPECIFIC DAY.
		 */
		
		/*
		 * SHOULD THE CODE BE DONE IN A WAY WHERE ANYTHING RELATED DATABASE
		 * WE HAVE TO GO THROUGH THE RELEVANT CLASSES FIRST RATHER THAN THE CONTROLLER?
		 * A WAY IS TO CREATE AN ARRAY OF GUESTS AND AFTER A CERTAIN TASK OR BEFORE THE APP
		 * IS CLOSED DATA IS SAVED. THIS IS TO PROMOTE HIGH COHESION
		 */
		do {
			trycatchflag=0;
			try {
				do {
					System.out.println("HOTEL RESERVATION AND PAYMENT SYSTEM\n"
							+ "========================================\n(1) Check-In");//used to update guest details and room to occupied etc
					System.out.println("(2) Check-Out");//change room to vacant and reservation details and payment too etc
					System.out.println("(3) Reservation");//make reservation and view reservation details of guest etc
					System.out.println("(4) Room Service");//order room service and maybe update menu items
					System.out.println("(5) Room Details");//print room status statistics report/ update room details and whatever create rom details is.
					System.out.println("(6) Exit");
					System.out.print("\nEnter the number of your choice: ");
					
					choice = sc.nextInt();
					sc.nextLine();
					switch (choice) {
					//CHECKIN 
					case 1://should whole of case 1 put in main controller since this whole case use all sorts of controllers
					 int whileFlag=0;
					 while(whileFlag==0){
						 /*checkExpiry() is done after pressing check-in because this is the main area
						  * where having expiry matters. If there are other places that requires
						  * expiry checking you can run the method there too.
						  */
						 ReservationController.checkExpiry();
						 
						 System.out.println("\nPick an Option\n"
						 				  + "================\n\t(1)Walk-In\n\t(2)Already had Reservations");
						 System.out.print("\nYour choice: ");
						 choice2=sc.nextInt();
						 if(choice2==1) {
							 //FOR WALKIN SO NEED TO WAIT TILL RESEVATION IS DONE MAYBE GOT METHOD I CAN CALL
						 }
						 else if(choice2==2) {
							 sc.nextLine();
							 System.out.print("Guest Identity (type * to search for all reservations): ");
							 String guestId=sc.next();
							 ArrayList<Reservation> confirmedList = ReservationController.searchConfirmedRes(guestId);
							 if(confirmedList.size()!=0) {
								 if(!guestId.equals("*")) {
									 guest = GuestController.searchGuestIden(guestId);
									 System.out.println(guest.getName()+" has a reservation today. Information is shown below: \n");
									 System.out.println("\trID|Identity|roomNum|party|Check-In|Check-Out|Expiry Time|status|");
									 for(int i=0;i<confirmedList.size();i++) {
										 res=confirmedList.get(i);
										 System.out.println("\t"+res.getall());
									 }
								 }
								 else if(guestId.equals("*")) {
									 System.out.println("\trID|Identity|roomNum|party|Check-In|Check-Out|Expiry Time|status|");
									 for(int i=0;i<confirmedList.size();i++) {
										 res=confirmedList.get(i);
										 System.out.println("\t"+res.getall());
									 }
								 }
								 /*ONE GUEST CAN MAKE MORE THEN 1 RESERVATIONS FOR A SPECIFIC DAY BECAUSE IF THE NUMBER OF PEOPLE FOLLOWING
								  * MIGHT BE TOO BIG SO GUEST BOOK TWO ROOMS, THATS WHY I USE ARRAY OF RESID TO CHECK IN BOTH ROOMS
								  */
								 sc.nextLine();
								 System.out.println("\nSelect Reservation IDs that you want to Check-In (e.g. resId1<space>resId2): ");
								 String strResId = sc.nextLine();
								 String[] strarrResId = strResId.split(" ");
								 System.out.print("Do you want to Check-In Reservation ID: "+strResId+"(Y/N)? ");
								 char checkIn = sc.next().charAt(0);
								 if(checkIn=='Y'||checkIn=='y') {
									 //update database
									 int resChecked=0, roomChecked=0;
									 Reservation resUpdate=null;
									 for(int i=0;i<strarrResId.length;i++) {
										 int resId=Integer.parseInt(strarrResId[i]);
										 resUpdate = ReservationController.updateStatus(resId,ReservationStatus.CHECKEDIN); 
										 roomChecked = RoomController.resUpdateRoom(resUpdate.getRoomNum());
									 }
									 if(resUpdate!=null && roomChecked==1) {
										 whileFlag=1;
										 System.out.println("Check-In Complete.\n");
									 }
									 else {
										 System.out.println("Check-In Failed\n");
										 whileFlag=0;
									 }
								 }
								 else
									 whileFlag=0;
							 }
							 else
								 System.out.println("There are no reservations made for the specified identity");
						 }
						 else {
							 System.out.println("Invalid Number, please try again");
							 whileFlag=0;
						 }
					}break;
					//RESERVATION
					case 3:
						System.out.println("");break;
					//ROOM SERVICE
					case 4:
						System.out.println("Room Service\n"
										 + "============\nHere is the Menu for viewing : ");
						int pass=0;
						do {
							RoomServiceController.viewMenu();
							System.out.print("select your item Number(e.g. itemNo1,Qty<SPACE>itemNo2,Qty): ");
							String[] itemNqty=sc.nextLine().split(" ");
							System.out.println(itemNqty[0]+" "+itemNqty[1]);
						//	pass = RoomserviceController.OrderItems(itemNqty);
							if(pass==1)
								System.out.println("Your items has been placed and is currently being prepared");
							else
								System.out.println("An error occured. Please try again.");
						}while(pass==0);
						break;						
					//ROOM DETAILS
					case 5:
						 do {
							 System.out.print("\nROOM DETAILS\n"
							 				+ "============\n\t(1) Print Room Status Statistic report\n\t(2) Create Room for Expansion\n\t(3) Update Room Details\n\t(4) Back\n\nEnter your choice: ");
							 choice2 = sc.nextInt();
							 
							 switch(choice2) {
							 	case 1:
							 		RoomController.printRoomStats();
							 		break;
							 	case 2:
							 		System.out.print("Create a unit Number(e.g. 204): ");
							 		int unit = sc.nextInt();
							 		Room room = RoomController.FindRoom(unit);
							 		int passfail=0;
							 		if(room==null) {
							 			System.out.println("Create Room Details\n"
												 + " ==================\n");
							 			System.out.print("Room Types: \n\t(1) SINGLE \n\t(2) DOUBLE \n\t(3) DELUXE \n\t(4) VIP SUITE\n\nSelect a ROOM Type: ");
							 			int rmtype=sc.nextInt();
							 			System.out.print("Bed Types: \n\t(1) SINGLE \n\t(2) DOUBLE \n\t(3) MASTER\n\nSelect a BED Type: ");
							 			int bdtype=sc.nextInt();
							 			passfail=RoomController.createRoom(unit,rmtype,bdtype);
							 			if(passfail==1)
							 				System.out.println("Room successfully created.");
							 			else
							 				System.out.println("There is an error. Please try again.");
							 		}
							 		else {
							 			System.out.println("Room already exist. Please try again");
							 			choice2=1;
							 		}
							 		break;
							 	case 3:
									int updated;
									do{
										System.out.print("Enter Room Number: ");
										int rmNum=sc.nextInt();
										//if updated=1 then is success if 0 the fail
										updated = RoomController.updateRoomDetails(rmNum);
										if(updated == 1)
											System.out.println("Success!");
									}while(updated==0);break;
								 default:break;
								}	
						    }while(choice2<4);
						break;
					 case EXIT_VALUE:System.out.println("TERMINATING.....");
					}
				} while (choice < EXIT_VALUE);
			}catch(InputMismatchException e) {
				System.out.println("You have keyed in an invalid character. Please start over");
				trycatchflag=1;
			}
			sc.nextLine();
		}while(trycatchflag==1);
		sc.close();
	}
}
