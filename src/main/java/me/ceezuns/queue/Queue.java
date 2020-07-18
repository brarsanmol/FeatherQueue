package me.ceezuns.queue;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;
import java.util.logging.Level;

public class Queue {

    private String identifier;
    private QueueStatus status;
    private int maximumQueueSize;
    private ServerInfo server;

    public Queue(String identifier, QueueStatus status, int maximumQueueSize) {
        this.identifier = identifier;
        this.status = status;
        this.maximumQueueSize = maximumQueueSize;
        this.server = FeatherQueue.getInstance().getProxy().getServerInfo(identifier);
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

    public void setStatus(QueueStatus status) {
        Preconditions.checkNotNull(status, "Status cannot be null.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed queue status for " + this.getIdentifier() + " from " + this.status.name() + " to " + status.name());
        this.status = status;
    }

    public int getMaximumQueueSize() {
        return maximumQueueSize;
    }

    public void setMaximumQueueSize(int maximumQueueSize) {
        Preconditions.checkNotNull(maximumQueueSize, "Maximum Queue Size cannot be null.");
        Preconditions.checkArgument(maximumQueueSize >= 0, "Maximum Queue Size Cannot Be Negative.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed maximum queue size for " + this.getIdentifier() + " from " + this.maximumQueueSize + " to " + maximumQueueSize);
        this.maximumQueueSize = maximumQueueSize;
    }
}
