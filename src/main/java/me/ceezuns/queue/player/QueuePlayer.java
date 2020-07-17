package me.ceezuns.queue.player;

import com.google.common.base.Preconditions;
import me.ceezuns.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class QueuePlayer {

    private ProxiedPlayer player;
    private Queue queue;

    public QueuePlayer(ProxiedPlayer player) {
        Preconditions.checkNotNull(player, "Player cannot be null.");
        this.player = player;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        Preconditions.checkNotNull(queue, "Queue cannot be null.");
        this.queue = queue;
    }
}
