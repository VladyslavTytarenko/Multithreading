package thread.wait.notify.brocker;

import thread.wait.notify.consumer.MessageConsumingTask;
import thread.wait.notify.model.Message;
import thread.wait.notify.producer.MessageProducingTask;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class MessageBroker {

    private Queue<Message> messages;
    private int maxSize;

    public MessageBroker(int maxSize) {
        this.messages = new ArrayDeque<>(maxSize);
        this.maxSize = maxSize;
    }

    public synchronized void produce(Message message, MessageProducingTask messageProducingTask) {
        try {
            while (isNotProduce(messageProducingTask)) wait();
            messages.add(message);
            System.out.printf("By %s '%s' is produced\n", messageProducingTask.getName(), message);
            notifyAll();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    public synchronized Message consume(MessageConsumingTask messageConsumingTask) {
        try {
            while (isNotConsume(messageConsumingTask)) wait();
            Message message = messages.poll();
            System.out.printf("By %s '%s' is consumed.\n", messageConsumingTask.getName(), message);
            notifyAll();

            return message;
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        return null;
    }

    private boolean isNotConsume(MessageConsumingTask messageConsumingTask) {
        return messages.isEmpty()
                || messages.size() < messageConsumingTask.getMinimalAmountMessagesToConsume();
    }

    private boolean isNotProduce(MessageProducingTask messageProducingTask) {
        return messages == null
                || messages.size() > messageProducingTask.getMaxAmountMessagesToProduce();
    }
}
