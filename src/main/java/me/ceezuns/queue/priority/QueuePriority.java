package me.ceezuns.queue.priority;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;

import java.util.logging.Level;

public class QueuePriority {

    private String identifier;
    private String permission;
    private int weight;

    public QueuePriority(String identifier, String permission, int weight) {
        this.identifier = identifier;
        this.permission = permission;
        this.weight = weight;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPermission() {
        return permission;
    }

    public int getWeight() {
        return weight;
    }

    public void setPermission(String permission) {
        Preconditions.checkNotNull(permission, "Permission cannot be null.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed priority permission for " + this.getIdentifier() + " from " + this.getPermission() + " to " + permission);
        this.permission = permission;
    }

    public void setWeight(int weight) {
        Preconditions.checkNotNull(weight, "Weight cannot be null.");
        Preconditions.checkArgument(weight >= 0, "Weight Cannot Be Negative.");
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Changed priority weight for " + this.getIdentifier() + " from " + this.getWeight() + " to " + weight);
        this.weight = weight;
    }
}
