package me.ceezuns.queue.events;

import me.ceezuns.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerQueueJoinEvent extends PlayerQueueEvent {

    public PlayerQueueJoinEvent(ProxiedPlayer player, Queue queue) {
        super(player, queue);
    }

}
