package me.riches.staffplugin.commands;

import me.riches.staffplugin.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StaffCommand implements CommandExecutor {
    ArrayList<Player> staffPlayers = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (staffPlayers.contains(p)) {
                p.getInventory().clear();
                staffPlayers.remove(p);
            }
            else {
                staffPlayers.add(p);
                ItemStack[] inv = ItemManager.createStaffItems();
                for (int i = 0; i < inv.length; i++) {
                    p.getInventory().setItem(i, inv[i]);
                }
            }
        }
        return true;
    }
}
