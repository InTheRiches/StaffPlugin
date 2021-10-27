package me.riches.staffplugin.commands;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.files.CustomConfigFile;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SpawnCommand implements CommandExecutor {

    public Server server;

    public SpawnCommand(Server server) {
        this.server = server;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Logging.log(p, "You have been teleported to spawn.");

            Location spawn = new Location(server.getWorld(Objects.requireNonNull(CustomConfigFile.get().getString("spawn-world"))), Double.parseDouble(Objects.requireNonNull(CustomConfigFile.get().getString("spawn-cords.X"))), Double.parseDouble(CustomConfigFile.get().getString("spawn-cords.Y")), Double.parseDouble(CustomConfigFile.get().getString("spawn-cords.Z")), Float.parseFloat(CustomConfigFile.get().getString("spawn-cords.YAW")),0);


            p.teleport(spawn);
        }
        return true;
    }
}
