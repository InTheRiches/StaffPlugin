package me.riches.staffplugin.commands;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.StaffPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    StaffPlugin plugin;

    ArrayList<Player> vanished = new ArrayList<>();

    public VanishCommand(StaffPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

        assert provider != null;
        LuckPerms lp = provider.getProvider();

        if (sender instanceof Player) {
            Player p = (Player) sender;

            User user = lp.getUserManager().getUser(p.getUniqueId());
            assert user != null;
            if (user.getPrimaryGroup().equals("admin")) {
                toggleVanish(p);
            }
        }
        return true;
    }

    public void toggleVanish(Player p) {
        if (vanished.contains(p)) {
            for (Player people : Bukkit.getOnlinePlayers())
                people.showPlayer(plugin, p);
            vanished.remove(p);
            Logging.log(p, "You have un-vanished.");
        }
        else{
            for (Player people : Bukkit.getOnlinePlayers())
                people.hidePlayer(plugin, p);
            vanished.add(p);
            Logging.log(p, "You have vanished.");
        }
    }
}
