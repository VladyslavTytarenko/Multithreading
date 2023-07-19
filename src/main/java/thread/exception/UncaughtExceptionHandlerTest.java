package thread.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtExceptionHandlerTest {

    public void test() {
        UncaughtExceptionHandler exceptionHandler = (thread, exception) -> System.out.printf("Exception was thrown with message '%s' in the thread '%s'\n", exception.getMessage(), thread.getName());

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler); // Set handler for all threads

        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(exceptionHandler);
        thread.start();

        Thread secondThread = new Thread(new Task());
        secondThread.setUncaughtExceptionHandler(exceptionHandler);
        secondThread.start();
    }
}
