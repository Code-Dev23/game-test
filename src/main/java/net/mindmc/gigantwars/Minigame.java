package net.mindmc.gigantwars;

import net.mindmc.gigantwars.commands.GWMainCommand;
import net.mindmc.gigantwars.commands.GWSetupCommand;
import net.mindmc.gigantwars.listeners.ConnectListener;
import net.mindmc.gigantwars.listeners.GameListener;
import net.mindmc.gigantwars.managers.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Minigame extends JavaPlugin {

    @Override
    public void onEnable() {
        instance = this;
        plugin = this;

        config = createFileConfig("config.yml");
        messages = createFileConfig("messages.yml");
        arenas = createFileConfig("arenas.yml");
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(true);
        saveDefaultConfig();

        arenaManager = new ArenaManager(this);

        Listeners();
        Commands();
    }

    public void Listeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ConnectListener(this), this);
        pm.registerEvents(new GameListener(this), this);
    }

    public void Commands() {
        new GWMainCommand(this);
        new GWSetupCommand();
    }

    public ArenaManager getArenaManager() { return arenaManager; }

    private static Minigame instance;
    private static Plugin plugin;
    private ArenaManager arenaManager;

    public static FileConfiguration config;
    public static FileConfiguration messages;
    public static FileConfiguration arenas;

    public static Minigame getInstance() {
        return instance;
    }

    public static FileConfiguration createFileConfig(String fileName) {
        File configFile = new File(plugin.getDataFolder(), fileName);

        if(!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }
        FileConfiguration configuration = new YamlConfiguration();
        try {
            configuration.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    public static void saveMainConfig(FileConfiguration config, String fileName) {
        File configFile = new File(Minigame.plugin.getDataFolder(), fileName);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
