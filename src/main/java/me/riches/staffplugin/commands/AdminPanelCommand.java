package me.riches.staffplugin.commands;

import me.riches.staffplugin.items.ItemManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AdminPanelCommand implements CommandExecutor {

    public static Inventory gui;

    public static Inventory createGUI(Player p) {

        gui = Bukkit.createInventory(p, 45, ChatColor.BLACK + "Admin Panel");

        ItemStack suicide = new ItemStack(Material.TNT);
        ItemStack feed = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack blank = new ItemStack(Material.AIR);
        ItemStack ban = new ItemStack(Material.BARRIER);
        ItemStack time = new ItemStack(Material.CLOCK);
        ItemStack gamemode = new ItemStack(Material.BEACON);


        ItemMeta suicide_meta = suicide.getItemMeta();
        suicide_meta.setDisplayName(ChatColor.RED + "Suicide");
        ArrayList<String> suicide_lore = new ArrayList<>();
        suicide_lore.add(ChatColor.GOLD + "Kill yourself.");
        suicide_meta.setLore(suicide_lore);
        suicide_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        suicide.setItemMeta(suicide_meta);

        ItemMeta time_meta = time.getItemMeta();
        time_meta.setDisplayName(ChatColor.YELLOW + "Set Time");
        ArrayList<String> time_lore = new ArrayList<>();
        time_lore.add(ChatColor.GOLD + "Become a God and Let There Be Light");
        time_meta.setLore(time_lore);
        time.setItemMeta(time_meta);

        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.RED + "Ban");
        ArrayList<String> ban_lore = new ArrayList<>();
        ban_lore.add(ChatColor.DARK_RED + "Ban a user.");
        ban_meta.setLore(ban_lore);
        ban.setItemMeta(ban_meta);

        ItemMeta feed_meta = feed.getItemMeta();
        feed_meta.setDisplayName(ChatColor.DARK_GREEN + "Godmode");
        ArrayList<String> feed_lore = new ArrayList<>();
        feed_lore.add(ChatColor.GOLD + "Become the almighty");
        feed_meta.setLore(feed_lore);
        feed_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        feed.setItemMeta(feed_meta);

        ItemMeta sword_meta = sword.getItemMeta();
        sword_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sword");
        ArrayList<String> sword_lore = new ArrayList<>();
        sword_lore.add(ChatColor.GOLD + "Get a sword.");
        sword_meta.setLore(sword_lore);
        sword_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sword.setItemMeta(sword_meta);

        ItemMeta gamemode_meta = gamemode.getItemMeta();
        gamemode_meta.setDisplayName(ChatColor.BLUE + "Gamemode");
        ArrayList<String> gamemode_lore = new ArrayList<>();
        gamemode_lore.add(ChatColor.DARK_BLUE + "Creative, Survival and Spectator");
        gamemode_meta.setLore(gamemode_lore);
        gamemode.setItemMeta(gamemode_meta);

        ItemStack[] menu_items = {
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                blank, blank, blank, gamemode, ban, ItemManager.vanish, blank, blank, blank,
                blank, blank, blank, suicide, feed, sword, blank, blank, blank,
                blank, blank, blank, blank, time, blank, blank, blank, blank,
                blank, blank, blank, blank, blank, blank, blank, blank, blank
        };
        gui.setContents(menu_items);

        return gui;
    }

    public static ItemStack createItem() {

        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLACK + "Staff Menu" + ChatColor.GRAY + " (Right Color)");
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right click to open the staff menu!");
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            p.openInventory(createGUI(p));
        }
        return true;
    }
}
