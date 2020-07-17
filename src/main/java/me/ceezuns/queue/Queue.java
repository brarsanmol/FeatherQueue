package me.ceezuns.queue;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import net.md_5.bungee.api.config.ServerInfo;

public class Queue {

    private String identifier;
    private QueueStatus status;
    private ServerInfo server;

    public Queue(String identifier, QueueStatus status) {
        this.identifier = identifier;
        this.status = status;
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
        this.status = status;
    }
}
