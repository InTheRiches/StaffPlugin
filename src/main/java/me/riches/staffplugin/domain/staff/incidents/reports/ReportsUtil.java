package me.riches.staffplugin.domain.staff.incidents.reports;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReportsUtil {
    public static Inventory createGUI (Player p) {
        Inventory gui = Bukkit.createInventory(p, 5, ChatColor.BLACK + "Reports Menu");

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.AQUA + "Back");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.GRAY + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        gui.setItem(0, back);
        return gui;
    }
}
