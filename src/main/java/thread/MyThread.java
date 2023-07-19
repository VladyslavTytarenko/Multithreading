package thread;

/**
 * Custom thread
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(currentThread().getName());
    }
}
