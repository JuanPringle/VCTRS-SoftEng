public class Vehicle {
    private int id;
    private String model;
    private double residencyDuration;

    public Vehicle(int id, String model, double duration ){
        this.id = id;
        this.model = model;
        this.residencyDuration = duration;
    }

    public String toString(){
        return "ID: " + id + "/nDuration: " + residencyDuration + "/nModel: " + model ;
    }
}
