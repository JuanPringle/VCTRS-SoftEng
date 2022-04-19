public class VehicleOwner extends User {
	private int OwnerID;
	private String[] VehicleInfo;
	private String ResidencyTime;
	private Vehicle[] VehiclesOwned;
	
	public VehicleOwner(int OwnerID, String VehicleInfo[], String ResidencyTime, Vehicle[] VehiclesOwned) {
		super(OwnerID);
		this.VehicleInfo=VehicleInfo;
		this.ResidencyTime=ResidencyTime;
		this.VehiclesOwned=VehiclesOwned;
	}
	
	public int getOwnerID() {
		return OwnerID;
	}
	
	public String[] getVehicleInfo() {
		return VehicleInfo;
	}
	
	public String getResidencyTime() {
		return ResidencyTime;
	}
	
	public void rent(Vehicle vehicle) {
		
	}
}
