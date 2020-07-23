package me.ceezuns.queue.tasks;

import me.ceezuns.FeatherQueue;
import me.ceezuns.queue.Queue;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.concurrent.TimeUnit;

public class QueuePositionTask implements Runnable {

    private Queue queue;
    private ScheduledTask task;

    public QueuePositionTask(Queue queue) {
        this.queue = queue;
        this.task = FeatherQueue.getInstance().getProxy().getScheduler().schedule(FeatherQueue.getInstance(), this, 1, this.queue.getPositionTaskDelay(), TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        this.queue.getPlayers().forEach(player -> player.getPlayer().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.positionTask").replaceAll("%identifier%", this.queue.getIdentifier()).replaceAll("%position%", String.valueOf(player.getPositionInQueue() + 1)).replaceAll("%size%", String.valueOf(this.queue.getPlayers().size()))))));
    }

    public void cancel() {
        this.task.cancel();
    }
}
