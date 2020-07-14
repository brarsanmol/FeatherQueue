package me.ceezuns.queue;

import me.ceezuns.FeatherQueue;
import net.md_5.bungee.api.config.ServerInfo;

public class Queue {

    private String identifier;
    private ServerInfo server;

    public Queue(String identifier) {
        this.identifier = identifier;
        this.server = FeatherQueue.getInstance().getProxy().getServerInfo(identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public ServerInfo getServer() {
        return server;
    }
}
