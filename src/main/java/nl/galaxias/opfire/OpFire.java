package nl.galaxias.opfire;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by wdele on 17-04-15.
 */
public class OpFire extends JavaPlugin {
    private static Plugin plugin;
    FileConfiguration config = getConfig();

    public void onEnable() {
        plugin = this;

        config.addDefault("general.enable-plugin", true);
        config.addDefault("messages.sent-to-player", "&cDon't try to use !command!");
        config.addDefault("messages.notification", "!player! &ctried to use !command!");

        config.options().copyDefaults(true);
        saveConfig();

        registerEvents(this, new OpListener());

        if (!(getConfig().getBoolean("general.enable-plugin"))) {
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    public void onDisable() {
        plugin = null;
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}