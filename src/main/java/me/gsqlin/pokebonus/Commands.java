package me.gsqlin.pokebonus;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BonusStats;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import me.gsqlin.pokebonus.utils.Bonus;
import me.gsqlin.pokebonus.utils.BonusData;
import me.gsqlin.pokebonus.utils.ParamParser;
import me.gsqlin.pokebonus.utils.PokeBonusData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

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
