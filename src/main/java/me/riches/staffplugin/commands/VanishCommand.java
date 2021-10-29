package me.riches.staffplugin.commands;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.StaffPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class VanishCommand implements CommandExecutor {

    public static StaffPlugin plugin;

    public static ArrayList<Player> vanished = new ArrayList<>();

    public VanishCommand(StaffPlugin plugin) {
        VanishCommand.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms lp = provider.getProvider();

        if (sender instanceof Player) {
            Player p = (Player) sender;

            User user = lp.getUserManager().getUser(p.getUniqueId());
            if (user.getPrimaryGroup().equals("admin")) {
                if (args.length >= 1) {
                    if (Objects.equals(args[0], "total")) {
                        toggleVanish(p, true);
                    }
                }
            }
        }
        return true;
    }

    public static void toggleVanish(Player p, boolean total) {
        if (vanished.contains(p)) {
            for (Player people : Bukkit.getOnlinePlayers())
                people.showPlayer(plugin, p);
            vanished.remove(p);
            Logging.log(p, "Your total vanish has been", "disabled");
        }
        else{
            for (Player people : Bukkit.getOnlinePlayers())
                people.hidePlayer(plugin, p);
            vanished.add(p);
            Logging.log(p, "Your total vanish has been", "enabled");
            while(vanished.contains(p)) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Vanish Enabled"));
                    }
                }, 2, 1);
            }
        }
    }
}
