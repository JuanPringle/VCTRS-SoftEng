import java.util.LinkedList;
import java.util.Queue;

public class Job {
    private Queue <Vehicle> assignedVehicles;
    private int redundancyLevel;
    public Job(){
        assignedVehicles = new LinkedList<>();
        redundancyLevel = 0;
    }

    void setRedundancy(int newValue){
        redundancyLevel = newValue;
    }

    void addJob(Vehicle v){
        assignedVehicles.add(v);
        if(assignedVehicles.size() > redundancyLevel){
            assignedVehicles.remove();
        }
    }

}
