package me.riches.staffplugin.items;

import me.riches.staffplugin.commands.AdminPanelCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {
    public static ItemStack vanish;

    public static ItemStack[] createStaffItems() {

        vanish = new ItemStack(Material.INK_SAC);
        ItemMeta vanishMeta = vanish.getItemMeta();
        vanishMeta.setDisplayName(ChatColor.GOLD + "Vanish");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Toggles your total vanish");
        vanishMeta.setLore(lore);
        vanish.setItemMeta(vanishMeta);

        ItemStack[] inv = new ItemStack[]{
                AdminPanelCommand.createItem(), vanish
        };
        System.out.println("Created Staff Items");
        return inv;
    }
}
