package ca.anmolbrar.queue.player;

import ca.anmolbrar.FeatherQueue;
import com.google.common.base.Preconditions;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class QueuePlayerManager {

    private Map<UUID, QueuePlayer> players;

    public QueuePlayerManager() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Created the QueuePlayerManager.");
        this.players = new ConcurrentHashMap<>();
    }

    public QueuePlayer getPlayer(ProxiedPlayer target) {
        Preconditions.checkNotNull(target, "Target cannot be null.");
        return this.players.get(target.getUniqueId());
    }


    public Map<UUID, QueuePlayer> getPlayers() {
        return players;
    }
}
