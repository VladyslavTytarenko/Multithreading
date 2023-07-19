package thread.daemon;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class ThreadDaemonTest {

    public void test() {
        System.out.println("Main thread is daemon: " + currentThread().isDaemon());

        Thread thread = new Thread(new Task());
        thread.setDaemon(true);
        thread.start();

        System.out.println("Thread is daemon: " + thread.isDaemon());
        System.out.println("Main thread is finished.");

        Thread firstThread = new Thread(() -> {
            try {
                System.out.println(currentThread().getName() + " : " + currentThread().isDaemon());
                Thread secondThread = new Thread(() -> System.out.println(currentThread().getName() + " : " + currentThread().isDaemon()));
                secondThread.start();
                secondThread.join();
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        });
        firstThread.setDaemon(true);
        firstThread.start();
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("I'm working...");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
