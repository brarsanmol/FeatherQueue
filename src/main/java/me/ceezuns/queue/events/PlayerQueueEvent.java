package me.ceezuns.queue.events;

import me.ceezuns.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class PlayerQueueEvent extends Event {

    private final ProxiedPlayer player;
    private final Queue queue;

    public PlayerQueueEvent(ProxiedPlayer player, Queue queue) {
        this.player = player;
        this.queue = queue;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public Queue getQueue() {
        return queue;
    }
}
