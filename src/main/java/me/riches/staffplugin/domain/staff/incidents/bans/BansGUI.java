package me.riches.staffplugin.domain.staff.incidents.bans;

import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import me.riches.staffplugin.Logging;
import me.riches.staffplugin.StaffPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.samjakob.spigui.SGMenu;

import java.util.ArrayList;

public class BansGUI {
    private static SpiGUI spiGUI;

    public static Inventory createGUI(Player p) {
        SGMenu myAwesomeMenu = StaffPlugin.getSpiGUI().create("&c&lCurrently Banned Players &c(Page {currentPage}/{maxPage})", 5);
        int count = 0;
        int page = 0;
        for (ItemStack skull : BansUtil.findBannedPlayers(p)) {
            count++;
            Player player = Bukkit.getPlayer(skull.getItemMeta().getDisplayName());
            if (count > 45) {
                count = 0;
                page++;
            }
            myAwesomeMenu.setButton(page, count, new SGButton(
                    new ItemBuilder(Material.PLAYER_HEAD)
                            .skullOwner(player.getDisplayName())
                            .name("&e&l" + player.getDisplayName())
                            .lore(
                                    "&eGame Mode: &6" + player.getGameMode().toString(),
                                    "&eLocation: &6" + String.format(
                                            "%.0f, %.0f, %.0f",
                                            player.getLocation().getX(),
                                            player.getLocation().getY(),
                                            player.getLocation().getZ()
                                    ),
                                    "&eExperience: &6" + player.getTotalExperience()
                            )
                            .build()
            ).withListener(event -> {
                Player player1 = (Player) event.getWhoClicked();
                Logging.log(player1, "You click on " + event.getCurrentItem().getItemMeta().getDisplayName());
            }));
        }

        return myAwesomeMenu.getInventory();
    }
}
