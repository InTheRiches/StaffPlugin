package me.riches.testingplugin;

import me.riches.testingplugin.commands.AdminPanelCommand;
import me.riches.testingplugin.commands.SetSpawnCommand;
import me.riches.testingplugin.commands.SmiteCommand;
import me.riches.testingplugin.commands.SpawnCommand;
import me.riches.testingplugin.events.ClickEvent;
import me.riches.testingplugin.files.CustomConfigFile;
import me.riches.testingplugin.listeners.PlayerEventsListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestingPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        CustomConfigFile.setup(getServer());

        System.out.println(ChatColor.GREEN + "TestingPlugin A.01 has started.");

        getServer().getPluginManager().registerEvents(new PlayerEventsListener(getServer()), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand(getServer()));
        getCommand("admin").setExecutor(new AdminPanelCommand());
        getCommand("smite").setExecutor(new SmiteCommand(getServer()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static JavaPlugin getThePlugin() {
        return plugin;
    }
}
