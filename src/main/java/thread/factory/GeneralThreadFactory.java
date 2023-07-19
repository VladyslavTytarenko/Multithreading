package thread.factory;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

public class GeneralThreadFactory implements ThreadFactory {

    private final UncaughtExceptionHandler exceptionHandler;

    public GeneralThreadFactory(UncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        thread.setDaemon(true);
        return thread;
    }
}
