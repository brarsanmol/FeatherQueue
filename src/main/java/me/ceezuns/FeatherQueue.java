package me.ceezuns;

import me.ceezuns.queue.player.QueuePlayerListener;
import me.ceezuns.queue.player.QueuePlayerManager;
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
    private Configuration configuration;
    private QueuePlayerManager queuePlayerManager;

    @Override
    public void onEnable() {
        instance = this;
        this.loadConfiguration();
        this.queuePlayerManager = new QueuePlayerManager();
        new QueuePlayerListener();

    }

    @Override
    public void onDisable() {
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
                exception.printStackTrace();
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
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
        } catch (IOException exception) {
            this.getLogger().log(Level.SEVERE, "Failed to save the configuration file.", exception);
        }
    }

    public static FeatherQueue getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public QueuePlayerManager getQueuePlayerManager() {
        return queuePlayerManager;
    }

}
