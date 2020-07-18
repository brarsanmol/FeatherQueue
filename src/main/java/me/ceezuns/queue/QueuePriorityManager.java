package me.ceezuns.queue;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class QueuePriorityManager {

    private List<QueuePriority> priorities;

    public QueuePriorityManager() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Created the QueuePriorityManager.");
        this.priorities = new ArrayList<>();
        this.loadPriorities();
    }

    public void loadPriorities() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Loading priorities...");
        Configuration section = FeatherQueue.getInstance().getConfiguration().getSection("priorities");
        if (section == null) {
            FeatherQueue.getInstance().getLogger().log(Level.WARNING, "Failed to load priorities. This can be for two reasons, none are defined or an unexpected error occurred.");
            return;
        }
        section.getKeys().forEach(priority -> {
            if (this.priorities.add(new QueuePriority(priority, section.getString(priority + ".permission"), section.getInt(priority + ".weight")))) {
                FeatherQueue.getInstance().getLogger().log(Level.INFO, "Successfully loaded the priority " + priority);
            } else {
                FeatherQueue.getInstance().getLogger().log(Level.SEVERE, "Failed loading the priority " + priority);
            }
        });
    }

    public void savePriorities() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Saving priorities...");
        this.priorities.forEach(priority -> {
            FeatherQueue.getInstance().getConfiguration().set("priorities." + priority.getIdentifier() + ".permission", priority.getPermission());
            FeatherQueue.getInstance().getConfiguration().set("priorities." + priority.getIdentifier() + ".weight", priority.getWeight());
        });
        this.priorities.clear();
    }

    public QueuePriority getPriority(String identifier) {
        Preconditions.checkNotNull(identifier, "Identifier cannot be null.");
        return this.priorities.stream().filter(priority -> priority.getIdentifier().equalsIgnoreCase(identifier)).findFirst().orElse(null);
    }

    public QueuePriority getPriorityForPlayer(ProxiedPlayer player) {
        Preconditions.checkNotNull(player, "Player cannot be null.");
        return this.priorities.stream().filter(priority -> player.hasPermission(priority.getPermission())).findFirst().orElse(null);
    }

    protected List<QueuePriority> getPriorities() {
        return priorities;
    }
}
