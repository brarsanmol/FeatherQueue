package me.ceezuns.queue.player;

import com.google.common.base.Preconditions;
import me.ceezuns.queue.Queue;
import me.ceezuns.queue.QueuePriority;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class QueuePlayer {

    private ProxiedPlayer player;
    private Queue queue;
    private QueuePriority priority;

    public QueuePlayer(ProxiedPlayer player, QueuePriority priority) {
        Preconditions.checkNotNull(player, "Player cannot be null.");
        Preconditions.checkNotNull(priority, "Priority cannot be null.");
        this.player = player;
        this.priority = priority;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public Queue getQueue() {
        return queue;
    }

    public QueuePriority getPriority() {
        return priority;
    }

    public void setQueue(Queue queue) {
        Preconditions.checkNotNull(queue, "Queue cannot be null.");
        this.queue = queue;
    }

    public boolean isQueued() {
        return this.queue != null;
    }
}
