
public class Client extends User {
	private String approximateTime;
	private String jobDeadline;
	private Job[] jobSubmitted;
	
	public Client(int ClientID, String approximateTime, String jobDeadline, Job[] jobSubmitted){
		super(ClientID);
		this.approximateTime=approximateTime;
		this.jobDeadline=jobDeadline;
		this.jobSubmitted=jobSubmitted;

	}
	public int getClientID(){
		return this.id;
	}
	public String getApproximateTime(){
		return approximateTime;
	}
	public String getJobDeadline(){
		return jobDeadline;
	}
	public void submitJob(Job job) {
		
	}
}
