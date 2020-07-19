package me.ceezuns.queue;

import com.google.common.base.Preconditions;
import me.ceezuns.FeatherQueue;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class QueueManager {

    private List<Queue> queues;

    public QueueManager() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Created the QueueManager.");
        this.queues = new ArrayList<>();
        this.loadQueues();
    }

    public void loadQueues() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Loading queues...");
        Configuration section = FeatherQueue.getInstance().getConfiguration().getSection("queues");
        if (section == null) {
            FeatherQueue.getInstance().getLogger().log(Level.WARNING, "Failed to load queues. This can be for two reasons, none are defined or an unexpected error occurred.");
            return;
        }
        section.getKeys().forEach(queue -> {
            if (this.queues.add(new Queue(queue, QueueStatus.valueOf(section.getString(queue + ".status")), section.getInt(queue + ".maximumQueueSize"), section.getInt(queue +".positionTaskDelay"), section.getInt(queue + ".pushTaskDelay")))) {
                FeatherQueue.getInstance().getLogger().log(Level.INFO, "Successfully loaded the queue " + queue);
            } else {
                FeatherQueue.getInstance().getLogger().log(Level.SEVERE, "Failed loading the queue " + queue);
            }
        });
    }

    public void saveQueues() {
        FeatherQueue.getInstance().getLogger().log(Level.INFO, "Saving queues...");
        this.queues.forEach(queue -> {
            FeatherQueue.getInstance().getConfiguration().set("queues." + queue.getIdentifier() + ".status", queue.getStatus().name());
            FeatherQueue.getInstance().getConfiguration().set("queues." + queue.getIdentifier() + ".maximumQueueSize", queue.getMaximumQueueSize());
        });
        this.queues.clear();
    }

    public Queue getQueue(String identifier) {
        Preconditions.checkNotNull(identifier, "Identifier cannot be null.");
        return this.queues.stream().filter(queue -> queue.getIdentifier().equalsIgnoreCase(identifier)).findFirst().orElse(null);
    }

    protected List<Queue> getQueues() {
        return queues;
    }
}
