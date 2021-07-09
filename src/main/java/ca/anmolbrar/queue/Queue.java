package ca.anmolbrar.queue;

import ca.anmolbrar.queue.tasks.QueuePositionTask;
import ca.anmolbrar.queue.tasks.QueuePushTask;
import com.google.common.base.Preconditions;
import ca.anmolbrar.FeatherQueue;
import ca.anmolbrar.queue.player.QueuePlayer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;

public class Queue {

    private String identifier;
    private QueueStatus status;
    private int maximumQueueSize;
    private int positionTaskDelay;
    private int pushTaskDelay;
    private ServerInfo server;
    private PriorityBlockingQueue<QueuePlayer> players;
    private QueuePositionTask positionTask;
    private QueuePushTask pushTask;

    public Queue(String identifier, QueueStatus status, int maximumQueueSize, int positionTaskDelay, int pushDelayTask) {
        this.identifier = identifier;
        this.status = status;
        this.maximumQueueSize = maximumQueueSize;
        this.positionTaskDelay = positionTaskDelay;
        this.pushTaskDelay = pushDelayTask;
        this.server = FeatherQueue.getInstance().getProxy().getServerInfo(identifier);
        this.players = new PriorityBlockingQueue<>();
        this.positionTask = new QueuePositionTask(this);
        this.pushTask = new QueuePushTask(this);
    }

    public String getIdentifier() {
        return identifier;
    }

    public QueueStatus getStatus() {
        return status;
    }

    public ServerInfo getServer() {
        return server;
    }

    public int getMaximumQueueSize() {
        return maximumQueueSize;
    }

    public int getPositionTaskDelay() {
        return positionTaskDelay;
    }

    public int getPushTaskDelay() {
        return pushTaskDelay;
    }

    public void setStatus(QueueStatus status) {
        Preconditions.checkNotNull(status, "Status cannot be null.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed queue status for " + this.getIdentifier() + " from " + this.status.name() + " to " + status.name());
        this.status = status;
    }

    public void setMaximumQueueSize(int maximumQueueSize) {
        Preconditions.checkNotNull(maximumQueueSize, "Maximum Queue Size cannot be null.");
        Preconditions.checkArgument(maximumQueueSize >= 0, "Maximum Queue Size Cannot Be Negative.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed maximum queue size for " + this.identifier + " from " + this.maximumQueueSize + " to " + maximumQueueSize);
        this.maximumQueueSize = maximumQueueSize;
    }

    // Should we check if the player is successfully added?
    public boolean addPlayer(QueuePlayer player) {
        Preconditions.checkNotNull(player, "Queue Player cannot be null.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Added " + player.getPlayer().getName() + " (" + player.getPlayer().getUniqueId() + ") from " + this.identifier);
        return this.players.add(player);
    }

    // Should we check if the player is successfully removed?
    public boolean removePlayer(QueuePlayer player) {
        Preconditions.checkNotNull(player, "Queue Player cannot be null.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Removed " + player.getPlayer().getName() + " (" + player.getPlayer().getUniqueId() + ") from " + this.identifier);
        player.setQueue(null);
        return this.players.remove(player);
    }

    public PriorityBlockingQueue<QueuePlayer> getPlayers() {
        return players;
    }

    public QueuePositionTask getPositionTask() {
        return positionTask;
    }

    public QueuePushTask getPushTask() {
        return pushTask;
    }
}
