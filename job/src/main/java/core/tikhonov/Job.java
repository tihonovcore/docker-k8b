package core.tikhonov;

public class Job {

    public static void main(String[] args) throws InterruptedException {
        var index = System.getenv("JOB_COMPLETION_INDEX");
        var sleepingMs = Long.parseLong(System.getenv("SLEEPING_MS"));

        System.out.println("job started, index=" + index);

        Thread.sleep(sleepingMs);

        if (index != null && Integer.parseInt(index) % 2 == 0) {
            System.exit(1);
        }

        System.out.println("job successfully finished, index=" + index);
    }
}
