package me.ceezuns.queue.player;

import com.google.common.base.Preconditions;
import com.google.common.collect.Streams;
import me.ceezuns.queue.Queue;
import me.ceezuns.queue.QueuePriority;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.stream.IntStream;

public class QueuePlayer implements Comparable<QueuePlayer> {

    private ProxiedPlayer player;
    private Queue queue;
    private QueuePriority priority;

    public QueuePlayer(ProxiedPlayer player, QueuePriority priority) {
        Preconditions.checkNotNull(player, "Player cannot be null.");
        Preconditions.checkNotNull(priority, "Priority cannot be null.");
        this.player = player;
        this.priority = priority;
    }

    public int getPositionInQueue() {
        if (this.isQueued()) {
            int index = 0;
            for (QueuePlayer target : this.queue.getPlayers()) {
                if (target.equals(this)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
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
        this.queue = queue;
    }

    public boolean isQueued() {
        return this.queue != null;
    }

    @Override
    public int compareTo(QueuePlayer target) {
        return this.getPriority().getWeight() - target.getPriority().getWeight();
    }
}
