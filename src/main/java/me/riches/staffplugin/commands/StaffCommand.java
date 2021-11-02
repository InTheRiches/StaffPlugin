package me.riches.staffplugin.commands;

import me.riches.staffplugin.files.CustomConfigFile;
import me.riches.staffplugin.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StaffCommand implements CommandExecutor {
    public static List<String> staffPlayers = new ArrayList<>();

    public StaffCommand() {
        staffPlayers = CustomConfigFile.get().getStringList("members-in-staff");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (staffPlayers.contains(p.getDisplayName())) {
                p.getInventory().clear();
                staffPlayers.remove(p.getDisplayName());
                CustomConfigFile.get().set("members-in-staff", staffPlayers);
                CustomConfigFile.save();
                CustomConfigFile.reload();
            }
            else {
                staffPlayers.add(p.getDisplayName());
                CustomConfigFile.get().set("members-in-staff", staffPlayers);
                CustomConfigFile.save();
                CustomConfigFile.reload();
                toggleStaff(p);
            }
        }
        return true;
    }
    public static void toggleStaff(Player p) {
        ItemStack[] inv = ItemManager.createStaffItems();
        for (int i = 0; i < inv.length; i++) {
            p.getInventory().setItem(i, inv[i]);
        }
    }
}
