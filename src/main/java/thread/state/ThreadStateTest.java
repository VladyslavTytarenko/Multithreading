package thread.state;

import static java.lang.Thread.currentThread;

public class ThreadStateTest {

    private final static String SHOW_STATE_MESSAGE_TEMPLATE = "%s : %s\n";
    private static final int SECOND = 1000;

    public void test() throws InterruptedException {
        Thread mainThread = currentThread();
        Thread thread = new Thread(() -> {
            try {
                mainThread.join(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            showThreadState(currentThread());
        });
        thread.start();
        Thread.sleep(SECOND);
        showThreadState(thread);
    }

    private void showThreadState(Thread thread) {
        System.out.printf(SHOW_STATE_MESSAGE_TEMPLATE, thread.getName(), thread.getState().name());
    }
}
