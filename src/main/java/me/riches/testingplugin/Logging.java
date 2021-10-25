package me.riches.testingplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Logging {
    public static void log(Player p, String message, ChatColor c) {
        p.sendMessage(ChatColor.GREEN + "[TestingPlugin] " + c + message);
    }

    public static void log(Player p, String message) {
        p.sendMessage(ChatColor.GREEN + "[TestingPlugin] " + ChatColor.AQUA + message);
    }
}
