package thread.priority;

import java.util.stream.IntStream;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;
import static java.lang.Thread.currentThread;

public class ThreadPriorityTest {

    private final static String MESSAGE_TEMPLATE = "%s : %d\n";

    public void test() {
        Thread main = currentThread();
        printThreadNameAndPriority(main);
        main.setPriority(MAX_PRIORITY);

        Thread thread = new Thread(() -> printThreadNameAndPriority(currentThread()));
        thread.start();
    }

    public void test1() {
        currentThread().setPriority(NORM_PRIORITY);

        Thread thread1 = new Thread(new Task());
        thread1.start();

        System.out.println("Main thread is finished.");
    }

    private void printThreadNameAndPriority(Thread thread) {
        System.out.printf(MESSAGE_TEMPLATE, thread.getName(), thread.getPriority());
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            IntStream.range(0, 100).forEach(System.out::println);
        }
    }
}
