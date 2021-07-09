package ca.anmolbrar;

import ca.anmolbrar.queue.QueueCommand;
import ca.anmolbrar.queue.QueueManager;
import ca.anmolbrar.queue.player.QueuePlayerListener;
import co.aikar.commands.BungeeCommandManager;
import ca.anmolbrar.queue.player.QueuePlayerManager;
import ca.anmolbrar.queue.priority.QueuePriorityManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class FeatherQueue extends Plugin {

    private static FeatherQueue instance;
    private BungeeCommandManager commandManager;
    private Configuration configuration;
    private QueueManager queueManager;
    private QueuePriorityManager queuePriorityManager;
    private QueuePlayerManager queuePlayerManager;

    @Override
    public void onEnable() {
        instance = this;
        this.commandManager = new BungeeCommandManager(this);
        this.loadConfiguration();
        this.queueManager = new QueueManager();
        this.queuePriorityManager = new QueuePriorityManager();
        this.queuePlayerManager = new QueuePlayerManager();
        new QueuePlayerListener();
        new QueueCommand();
    }

    @Override
    public void onDisable() {
        this.queueManager.saveQueues();
        this.queuePriorityManager.savePriorities();
        this.saveConfiguration();
        instance = null;
    }

    private void loadConfiguration() {
        if (!this.getDataFolder().exists()) {
            this.getLogger().log(Level.INFO, "The plugin data folder does exist, creating it now...");
            this.getDataFolder().mkdirs();
            try {
                this.getLogger().log(Level.INFO, "Creating the configuration file...");
                Files.copy(getResourceAsStream("config.yml"), new File(this.getDataFolder(), "config.yml").toPath());
            } catch (IOException exception) {
                this.getLogger().log(Level.SEVERE, "Failed to create the configuration file.", exception);
            }

        }
        try {
            this.getLogger().log(Level.INFO, "Loading configuration file.");
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
        } catch (IOException exception) {
            this.getLogger().log(Level.SEVERE, "Failed to load the configuration file.", exception);
        }
    }

    private void saveConfiguration() {
        try {
            this.getLogger().log(Level.INFO, "Saving configuration file.");
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, new File(getDataFolder(), "config.yml"));
        } catch (IOException exception) {
            this.getLogger().log(Level.SEVERE, "Failed to save the configuration file.", exception);
        }
    }

    public static FeatherQueue getInstance() {
        return instance;
    }

    public BungeeCommandManager getCommandManager() {
        return commandManager;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public QueueManager getQueueManager() {
        return queueManager;
    }

    public QueuePriorityManager getQueuePriorityManager() {
        return queuePriorityManager;
    }

    public QueuePlayerManager getQueuePlayerManager() {
        return queuePlayerManager;
    }

}
