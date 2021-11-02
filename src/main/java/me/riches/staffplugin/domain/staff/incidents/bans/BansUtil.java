package me.riches.staffplugin.domain.staff.incidents.bans;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Set;

public class BansUtil {

    public static ArrayList<ItemStack> findBannedPlayers(Player p) {
        ItemStack blank = new ItemStack(Material.AIR);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName(ChatColor.AQUA + "Back");
        ArrayList<String> back_lore = new ArrayList<>();
        back_lore.add(ChatColor.GRAY + "Back to Previous Panel");
        back_meta.setLore(back_lore);
        back.setItemMeta(back_meta);

        ArrayList<ItemStack> banned_players = new ArrayList<>();
        Set<BanEntry> player_list = Bukkit.getBanList(BanList.Type.NAME).getBanEntries();
        for (int i = 0;i < player_list.size();i++) {
            Object[] array = player_list.toArray();
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();
            //meta.setDisplayName(array[i].getDisplayName());

            ArrayList<String> lore = new ArrayList<>();
            meta.setLore(lore);
            playerHead.setItemMeta(meta);

            SkullMeta skull = (SkullMeta) playerHead.getItemMeta();
            skull.setOwningPlayer(p);

            playerHead.setItemMeta(skull);
            banned_players.add(playerHead);
        }

        return banned_players;
    }
}
