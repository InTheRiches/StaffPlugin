package me.riches.staffplugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BanGUI {

    public static Inventory gui;
    public static Inventory gui2;

    public static Inventory createGUI(Player p) {
        // Create the GUI
        gui = Bukkit.createInventory(p, 45, ChatColor.BLACK + "Ban Panel");

        // Create the items
        ItemStack temp = new ItemStack(Material.CLOCK);
        ItemStack perm = new ItemStack(Material.BARRIER);
        ItemStack unban = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemStack blank = new ItemStack(Material.AIR);
        ItemStack blank2 = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemStack blank3 = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemStack search = new ItemStack(Material.MAP);

        // This is where the items are created
        ItemMeta blank2_meta = blank2.getItemMeta();
        blank2_meta.setDisplayName("");
        blank2.setItemMeta(blank2_meta);

        ItemMeta search_meta = search.getItemMeta();
        search_meta.setDisplayName("Search");
        search.setItemMeta(search_meta);

        ItemMeta temp_meta = temp.getItemMeta();
        temp_meta.setDisplayName(ChatColor.RED + "Temp Ban");
        ArrayList<String> temp_lore = new ArrayList<>();
        temp_lore.add(ChatColor.GOLD + "Temp ban a user.");
        temp_meta.setLore(temp_lore);
        temp.setItemMeta(temp_meta);

        ItemMeta perm_meta = perm.getItemMeta();
        perm_meta.setDisplayName(ChatColor.RED + "Perm Ban");
        ArrayList<String> perm_lore = new ArrayList<>();
        perm_lore.add(ChatColor.DARK_RED + "Perm Ban a user.");
        perm_meta.setLore(perm_lore);
        perm.setItemMeta(perm_meta);

        ItemMeta unban_meta = unban.getItemMeta();
        unban_meta.setDisplayName(ChatColor.RED + "Unban");
        ArrayList<String> unban_lore = new ArrayList<>();
        unban_lore.add(ChatColor.GREEN + "Unban a user.");
        unban_meta.setLore(unban_lore);
        unban.setItemMeta(unban_meta);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.GREEN + "Go Back I Want to be MONKEY");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.LIGHT_PURPLE + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        ItemStack[] menu_items = {
                back, blank, blank, temp, unban, perm, blank, blank, blank,
                blank2, blank2, blank2, blank2, blank2, blank2, blank2, blank2, blank2,
                blank3, blank3, blank3, blank3, blank3, blank3, blank3, blank3, search
        };
        gui.setContents(menu_items);

        return gui;
    }

    public static Inventory createBanGUI(Player p) {
        ItemStack back = new ItemStack(Material.ARROW);
        ItemStack temp = new ItemStack(Material.CLOCK);
        ItemStack perm = new ItemStack(Material.BARRIER);
        ItemStack unban = new ItemStack(Material.TOTEM_OF_UNDYING);


        ArrayList<Player> player_list = new ArrayList<>(p.getServer().getOnlinePlayers());
        gui2 = Bukkit.createInventory(p, 54, ChatColor.BLACK + "Perm Ban Panel | Player List");

        for (int i = 0;i < player_list.size();i++) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();
            meta.setDisplayName(player_list.get(i).getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Player Health: " + player_list.get(i).getHealth());
            lore.add(ChatColor.GOLD + "EXP: " + player_list.get(i).getExp());
            meta.setLore(lore);
            playerHead.setItemMeta(meta);
            gui2.addItem(playerHead);
        }

        gui2.setItem(46, back);

        return gui2;
    }
}
