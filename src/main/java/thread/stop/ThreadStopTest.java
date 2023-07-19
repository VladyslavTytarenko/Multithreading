package thread.stop;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ThreadStopTest {

    public void test() throws InterruptedException {
        Thread communicationThread = new Thread(() -> {
            try {
                while (!currentThread().isInterrupted()) {
                    doRequest();
                }
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        });
        communicationThread.start();

        Thread stoppingThread = new Thread(() -> {
            if (isServerShouldBeStopped()) {
                stopServer();
                communicationThread.interrupt();
            }
        });
        SECONDS.sleep(5);
        stoppingThread.start();
    }

    private void doRequest() throws InterruptedException {
        System.out.println("\nRequest was sent...");
        SECONDS.sleep(1);
        System.out.println("Response was received...");
    }

    private void stopServer() {
        System.out.println("Server was stopped");
    }

    private boolean isServerShouldBeStopped() {
        return true;
    }
}
