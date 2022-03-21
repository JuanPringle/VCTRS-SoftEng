import java.util.ArrayList;

public class Controller {
    private ArrayList <Vehicle> Vehicles= new ArrayList<Vehicle>(); 
    private ArrayList <Job> Jobs= new ArrayList<Job>();
    private ArrayList <Checkpoint> Checkpoints= new ArrayList<Checkpoint>();


    void addCheckpoint(Checkpoint c){
        Checkpoints.add(c);
    }

    void recruitVehicle(Vehicle v){
        Vehicles.add(v);
    }

    void addJob(Job j){
        Jobs.add(j);
    }

    void assignJob(Vehicle v, Job j){
        j.addJob(v);
    }


    
}
