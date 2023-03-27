package net.mindmc.gigantwars.instance;

import net.mindmc.gigantwars.GameState;
import net.mindmc.gigantwars.Minigame;
import net.mindmc.gigantwars.managers.ConfigManager;
import net.mindmc.gigantwars.utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Minigame minigame;
    private Arena arena;
    private int countdown;

    public Countdown(Minigame minigame, Arena arena) {
        this.minigame = minigame;
        this.arena = arena;
        this.countdown = ConfigManager.getCountdown();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigame, 0, 20);
    }

    @Override
    public void run() {
        if(countdown == 0) {
            cancel();
            arena.start();
            return;
        }

        if(countdown <= 10 || countdown % 15 == 0) {
            arena.sendMessage(Format.color("&3&lGIANTWARS &f» &7Il game inizierà tra &2" + countdown + " &7secondi" + (countdown == 1 ? "" : "s") + "."));
        }


        arena.sendTitle(Format.color("&c" + countdown + " secondi" + (countdown == 1 ? "" : "s")), "");

        countdown--;
    }
}
