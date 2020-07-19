package me.ceezuns.queue;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import me.ceezuns.queue.player.QueuePlayer;
import me.ceezuns.queue.tasks.QueuePositionTask;
import me.ceezuns.queue.tasks.QueuePushTask;
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

    public void setPositionTaskDelay(int positionTaskDelay) {
        Preconditions.checkNotNull(positionTaskDelay, "Position Task Delay cannot be null.");
        Preconditions.checkArgument(positionTaskDelay >= 0, "Position Task Delay Cannot Be Negative.");
        this.positionTaskDelay = positionTaskDelay;
    }

    public void setPushTaskDelay(int pushTaskDelay) {
        Preconditions.checkNotNull(pushTaskDelay, "Push Task Delay cannot be null.");
        Preconditions.checkArgument(pushTaskDelay >= 0, "Push Task Delay Cannot Be Negative.");
        this.pushTaskDelay = pushTaskDelay;
    }

    public void setMaximumQueueSize(int maximumQueueSize) {
        Preconditions.checkNotNull(maximumQueueSize, "Maximum Queue Size cannot be null.");
        Preconditions.checkArgument(maximumQueueSize >= 0, "Maximum Queue Size Cannot Be Negative.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed maximum queue size for " + this.getIdentifier() + " from " + this.maximumQueueSize + " to " + maximumQueueSize);
        this.maximumQueueSize = maximumQueueSize;
    }

    public boolean addPlayer(QueuePlayer player) {
        Preconditions.checkNotNull(player, "Queue Player cannot be null.");
        return this.players.add(player);
    }

    public boolean removePlayer(QueuePlayer player) {
        Preconditions.checkNotNull(player, "Queue Player cannot be null.");
        return this.players.remove(player);
    }

    public PriorityBlockingQueue<QueuePlayer> getPlayers() {
        return players;
    }
}
