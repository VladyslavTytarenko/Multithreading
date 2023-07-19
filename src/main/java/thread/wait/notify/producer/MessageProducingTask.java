package thread.wait.notify.producer;

import thread.wait.notify.brocker.MessageBroker;
import thread.wait.notify.model.Message;
import thread.wait.notify.model.factory.MessageFactory;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MessageProducingTask implements Runnable {

    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;
    private final int maxAmountMessagesToProduce;
    private final String name;

    public MessageProducingTask(MessageBroker messageBroker,
                                MessageFactory messageFactory,
                                int maxAmountMessagesToProduce,
                                String name) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
        this.maxAmountMessagesToProduce = maxAmountMessagesToProduce;
        this.name = name;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            Message message = messageFactory.create();
            messageBroker.produce(message, this);
        }
    }

    public int getMaxAmountMessagesToProduce() {
        return maxAmountMessagesToProduce;
    }

    public String getName() {
        return name;
    }
}
