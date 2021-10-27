package me.riches.staffplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Logging {
    public static void log(Player p, String message, ChatColor c) {
        p.sendMessage(ChatColor.GREEN + "[StaffPlugin] " + c + message);
    }

    public static void log(Player p, String message) {
        p.sendMessage(ChatColor.GREEN + "[StaffPlugin] " + ChatColor.AQUA + message);
    }
}
