package thread;

import static java.lang.Thread.currentThread;
import static java.util.stream.IntStream.rangeClosed;
import static thread.Constants.MESSAGE_TEMPLATE;

public class TaskSummingNumbers implements Runnable {

    private static final int INITIAL_VALUE_RESULT_NUMBER = 0;

    private final int fromNumber;
    private final int toNumber;
    private int resultNumber;

    public TaskSummingNumbers(final int fromNumber,
                              final int toNumber) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.resultNumber = INITIAL_VALUE_RESULT_NUMBER;
    }

    @Override
    public void run() {
        rangeClosed(fromNumber, toNumber).forEach(i -> resultNumber += i);
        System.out.printf(MESSAGE_TEMPLATE, currentThread().getName(), resultNumber);
    }

    public int getResultNumber() {
        return resultNumber;
    }
}
