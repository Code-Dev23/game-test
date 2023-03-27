package net.mindmc.gigantwars.listeners;

import net.mindmc.gigantwars.Minigame;
import net.mindmc.gigantwars.instance.Arena;
import net.mindmc.gigantwars.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.MapInitializeEvent;

public class ConnectListener implements Listener {

    private Minigame minigame;

    public ConnectListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e ) {

        Player player = e.getPlayer();
        player.teleport(ConfigManager.getLobbySpawn());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e ) {
        Arena arena = minigame.getArenaManager().getArena(e.getPlayer());
        if(arena != null) {
            arena.removePlayer(e.getPlayer());
        }
    }
}
