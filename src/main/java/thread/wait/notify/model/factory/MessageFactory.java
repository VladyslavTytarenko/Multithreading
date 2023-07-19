package thread.wait.notify.model.factory;

import thread.wait.notify.model.Message;

import static java.lang.String.format;

public class MessageFactory {

    private int nextMessageIndex;

    public MessageFactory() {
        nextMessageIndex = 0;
    }

    public Message create() {
        return new Message(format("Message#%d", incrementMessageIndex()));
    }

    private synchronized int incrementMessageIndex() {
        return nextMessageIndex++;
    }
}