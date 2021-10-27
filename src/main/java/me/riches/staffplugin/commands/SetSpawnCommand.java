package me.riches.staffplugin.commands;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.files.CustomConfigFile;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

        LuckPerms lp = provider.getProvider();

        if (sender instanceof Player){
            Player p = (Player) sender;

            User user = lp.getUserManager().getUser(p.getUniqueId());
            if(user.getPrimaryGroup().equals("admin")) {

                Logging.log(p, "The server's spawn has been successfully set to your location.", ChatColor.AQUA);

                CustomConfigFile.get().set("spawn-cords.X", p.getLocation().getX());
                CustomConfigFile.get().set("spawn-cords.Y", p.getLocation().getY());
                CustomConfigFile.get().set("spawn-cords.Z", p.getLocation().getZ());
                CustomConfigFile.get().set("spawn-cords.YAW", p.getLocation().getYaw());
                CustomConfigFile.save();
                CustomConfigFile.reload();
            }
            else {
                p.chat(ChatColor.RED + "You do not obtain the required permissions to access this command.");
            }
        }

        return true;
    }
}
