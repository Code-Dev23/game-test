package net.mindmc.gigantwars.managers;

import net.mindmc.gigantwars.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandManager implements CommandExecutor {
    String command;
    String permissions;
    boolean console;

    public CommandManager(String command, String permissions, boolean console){
        this.command = command;
        this.permissions = permissions;
        this.console = console;

        Minigame.getInstance().getCommand(command).setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!console && !(sender instanceof Player)) {
            Bukkit.getLogger().info(Minigame.messages.getString("no-console"));
        }
        if(permissions != null && !sender.hasPermission(permissions)) {
            Bukkit.getLogger().info(Minigame.messages.getString("no-permissions"));
        }

        execute(sender, args);
        return true;
    }
}