package me.riches.staffplugin.domain.staff.vanish;

import me.riches.staffplugin.files.CustomConfigFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishEvents implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        if (CustomConfigFile.get().getStringList("members-in-vanish").contains(e.getPlayer().getDisplayName())) {
            VanishCommand.enableVanish(e.getPlayer(), true);
            System.out.println("Giving Vanish");
        }
    }
}
