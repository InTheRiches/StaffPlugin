package me.riches.staffplugin;

import me.riches.staffplugin.commands.*;
import me.riches.staffplugin.events.ClickEvent;
import me.riches.staffplugin.files.CustomConfigFile;
import me.riches.staffplugin.listeners.PlayerEventsListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        CustomConfigFile.setup(getServer());

        System.out.println(ChatColor.GREEN + "StaffPlugin A.01 has started.");

        getServer().getPluginManager().registerEvents(new PlayerEventsListener(getServer()), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand(getServer()));
        getCommand("admin").setExecutor(new AdminPanelCommand());
        getCommand("smite").setExecutor(new SmiteCommand(getServer()));
        getCommand("vanish").setExecutor(new VanishCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static JavaPlugin getThePlugin() {
        return plugin;
    }
}
