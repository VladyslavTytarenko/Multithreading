import thread.MyThread;
import thread.SumArithmeticProgressionCalculator;
import thread.daemon.ThreadDaemonTest;
import thread.exception.UncaughtExceptionHandlerTest;
import thread.factory.GeneralThreadFactoryTest;
import thread.lock.EvenNumberGenerator;
import thread.priority.ThreadPriorityTest;
import thread.race.ThreadRaceTest;
import thread.state.ThreadStateTest;
import thread.stop.ThreadStopTest;
import thread.wait.notify.brocker.MessageBroker;
import thread.wait.notify.consumer.MessageConsumingTask;
import thread.wait.notify.model.factory.MessageFactory;
import thread.wait.notify.producer.MessageProducingTask;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.stream.IntStream.range;

public final class Main {

    private static final int THREADS_COUNT = 10;

    public static void main(final String... args) throws InterruptedException {
        //part1();
        //part2();
        //part3();
        //part4();
        //part5();
        //part6();
        //part7();
        //part8();
        //part9();
        //part10();
        part11();
    }

    private static void part11() {
        EvenNumberGenerator generator = new EvenNumberGenerator(new ReentrantLock());

        Runnable generatingTask = () -> range(0, 100).forEach(i -> System.out.println(generator.generate() + "\n"));

        Thread firstThread = new Thread(generatingTask);
        Thread secondThread = new Thread(generatingTask);
        Thread thirdThread = new Thread(generatingTask);

        startThreads(firstThread, secondThread, thirdThread);
    }

    private static void part10() {
        int brokerMaxStoredMessages = 1;
        MessageBroker messageBroker = new MessageBroker(brokerMaxStoredMessages);

        MessageFactory messageFactory = new MessageFactory();

        Thread firstProducingThread = new Thread(new MessageProducingTask(messageBroker, messageFactory, 15, "PRODUCER_1"));
        Thread secondProducingThread = new Thread(new MessageProducingTask(messageBroker, messageFactory, 10, "PRODUCER_2"));
        Thread thirdProducingThread = new Thread(new MessageProducingTask(messageBroker, messageFactory, 5, "PRODUCER_3"));

        Thread firstConsumingThead = new Thread(new MessageConsumingTask(messageBroker, 0, "CONSUMER_1"));
        Thread secondConsumingThead = new Thread(new MessageConsumingTask(messageBroker, 5, "CONSUMER_2"));
        Thread thirdConsumingThead = new Thread(new MessageConsumingTask(messageBroker, 15, "CONSUMER_3"));

        startThreads(firstConsumingThead, secondConsumingThead, thirdConsumingThead,
                firstProducingThread, secondProducingThread, thirdProducingThread);
    }

    private static void part9() throws InterruptedException {
        ThreadRaceTest threadRaceTest = new ThreadRaceTest();
        threadRaceTest.test();
    }

    private static void part8() throws InterruptedException {
        GeneralThreadFactoryTest generalThreadFactoryTest = new GeneralThreadFactoryTest();
        generalThreadFactoryTest.test();
    }

    private static void part7() {
        UncaughtExceptionHandlerTest uncaughtExceptionHandlerTest = new UncaughtExceptionHandlerTest();
        uncaughtExceptionHandlerTest.test();
    }

    private static void part6() {
        ThreadDaemonTest threadDaemonTest = new ThreadDaemonTest();
        threadDaemonTest.test();
    }

    private static void part5() {
        ThreadPriorityTest threadPriorityTest = new ThreadPriorityTest();
        threadPriorityTest.test();
        threadPriorityTest.test1();
    }

    private static void part4() throws InterruptedException {
        ThreadStopTest threadStopTest = new ThreadStopTest();
        threadStopTest.test();
    }

    private static void part3() throws InterruptedException {
        ThreadStateTest threadStateTest = new ThreadStateTest();
        threadStateTest.test();
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

    private static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }
}
