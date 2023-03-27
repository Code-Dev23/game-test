package net.mindmc.gigantwars.managers;

import net.mindmc.gigantwars.instance.Arena;
import net.mindmc.gigantwars.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(Minigame minigame) {
        for(String str : Minigame.arenas.getConfigurationSection("arenas.").getKeys(false)) {
            arenas.add(new Arena(minigame, Integer.parseInt(str), new Location(
                    Bukkit.getWorld(Minigame.arenas.getString("arenas." + str + ".world")),
                    Minigame.arenas.getDouble("arenas." + str + ".x"),
                    Minigame.arenas.getDouble("arenas." + str + ".y"),
                    Minigame.arenas.getDouble("arenas." + str + ".z"),
                    (float) Minigame.arenas.getDouble("arenas." + str + ".yaw"),
                    (float) Minigame.arenas.getDouble("arenas." + str + ".pitch"))));
        }
    }

    public List<Arena> getArenas() { return arenas; }
    public Arena getArena(Player player) {
        for(Arena arena : arenas) {
            if(arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for(Arena arena : arenas) {
            if(arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }
}
