package thread.lock;

import java.util.concurrent.locks.Lock;

public class EvenNumberGenerator {

    private int previousGenerated;
    private Lock lock;

    public EvenNumberGenerator(Lock lock) {
        this.previousGenerated = -2;
        this.lock = lock;
    }

    public int generate() {
        lock.lock();
        try {
            return previousGenerated += 2;
        } finally {
            lock.unlock();
        }
    }
}
