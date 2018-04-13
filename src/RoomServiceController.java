import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RoomServiceController {
	private static final String SEPARATOR ="|";
	
	private static ArrayList<FoodService> fSDbList() {
		ArrayList<FoodService> dbList = new ArrayList<FoodService>();
		try {
			dbList=readFS("fooditem.txt");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dbList;
	}
	
	private static ArrayList<FoodService> readFS(String filename) throws IOException{
		ArrayList stringArray=(ArrayList)DatabaseCreator.read(filename);
		ArrayList<FoodService> readList = new ArrayList<FoodService>();
		if(stringArray !=null) {
			for(int i = 0; i<stringArray.size();i++) {
				String st=(String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st, SEPARATOR);
				int fId=Integer.parseInt(star.nextToken().trim());
				String fName = star.nextToken().trim();
				double price = Double.parseDouble(star.nextToken().trim());
				String foodDetails = star.nextToken().trim();
				FoodService fs = new FoodService(fId,fName,price,foodDetails);
				readList.add(fs);
			}
			return readList;
		}
		else
			return readList;
	}	

	/*private static void saveFS(String filename, List alr) throws IOException {
		ArrayList<String> alw = new ArrayList<String>();
	    for (int i = 0 ; i < alr.size() ; i++) {
				FoodService food = (FoodService)alr.get(i);
				
				StringBuilder st =  new StringBuilder() ;
				st.append(food.);
				st.append(SEPARATOR);	
				alw.add(st.toString()) ;
			}
        try {
			DatabaseCreator.write(filename,alw);
        }catch(Exception e) {
        	System.out.println("DATA NOT SAVED DUE TO ERROR.");
        }
	}*/
		
	public static void viewMenu() {
		//print menu
		ArrayList<FoodService> menuList = fSDbList();
		FoodService food;
		for(int i=0;i<menuList.size();i++) {
			food=menuList.get(i);
			System.out.println(food.showMenu());
			System.out.println("");
		}
	}
	
	/*public static int OrderItems(String[] itemNqty) {
		ArrayList<FoodService> dbList=fSDbList();
		FoodService food;
		int itemNo,qty;
		String[] str;
		for(int i=0;i<dbList.size();i++) {
			food=dbList.get(i);
			for(int j=0;j<itemNqty.length;j++) {
				str=itemNqty[j].split(",");
				itemNo=Integer.parseInt(str[0]);
				qty=Integer.parseInt(str[1]);
				if(itemNo==food.fId) {
					
				}
			}
		}
		return 1;
	}*/
}
