package me.ceezuns.queue;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.google.common.base.Enums;
import me.ceezuns.FeatherQueue;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Arrays;
import java.util.stream.Collectors;

@CommandAlias("queue")
public class QueueCommand extends BaseCommand {

    public QueueCommand() {
        FeatherQueue.getInstance().getCommandManager().getCommandCompletions().registerAsyncCompletion("queues", callback -> FeatherQueue.getInstance().getQueueManager().getQueues().parallelStream().map(Queue::getIdentifier).collect(Collectors.toList()));
        FeatherQueue.getInstance().getCommandManager().getCommandCompletions().registerAsyncCompletion("queue-statuses", callback -> Arrays.stream(QueueStatus.values()).parallel().map(QueueStatus::name).collect(Collectors.toList()));
        FeatherQueue.getInstance().getCommandManager().registerCommand(this);
    }

    @Default
    @HelpCommand
    public static void onHelpCommand(CommandSender sender) {
        FeatherQueue.getInstance().getConfiguration().getStringList("messages.queueHelpCommand").forEach(line -> sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', line))));
    }

    @Subcommand("set size")
    @CommandCompletion("@queues")
    @CommandPermission("queue.set.size")
    public static void onSetSizeCommand(CommandSender sender, String identifier, int maximumQueueSize) {
        if (FeatherQueue.getInstance().getQueueManager().getQueue(identifier) == null) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetSizeCommand.invalidIdentifier").replaceAll("%identifier%", identifier))));
        } else if (maximumQueueSize < 0) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetSizeCommand.invalidSize").replaceAll("%size%", String.valueOf(maximumQueueSize)))));
        } else if (FeatherQueue.getInstance().getQueueManager().getQueue(identifier).getMaximumQueueSize() == maximumQueueSize) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetSizeCommand.sameSize").replaceAll("%size%", String.valueOf(maximumQueueSize)).replaceAll("%identifier%", identifier))));
        } else {
            FeatherQueue.getInstance().getQueueManager().getQueue(identifier).setMaximumQueueSize(maximumQueueSize);
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetSizeCommand.sizeChange").replaceAll("%identifier%", identifier).replaceAll("%size%", String.valueOf(maximumQueueSize)))));
        }
    }

    @Subcommand("set status")
    @CommandCompletion("@queues @queue-statuses")
    @CommandPermission("queue.set.status")
    public static void onSetStatusCommand(CommandSender sender, String identifier, String status) {
        if (FeatherQueue.getInstance().getQueueManager().getQueue(identifier) == null) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetStatusCommand.invalidIdentifier").replaceAll("%identifier%", identifier))));
        } else if (!QueueStatus.isValidStatus(status)) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetStatusCommand.invalidStatus").replaceAll("%status%", status))));
        } else if (FeatherQueue.getInstance().getQueueManager().getQueue(identifier).getStatus().name().equalsIgnoreCase(status)) {
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetStatusCommand.sameStatus").replaceAll("%status%", status).replaceAll("%identifier%", identifier))));
        } else {
            FeatherQueue.getInstance().getQueueManager().getQueue(identifier).setStatus(QueueStatus.valueOf(status.toUpperCase()));
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', FeatherQueue.getInstance().getConfiguration().getString("messages.queueSetStatusCommand.statusChange").replaceAll("%identifier%", identifier).replaceAll("%status%", status))));
        }
    }

}
