
public class Client {
	private int ClientID;
	private String approximateTime;
	private String jobDeadline;
	
	public Client(int ClientID, String approximateTime, String jobDeadline){
		this.ClientID=ClientID;
		this.approximateTime=approximateTime;
		this.jobDeadline=jobDeadline;
	}
	public int getClientID(){
		return ClientID;
	}
	public String getApproximateTime(){
		return approximateTime;
	}
	public String getJobDeadline(){
		return jobDeadline;
	}
	public String toString(){
		return "Client ID: " + ClientID + "\nDuration: " + approximateTime + "\nDeadline:" + jobDeadline;
	}
	
}
