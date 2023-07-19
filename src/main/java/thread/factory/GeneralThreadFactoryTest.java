package thread.factory;

import thread.exception.CustomUncaughtExceptionHandler;
import thread.exception.Task;

public class GeneralThreadFactoryTest {

    public void test() throws InterruptedException {
        CustomUncaughtExceptionHandler exceptionHandler = new CustomUncaughtExceptionHandler();
        GeneralThreadFactory threadFactory = new GeneralThreadFactory(exceptionHandler);

        Thread firstThread = threadFactory.newThread(new Task());
        firstThread.start();
        Thread secondThread = threadFactory.newThread(new Task());
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }
}
