package net.mindmc.gigantwars.instance;

import net.mindmc.gigantwars.GameState;
import net.mindmc.gigantwars.utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        this.points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendTitle(Format.color("&2&lGIGANT&f&lWARS"), Format.color("&f&lIl game è iniziato."));
        arena.sendMessage(Format.color("&aIl game è iniziato! &6Obbiettivo&7: Distruggi 20 blocchi."));

        for(UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
    }

    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if(playerPoints == 20) {
            arena.sendMessage(Format.color("&6" + player.getName() + "Ha vinto la partita!"));
            arena.reset(true);
            return;
        }

        player.sendMessage(Format.color("&5+ 1 Punto"));
        points.replace(player.getUniqueId(), playerPoints);
    }
}
