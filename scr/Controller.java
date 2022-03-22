import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    private Queue<Vehicle> Vehicles; 
    private Queue<Job> Jobs;
    private ArrayList <Checkpoint> Checkpoints;

    public Controller(){
        Vehicles = new LinkedList<Vehicle>(); 
        Jobs = new LinkedList<Job>();
        Checkpoints= new ArrayList<Checkpoint>();
    }

    void submitJob(Job j){
        Jobs.add(j);
    }

    void recruitVehicle(Vehicle v){
        Vehicles.add(v);
    }

    void addCheckpoint(Checkpoint c){
        Checkpoints.add(c);
    }

    
    void calculateCompletionTime(){
        double totalDuration = 0;
        for (Job currentJob : Jobs) {
            totalDuration+= currentJob.jobDuration;
            currentJob.completionTime = totalDuration;
        }
    }

    Queue<Job> getJobs(){
        return this.Jobs;
    }


    
}
