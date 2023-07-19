package thread;

import static java.lang.Thread.currentThread;
import static thread.Constants.MESSAGE_TEMPLATE;

/**
 * The sum of an arithmetic progression calculator with multithreading
 */
public class SumArithmeticProgressionCalculator {

    private static final int FROM_NUMBER_FIRST_THREAD = 1;
    private static final int TO_NUMBER_FIRST_THREAD = 500;

    private static final int FROM_NUMBER_SECOND_THREAD = 501;
    private static final int TO_NUMBER_SECOND_THREAD = 1000;

    public void calculate() throws InterruptedException {
        final TaskSummingNumbers firstTask = new TaskSummingNumbers(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
        final TaskSummingNumbers secondTask = new TaskSummingNumbers(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);

        Thread firstThread = new Thread(firstTask);
        Thread secondThread = new Thread(secondTask);

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        int result = firstTask.getResultNumber() + secondTask.getResultNumber();
        System.out.printf(MESSAGE_TEMPLATE, currentThread().getName(), result);
    }
}
