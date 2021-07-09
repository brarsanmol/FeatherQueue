package ca.anmolbrar.queue.events;

import ca.anmolbrar.queue.Queue;
import ca.anmolbrar.queue.QueueStatus;
import net.md_5.bungee.api.plugin.Event;

public class QueueStatusChangeEvent extends Event {

    private Queue queue;
    private QueueStatus oldStatus;
    private QueueStatus newStatus;

    public QueueStatusChangeEvent(Queue queue, QueueStatus oldStatus, QueueStatus newStatus) {
        this.queue = queue;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public Queue getQueue() {
        return queue;
    }

    public QueueStatus getOldStatus() {
        return oldStatus;
    }

    public QueueStatus getNewStatus() {
        return newStatus;
    }
}
