package me.riches.staffplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TimeGUI {

    public static Inventory gui;

    public static Inventory createGUI(Player p) {
        gui = Bukkit.createInventory(p, 45, ChatColor.BLACK + "Time Set Panel");

        //Create Items
        ItemStack blank = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemStack morning = new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA);
        ItemStack midday = new ItemStack(Material.BLUE_GLAZED_TERRACOTTA);
        ItemStack night = new ItemStack(Material.BLACK_GLAZED_TERRACOTTA);

        //Set Item Values
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.GREEN + "Go Back I Want to be MONKEY");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.LIGHT_PURPLE + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        ItemMeta morning_meta = morning.getItemMeta();
        morning_meta.setDisplayName(ChatColor.YELLOW + "Make it Morning");
        ArrayList<String> morning_lore = new ArrayList<>();
        morning_lore.add(ChatColor.GOLD + "Im a Morning Person");
        morning_meta.setLore(morning_lore);
        morning.setItemMeta(morning_meta);

        ItemMeta midday_meta = midday.getItemMeta();
        midday_meta.setDisplayName(ChatColor.BLUE + "Make it Midday");
        ArrayList<String> midday_lore = new ArrayList<>();
        midday_lore.add(ChatColor.DARK_GREEN + "Lets Go to Lunch");
        midday_meta.setLore(midday_lore);
        midday.setItemMeta(midday_meta);

        ItemMeta night_meta = night.getItemMeta();
        night_meta.setDisplayName(ChatColor.DARK_GRAY + "Make it Night");
        ArrayList<String> night_lore = new ArrayList<>();
        night_lore.add(ChatColor.DARK_RED + "Slowly Die Inside While Eating your 5th Bowl of Cereal that Day");
        night_meta.setLore(night_lore);
        night.setItemMeta(night_meta);

        //Set Item Placement
        ItemStack[] menu_items = {
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                blank, blank, blank, morning, midday, night, blank, blank, blank,
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                back, blank, blank, blank, blank, blank, blank, blank, blank
        };
        gui.setContents(menu_items);

        return gui;
    }
}
