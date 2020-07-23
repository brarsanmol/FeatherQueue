package me.ceezuns.queue.player;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import me.ceezuns.queue.player.QueuePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashSet;
import java.util.logging.Level;

public class QueuePlayerManager {

    private HashSet<QueuePlayer> players;

    public QueuePlayerManager() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Created the QueuePlayerManager.");
        this.players = new HashSet<>();
    }

    public QueuePlayer getPlayer(ProxiedPlayer target) {
        Preconditions.checkNotNull(target, "Target cannot be null.");
        return this.players.stream().filter(player -> player.getPlayer().equals(target)).findFirst().orElse(null);
    }

    public HashSet<QueuePlayer> getPlayers() {
        return players;
    }
}
