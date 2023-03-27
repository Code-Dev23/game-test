package net.mindmc.gigantwars.managers;

import net.mindmc.gigantwars.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ConfigManager {
    public static int getRequiredPlayers() { return Minigame.config.getInt("required-players"); }

    public static int getCountdown() { return Minigame.config.getInt("countdown"); }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(Minigame.config.getString("lobby-spawn.world")),
                Minigame.config.getDouble("lobby-spawn.x"),
                Minigame.config.getDouble("lobby-spawn.y"),
                Minigame.config.getDouble("lobby-spawn.z"),
                (float) Minigame.config.getDouble("lobby-spawn.yaw"),
                (float) Minigame.config.getDouble("lobby-spawn.pitch"));
    }
}