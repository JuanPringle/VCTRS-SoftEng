import java.util.Date;

public class Vehicle {
	private Date Schedule;
	private String info;

	public Date getSchedule() {
		return Schedule;
	}
	public void arrive () {
		System.out.print("Vehicle has arrived");
	}
	
	public void depart() {
		System.out.print("Vehicle has departed");
	}
}
