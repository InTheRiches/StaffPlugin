package me.riches.staffplugin.domain.staff.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GamemodeGUI {

    public static Inventory gui;

    public static Inventory createGUI(Player p) {
        gui = Bukkit.createInventory(p, 45, ChatColor.BLACK + "Gamemode Panel");

        //Create Items
        ItemStack blank = new ItemStack(Material.AIR);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemStack creative = new ItemStack(Material.BEACON);
        ItemStack survival = new ItemStack(Material.IRON_PICKAXE);
        ItemStack spectator = new ItemStack(Material.ENDER_EYE);

        //Set Item Values
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.GREEN + "Go Back I Want to be MONKEY");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.LIGHT_PURPLE + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        ItemMeta creative_meta = creative.getItemMeta();
        creative_meta.setDisplayName(ChatColor.GREEN + "Creative Mode");
        ArrayList<String> creative_lore = new ArrayList<>();
        creative_lore.add(ChatColor.LIGHT_PURPLE + "Become Invincible >:)");
        creative_meta.setLore(creative_lore);
        creative.setItemMeta(creative_meta);

        ItemMeta survival_meta = survival.getItemMeta();
        survival_meta.setDisplayName(ChatColor.GREEN + "Survival Mode");
        ArrayList<String> survival_lore = new ArrayList<>();
        survival_lore.add(ChatColor.LIGHT_PURPLE + "Become a Mortal :(");
        survival_meta.setLore(survival_lore);
        survival.setItemMeta(survival_meta);

        ItemMeta spectator_meta = spectator.getItemMeta();
        spectator_meta.setDisplayName(ChatColor.GREEN + "Spectator Mode");
        ArrayList<String> spectator_lore = new ArrayList<>();
        spectator_lore.add(ChatColor.LIGHT_PURPLE + "O .,;,,,;,. O");
        spectator_meta.setLore(spectator_lore);
        spectator.setItemMeta(spectator_meta);

        //Set Item Placement
        ItemStack[] menu_items = {
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                blank, blank, blank, creative, survival, spectator, blank, blank, blank,
                blank, blank, blank, blank, blank, blank, blank, blank, blank,
                back, blank, blank, blank, blank, blank, blank, blank, blank
        };
        gui.setContents(menu_items);

        return gui;
    }
}
