package me.riches.staffplugin.domain.staff.incidents;

import me.riches.staffplugin.events.ClickEvent;
import me.riches.staffplugin.listeners.PlayerEventsListener;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Set;

public class IncidentsGUI {

    public static Inventory gui;
    public static Inventory gui2;

    public static ItemStack reports = new ItemStack(Material.PAPER);
    public static ItemStack banned = new ItemStack(Material.PLAYER_HEAD);
    public static ItemStack unban = new ItemStack(Material.TOTEM_OF_UNDYING);

    static ItemStack blank = new ItemStack(Material.AIR);

    static ItemStack[] menu_items;

    public static Inventory createGUI(Player p) {
        // Create the GUI
        gui = Bukkit.createInventory(p, 27, ChatColor.BLACK + "Player Incidents Panel");

        // Create the items
        ItemStack back = new ItemStack(Material.ARROW);

        ItemMeta reports_meta = reports.getItemMeta();
        reports_meta.setDisplayName(ChatColor.AQUA + "Player Reports");
        reports_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> reports_lore = new ArrayList<>();
        reports_lore.add(ChatColor.GRAY + "Displays all player reports.");
        reports_meta.setLore(reports_lore);
        reports.setItemMeta(reports_meta);

        ItemMeta banned_meta = banned.getItemMeta();
        banned_meta.setDisplayName(ChatColor.AQUA + "Open Banned Panel");
        ArrayList<String> banned_lore = new ArrayList<>();
        banned_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        banned_lore.add(ChatColor.GRAY + "Displays currently banned players.");
        banned_meta.setLore(banned_lore);
        banned.setItemMeta(banned_meta);

        ItemMeta unban_meta = unban.getItemMeta();
        unban_meta.setDisplayName(ChatColor.AQUA + "Unban");
        ArrayList<String> unban_lore = new ArrayList<>();
        unban_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        unban_lore.add(ChatColor.GRAY + "Unban a user.");
        unban_meta.setLore(unban_lore);
        unban.setItemMeta(unban_meta);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.GREEN + "Back");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.GRAY + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        menu_items = new ItemStack[]{
                back, reports, blank, blank, blank, blank, blank, blank, banned,
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
        };
        gui.setContents(menu_items);

        return gui;
    }
}
