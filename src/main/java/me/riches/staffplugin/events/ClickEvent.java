package me.riches.staffplugin.events;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.StaffPlugin;
import me.riches.staffplugin.commands.AdminPanelCommand;
import me.riches.staffplugin.commands.StaffCommand;
import me.riches.staffplugin.commands.VanishCommand;
import me.riches.staffplugin.gui.BanGUI;
import me.riches.staffplugin.gui.GamemodeGUI;
import me.riches.staffplugin.gui.TimeGUI;
import me.riches.staffplugin.items.ItemManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Objects;

public class ClickEvent implements Listener {

    public static ItemStack banGuiButtonClick = null;
    public static boolean vanish = false;

    StaffPlugin plugin;

    public ClickEvent(StaffPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory() == AdminPanelCommand.gui) {

            switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case TNT:
                    player.closeInventory();
                    player.setHealth(0.0);
                    Logging.log(player, "You commmmmmmmmmmmited suicide.");
                    break;
                case TOTEM_OF_UNDYING:
                    if(player.isInvulnerable()) {
                        player.setInvulnerable(false);
                        Logging.log(player, "You have stepped down from being almighty.");
                    }
                    else {
                        player.setInvulnerable(true);
                        Logging.log(player, "You have became the almighty. We all bow down to you.");
                    }
                    break;
                case DIAMOND_SWORD:
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    Logging.log(player, "You have recieved 1x Diamond Sword");
                    break;

                case BARRIER:
                    player.openInventory(BanGUI.createGUI(player));
                    break;

                case CLOCK:
                    player.openInventory(TimeGUI.createGUI(player));
                    break;

                case BEACON:
                    player.openInventory(GamemodeGUI.createGUI(player));
                    break;
                case INK_SAC:
                    VanishCommand.toggleVanish(player, true);
                    break;
            }

            e.setCancelled(true);
        }
        else if (e.getClickedInventory() == BanGUI.gui) {
            switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case ARROW:
                    player.openInventory(AdminPanelCommand.gui);
                    break;
                case BARRIER:
                case CLOCK:
                case TOTEM_OF_UNDYING:
                    ItemStack previous = banGuiButtonClick.clone();
                    ItemStack item = e.getCurrentItem();
                    ItemMeta meta = item.getItemMeta();
                    ItemMeta otherItemMeta;
                    Material temp = previous.getType();
                    System.out.println(temp);
                    otherItemMeta = previous.getItemMeta();
                    otherItemMeta.removeEnchant(Enchantment.LUCK);

                    meta.addEnchant(Enchantment.LUCK, 1, true);
                    item.setItemMeta(meta);

                    ItemStack[] check = e.getClickedInventory().getContents();
                    for (int i = 0;i < check.length;i++) {
                        if (check[i] != null) {
                            System.out.println(check[i].getType());
                            if (check[i].getType() == temp) {
                                previous.setItemMeta(otherItemMeta);
                                check[i].setItemMeta(otherItemMeta);
                            }
                            if (check[i].getItemMeta() == meta) {
                                check[i] = item;
                            }
                        }
                    }
                    banGuiButtonClick = item;
                    e.setCancelled(true);
                    break;
            }

            e.setCancelled(true);
        }
        else if (e.getClickedInventory() == TimeGUI.gui) {

            switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case ARROW:
                    player.openInventory(AdminPanelCommand.gui);
                    break;

                case YELLOW_GLAZED_TERRACOTTA:
                    player.getWorld().setTime(0);
                    Logging.log(player, "Good Morning!");
                    break;

                case BLUE_GLAZED_TERRACOTTA:
                    player.getWorld().setTime(6000);
                    Logging.log(player, "Good Afternoon!");
                    break;

                case BLACK_GLAZED_TERRACOTTA:
                    player.getWorld().setTime(18000);
                    Logging.log(player, "Good Night!");
                    break;


            }

            e.setCancelled(true);
        }
        else if (e.getClickedInventory() == GamemodeGUI.gui) {
            switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case ARROW:
                    player.openInventory(AdminPanelCommand.gui);
                    break;

                case BEACON:
                    player.setGameMode(GameMode.CREATIVE);
                    break;

                case IRON_PICKAXE:
                    player.setGameMode(GameMode.SURVIVAL);
                    break;

                case ENDER_EYE:
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void rightClickEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        try {
            e.getItem();
        } catch(NullPointerException ex) {return;}

        if (Objects.equals(e.getItem(), AdminPanelCommand.createItem())) {
            player.openInventory(AdminPanelCommand.createGUI(player));
        }

        if (Objects.equals(e.getItem().getItemMeta().getItemFlags(), ItemManager.vanish.getItemMeta().getItemFlags())) {
            VanishCommand.toggleVanish(player, true);
            ItemStack item = e.getItem();
            ItemMeta meta = item.getItemMeta();
            if (vanish) {
                meta.removeEnchant(Enchantment.LUCK);
                vanish = false;
            }
            else {
                meta.addEnchant(Enchantment.LUCK, 1, false);
                vanish = true;
            }

            ItemStack[] check = e.getPlayer().getInventory().getContents();
            for (int i = 0;i < check.length;i++) {
                if (check[i] != null) {
                    if (check[i].getType() == item.getType()) {
                        check[i].setItemMeta(meta);
                    }
                }
            }
        }
    }
}
