

public class Job {
    int id;
    double jobDuration;
    double completionTime;
    String deadline;
    
    public Job(int id, String deadline, double duration){
        this.id = id;
        this.deadline = deadline;
        this.jobDuration = duration;
        
    }

    public String toString(){
        return "ID: " + id + "\nDuration: " + jobDuration + "\nDeadline: " + deadline ;
    }


}
