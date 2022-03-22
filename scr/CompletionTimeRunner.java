//to test if the completion time calculator works
public class CompletionTimeRunner {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Job jobOne = new Job(14);
        Job jobTwo = new Job(14);
        Job jobThree = new Job(14);
        Job jobFour = new Job(14);

        controller.submitJob(jobOne);
        controller.submitJob(jobTwo);
        controller.submitJob(jobThree);
        controller.submitJob(jobFour);

        controller.calculateCompletionTime();
        System.out.println(jobThree.completionTime);

    }
}
