package thread.race;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class ThreadRaceTest {

    private final int INCREMENT_AMOUNT_FIRST = 500;
    private final int INCREMENT_AMOUNT_SECOND = 600;

    private final Object FIRST_LOCK = new Object();
    private final Object SECOND_LOCK = new Object();

    private int firstCounter = 0;
    private int secondCounter = 0;

    public void test() {

        Thread firstThread = createIncrementingCounterThread(INCREMENT_AMOUNT_FIRST, i -> incrementFirstCounter());
        Thread secondThread = createIncrementingCounterThread(INCREMENT_AMOUNT_FIRST, i -> incrementFirstCounter());
        Thread thirdThread = createIncrementingCounterThread(INCREMENT_AMOUNT_SECOND, i -> incrementSecondCounter());
        Thread fourthThread = createIncrementingCounterThread(INCREMENT_AMOUNT_SECOND, i -> incrementSecondCounter());

        startThreads(firstThread, secondThread, thirdThread, fourthThread);
        joinThreads(firstThread, secondThread, thirdThread, fourthThread);

        System.out.println(firstCounter);
        System.out.println(secondCounter);
    }

    private void joinThreads(Thread... threads) {
        stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        });
    }

    private void startThreads(Thread... threads) {
        stream(threads).forEach(Thread::start);
    }

    private Thread createIncrementingCounterThread(int incrementAmount, IntConsumer incrementingOperation) {
        return new Thread(() -> IntStream.range(0, incrementAmount).forEach(incrementingOperation));
    }

    private void incrementFirstCounter() {
        synchronized (FIRST_LOCK) {
            firstCounter++;
        }
    }

    private void incrementSecondCounter() {
        synchronized (SECOND_LOCK) {
            secondCounter++;
        }
    }
}
