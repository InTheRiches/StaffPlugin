package me.riches.staffplugin.events;

import me.riches.staffplugin.Logging;
import me.riches.staffplugin.commands.AdminPanelCommand;
import me.riches.staffplugin.gui.BanGUI;
import me.riches.staffplugin.gui.GamemodeGUI;
import me.riches.staffplugin.gui.TimeGUI;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickEvent implements Listener {

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
            }

            e.setCancelled(true);
        }

        else if (e.getClickedInventory() == BanGUI.gui) {
            switch(Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case ARROW:
                    player.openInventory(AdminPanelCommand.gui);
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
}
