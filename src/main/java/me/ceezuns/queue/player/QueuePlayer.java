package me.ceezuns.queue.player;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class QueuePlayer {

    private ProxiedPlayer player;

    public QueuePlayer(ProxiedPlayer player) {
        Preconditions.checkNotNull(player, "Player cannot be null.");
        this.player = player;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }
}
