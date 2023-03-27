package net.mindmc.gigantwars.commands;

import me.clip.placeholderapi.commands.PlaceholderCommand;
import net.mindmc.gigantwars.GameState;
import net.mindmc.gigantwars.Minigame;
import net.mindmc.gigantwars.instance.Arena;
import net.mindmc.gigantwars.managers.CommandManager;
import net.mindmc.gigantwars.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GWMainCommand extends CommandManager {

    private Minigame minigame;


    public GWMainCommand(Minigame minigame) {
        super("gw", "giantwars.gw", false);
        this.minigame = minigame;
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
            player.sendMessage(Format.color("&aArene disponibili:"));
            for(Arena arena : minigame.getArenaManager().getArenas()) {
                player.sendMessage(Format.color("&e- " + arena.getId() + "&8[&3" + arena.getState().name() + "&8]"));
            }
        } else if(args.length == 1 && args[0].equalsIgnoreCase("leave")) {
            Arena arena = minigame.getArenaManager().getArena(player);
            if(arena != null) {
                player.sendMessage(Format.color("&eSei uscito dal game."));
            } else {
                player.sendMessage(Format.color("&cNon sei in nessun game!"));
                arena.removePlayer(player);
            }
        } else if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
            if(minigame.getArenaManager().getArena(player) != null) {
                player.sendMessage(Format.color("&eSei entrato nel game!"));

                return;
            }

            int id;
            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(Format.color("&cL'id inserito non è valido."));
                return;
            }

            if(id >= 0 && id < minigame.getArenaManager().getArenas().size()) {
                Arena arena = minigame.getArenaManager().getArena(id);
                if(arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN) {
                    player.sendMessage(Format.color("&aStai giocando nel game " + id + "&a."));
                    arena.addPlayer(player);
                } else {
                    player.sendMessage(Format.color("&cNon puoi entrare in questo game."));
                }
            } else
                player.sendMessage(Format.color("&cL'id inserito non è valido."));
        } else {
            player.sendMessage(Format.color("&cUtilizza uno dei seguenti comandi:"));
            player.sendMessage(Format.color("&c- /arena list"));
            player.sendMessage(Format.color("&c- /arena leave"));
            player.sendMessage(Format.color("&c- /arena join <id>"));
        }
    }
}
