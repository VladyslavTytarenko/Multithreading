import thread.MyThread;
import thread.SumArithmeticProgressionCalculator;

import static java.lang.Thread.currentThread;
import static java.util.stream.IntStream.range;

public final class Main {

    private static final int THREADS_COUNT = 10;

    public static void main(final String... args) throws InterruptedException {
        //part1();
        //part2();
    }

    private static void part2() throws InterruptedException {
        SumArithmeticProgressionCalculator calculator = new SumArithmeticProgressionCalculator();
        calculator.calculate();
    }

    /**
     * Run threads
     */
    private static void part1() {
        System.out.println("Threads from the main thread");
        System.out.println(currentThread().getName());

        final Thread thread = new MyThread();
        thread.start();

        final Thread thread1 = new Thread(() -> System.out.println(currentThread().getName()));
        thread1.start();

        final Runnable task = () -> System.out.println(currentThread().getName());
        final Thread thread2 = new Thread(task);
        thread2.start();

        System.out.println("Threads from the custom thread");
        final Runnable displayThreadNameTask = () -> System.out.println("Thread group: " + currentThread().getThreadGroup().getName() +
                                                                        ", Thread name: " + currentThread().getName());
        final Runnable runThreadsTask = () -> {
            System.out.println(currentThread().getName());
            range(0, THREADS_COUNT)
                    .forEach(index -> startThread(displayThreadNameTask));
        };
        startThread(runThreadsTask);
    }

    private static void startThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
