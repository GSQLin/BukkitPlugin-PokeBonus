package me.gsqlin.pokebonus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    static PokeBonus plugin = PokeBonus.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) return false;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        sender.sendMessage("已重载");
        return false;
    }
}
