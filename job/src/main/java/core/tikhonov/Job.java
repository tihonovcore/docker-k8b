package core.tikhonov;

public class Job {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("job started");

        Thread.sleep(2000);

        System.out.println("job successfully finished");
    }
}
