
public class FoodService extends RoomService{
	private String foodName,foodDetails;
	private int fId;
	double price;//might remove because super class should already have this
	
	FoodService(){
		super();
	}
	FoodService(int fId,String foodName, double price, String foodDetails){
		this.fId=fId;
		this.foodName=foodName;
		this.foodDetails=foodDetails;
	}
	

	public String showMenu() {
		return "("+fId+") "+foodName+"\t $"+price+"\n    Description: "+foodDetails;
	}
	
	public int getfId() {
		return fId;
	}
	
	public void setfId(int fId) {
		this.fId = fId;
	}
	
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodDetails() {
		return foodDetails;
	}

	public void setFoodDetails(String foodDetails) {
		this.foodDetails = foodDetails;
	}
}
