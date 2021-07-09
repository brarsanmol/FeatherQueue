package ca.anmolbrar.queue.events;

import ca.anmolbrar.queue.Queue;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerQueuePushEvent extends PlayerQueueEvent {

    public PlayerQueuePushEvent(ProxiedPlayer player, Queue queue) {
        super(player, queue);
    }

}
