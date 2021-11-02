package me.riches.staffplugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.samjakob.spigui.SpiGUI;
import me.riches.staffplugin.commands.AdminPanelCommand;
import me.riches.staffplugin.commands.SmiteCommand;
import me.riches.staffplugin.commands.StaffCommand;
import me.riches.staffplugin.domain.staff.vanish.VanishCommand;
import me.riches.staffplugin.domain.staff.vanish.VanishEvents;
import me.riches.staffplugin.events.ClickEvent;
import me.riches.staffplugin.files.CustomConfigFile;
import me.riches.staffplugin.items.ItemManager;
import me.riches.staffplugin.listeners.PlayerEventsListener;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffPlugin extends JavaPlugin {

    StaffPlugin plugin;
    static SpiGUI spiGUI;

    @Override
    public void onEnable() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://admin:5SYlYwAOnzmMEk5s@reports.xvbeu.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("bans");
        MongoCollection<Document> bans = database.getCollection("playerBans");


        spiGUI = new SpiGUI(this);
        plugin = this;

        CustomConfigFile.setup(getServer());

        System.out.println(ChatColor.GREEN + "StaffPlugin A.01 has started.");

        getServer().getPluginManager().registerEvents(new PlayerEventsListener(getServer()), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new VanishEvents(), this);

        new ItemManager();

        getCommand("staff").setExecutor(new StaffCommand());
        getCommand("admin").setExecutor(new AdminPanelCommand());
        getCommand("smite").setExecutor(new SmiteCommand(getServer()));
        getCommand("vanish").setExecutor(new VanishCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SpiGUI getSpiGUI() {
        return spiGUI;
    }
}
