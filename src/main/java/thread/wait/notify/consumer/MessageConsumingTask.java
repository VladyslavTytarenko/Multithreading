package thread.wait.notify.consumer;

import thread.wait.notify.brocker.MessageBroker;

import static java.lang.Thread.currentThread;

public class MessageConsumingTask implements Runnable {

    private final MessageBroker messageBroker;
    private final int minimalAmountMessagesToConsume;
    private final String name;

    public MessageConsumingTask(MessageBroker messageBroker,
                                int minimalAmountMessagesToConsume,
                                String name) {
        this.messageBroker = messageBroker;
        this.minimalAmountMessagesToConsume = minimalAmountMessagesToConsume;
        this.name = name;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            messageBroker.consume(this);
        }
    }

    public int getMinimalAmountMessagesToConsume() {
        return minimalAmountMessagesToConsume;
    }

    public String getName() {
        return name;
    }
}
