package me.riches.staffplugin.domain.staff.vanish;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.StaffPlugin;
import me.riches.staffplugin.files.CustomConfigFile;
import me.riches.staffplugin.items.ItemManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class VanishCommand implements CommandExecutor {

    public static StaffPlugin plugin;

    public static List<String> vanished = new ArrayList<>();

    public VanishCommand(StaffPlugin plugin) {
        vanished = CustomConfigFile.get().getStringList("members-in-vanish");
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
                if (vanished.contains(p.getDisplayName())) {
                    disableVanish(p);
                }
                else {
                    enableVanish(p, false);
                }
            }
        }
        return true;
    }

    public static void enableVanish(Player p, boolean onJoin) {
        if (onJoin) {
            System.out.println("Toggled Vanish");
            for (Player people : Bukkit.getOnlinePlayers())
                people.hidePlayer(plugin, p);
            Logging.log(p, "Your total vanish has been", "enabled");
            enchantItem(p, true);
            int task = 0;
            int finalTask = task;
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if (!vanished.contains(p.getDisplayName())){
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Vanish Disabled"));
                        Bukkit.getScheduler().cancelTask(finalTask);
                    }
                    else{
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Vanish Enabled"));
                    }

                }
            },0, 20);

        }
        else {
            for (Player people : Bukkit.getOnlinePlayers())
                people.hidePlayer(plugin, p);
            vanished.add(p.getDisplayName());
            CustomConfigFile.get().set("members-in-vanish", vanished);
            CustomConfigFile.save();
            CustomConfigFile.reload();
            enchantItem(p, true);
            Logging.log(p, "Your total vanish has been", "enabled");
            int task = 0;
            int finalTask = task;
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    if (!vanished.contains(p.getDisplayName())){
                        Bukkit.getScheduler().cancelTask(finalTask);
                    }
                    else{
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Vanish Enabled"));
                    }

                }
            },0, 20);
        }
    }

    public static void disableVanish(Player p) {
        for (Player people : Bukkit.getOnlinePlayers())
            people.showPlayer(plugin, p);
        vanished.remove(p.getDisplayName());
        CustomConfigFile.get().set("members-in-vanish", vanished);
        CustomConfigFile.save();
        CustomConfigFile.reload();
        enchantItem(p, false);
        Logging.log(p, "Your total vanish has been", "disabled");
    }

    public static boolean toggleVanish(Player p, boolean onJoin) {
        if (vanished.contains(p.getDisplayName())) {
            disableVanish(p);
            return false;
        }
        else {
            enableVanish(p, onJoin);
            return true;
        }
    }

    public static void enchantItem(Player p, boolean toggle) {
        for (ItemStack Item : p.getInventory().getContents()) {
            if (Item == null) {
                return;
            }
            else if (Item.getItemMeta().getItemFlags().equals(ItemManager.vanish.getItemMeta().getItemFlags())) {
                System.out.println("Item is a squid ink and matches the staff one.");
                ItemMeta meta = Item.getItemMeta();
                if (!toggle) {
                    meta.removeEnchant(Enchantment.LUCK);
                }
                else {
                    meta.addEnchant(Enchantment.LUCK, 1, false);
                }
                Item.setItemMeta(meta);
            }
        }
    }
}
