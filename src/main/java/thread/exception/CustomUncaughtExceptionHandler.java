package thread.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class CustomUncaughtExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        System.out.printf("Exception was thrown with message '%s' in the thread '%s'\n", exception.getMessage(), thread.getName());
    }
}
