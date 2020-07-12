package me.ceezuns;

import net.md_5.bungee.api.plugin.Plugin;

public class FeatherQueue extends Plugin {

    private static FeatherQueue instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static FeatherQueue getInstance() {
        return instance;
    }

}
