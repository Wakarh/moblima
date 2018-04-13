import java.util.Date;

public class RoomService {
	private int serviceId,qty,reservationId;//totalprice of each serviceName
	private double price;
	private String remarks, serviceName;//for if there are more type of service to be implemented in the future
	private Date orderedTime;
	
	RoomService(){
		
	}
	
	RoomService(double price, String serviceName,int serviceId,String remarks, Date orderedTime, int reservationId){
		this.price=price;
		this.serviceName=serviceName;
		this.serviceId=serviceId;
		this.orderedTime=orderedTime;
		this.remarks=remarks;
		this.reservationId = reservationId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}
}
