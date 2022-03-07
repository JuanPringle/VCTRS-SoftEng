public class Owner {
	private int OwnerID;
	private String VehicleInfo;
	private String ResidencyTime;
	public Owner(int OwnerID, String VehicleInfo, String ResidencyTime){
		this.OwnerID=OwnerID;
		this.VehicleInfo=VehicleInfo;
		this.ResidencyTime=ResidencyTime;
	}
	public int getOwnerID(){
		return OwnerID;
	}
	public String getVehicleInfo(){
		return VehicleInfo;
	}
	public String getResidencyTime(){
		return ResidencyTime;
	}
	
	public String toString(){
		return "OwnerID:" + OwnerID + "\nVehicle Info:" + VehicleInfo + "\nResidency Time:" + ResidencyTime;

	}
}
