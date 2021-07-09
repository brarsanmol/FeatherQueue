package ca.anmolbrar.queue.events;

import ca.anmolbrar.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerQueueJoinEvent extends PlayerQueueEvent {

    public PlayerQueueJoinEvent(ProxiedPlayer player, Queue queue) {
        super(player, queue);
    }

}
