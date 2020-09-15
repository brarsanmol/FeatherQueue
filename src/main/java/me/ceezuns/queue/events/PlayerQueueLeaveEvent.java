package me.ceezuns.queue.events;

import me.ceezuns.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerQueueLeaveEvent extends PlayerQueueEvent {

    public PlayerQueueLeaveEvent(ProxiedPlayer player, Queue queue) {
        super(player, queue);
    }

}
