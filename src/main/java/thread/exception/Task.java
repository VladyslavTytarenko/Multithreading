package thread.exception;

import static java.lang.Thread.currentThread;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.printf("Thread %s is daemon %s\n", currentThread().getName(), currentThread().isDaemon());
        throw new RuntimeException("Exception!");
    }
}
