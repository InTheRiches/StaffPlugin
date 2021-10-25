package me.riches.testingplugin.listeners;

import me.riches.testingplugin.files.CustomConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventsListener implements Listener {

    Server server;

    public PlayerEventsListener(Server server) {
        this.server = server;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Location spawn = new Location(server.getWorld(CustomConfigFile.get().getString("spawn-world")), Double.parseDouble(CustomConfigFile.get().getString("spawn-cords.X")), Double.parseDouble(CustomConfigFile.get().getString("spawn-cords.Y")), Double.parseDouble(CustomConfigFile.get().getString("spawn-cords.Z")));

        player.teleport(spawn);

        if (player.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.YELLOW + "Welcome back " + player.getName() + "!");
        }
        else {
            e.setJoinMessage(ChatColor.YELLOW + "Welcome to riches' testing server " + player.getName() + "!");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.RED + player.getName() + " has left the server. Goodbye!");
    }
}
