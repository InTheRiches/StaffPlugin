package me.riches.testingplugin.files;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup(Server server) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("TestingPlugin").getDataFolder(), "config.yml");

        if (!file.exists()) {
            try{
                file.createNewFile();
            }catch (IOException e){
                //bruh
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
        customFile.addDefault("welcome-message", true);
        customFile.addDefault("spawn-world", "world");
        customFile.addDefault("spawn-cords.X", server.getWorld(customFile.getString("spawn-world")).getSpawnLocation().getX());
        customFile.addDefault("spawn-cords.Y", server.getWorld(customFile.getString("spawn-world")).getSpawnLocation().getY());
        customFile.addDefault("spawn-cords.Z", server.getWorld(customFile.getString("spawn-world")).getSpawnLocation().getZ());
        customFile.addDefault("spawn-cords.YAW", 0.0);
        customFile.options().copyDefaults(true);
        save();
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try{
            customFile.save(file);
        }catch(IOException e) {
            System.out.println("Couldnt Save File");
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
