package me.riches.testingplugin.commands;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteCommand implements CommandExecutor {

    public Server server;

    public SmiteCommand(Server server) {
        this.server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Player p = (Player) sender;
            Player player = p.getServer().getPlayer(args[0]);
            Location loc = player.getLocation();

            player.getWorld().strikeLightning(loc);
        }
        return true;
    }
}
