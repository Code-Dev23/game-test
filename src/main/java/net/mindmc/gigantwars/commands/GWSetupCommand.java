package net.mindmc.gigantwars.commands;

import me.clip.placeholderapi.commands.PlaceholderCommand;
import net.mindmc.gigantwars.Minigame;
import net.mindmc.gigantwars.managers.CommandManager;
import net.mindmc.gigantwars.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GWSetupCommand extends CommandManager {

    public GWSetupCommand() {
        super("gwsetup", "giantwars.gwsetup", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        switch (args[0]) {
            case "setLobbySpawn":
                Minigame.config.set("lobby-spawn.world", Bukkit.getWorld(player.getWorld().getName()));
                Minigame.config.set("lobby-spawn.x", player.getLocation().getX());
                Minigame.config.set("lobby-spawn.y", player.getLocation().getY());
                Minigame.config.set("lobby-spawn.z", player.getLocation().getZ());
                Minigame.config.set("lobby-spawn.yaw", player.getLocation().getYaw());
                Minigame.config.set("lobby-spawn.pitch", player.getLocation().getPitch());

                player.sendMessage(Format.color("&aLo spawn della lobby è stato impostato con successo."));
                break;
            case "setGameSpawn":
                if(args[1] == null) {
                    player.sendMessage(Format.color("&cNon hai inserito l'id del game."));
                }

                Minigame.arenas.set("arenas. " + args[1] + ".world", Bukkit.getWorld(player.getWorld().getName()));
                Minigame.arenas.set("arenas. " + args[1] + ".x", player.getLocation().getX());
                Minigame.arenas.set("arenas. " + args[1] + ".y", player.getLocation().getY());
                Minigame.arenas.set("arenas. " + args[1] + ".z", player.getLocation().getZ());
                Minigame.arenas.set("arenas. " + args[1] + ".yaw", player.getLocation().getYaw());
                Minigame.arenas.set("arenas. " + args[1] + ".pitch", player.getLocation().getPitch());

                player.sendMessage(Format.color("&aLo spawn del game è stato impostato con successo."));
                break;
            default:
                player.sendMessage(Format.color("&cUtilizza: /gwsetup [setLobbySpawn/setGameSpawn <id>] "));
                break;
        }
    }
}
