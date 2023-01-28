package me.gsqlin.pokebonus;

import com.google.gson.Gson;
import me.gsqlin.pokebonus.utils.PokeBonusData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PokeBonus extends JavaPlugin {
    public Gson gson;

    public PokeBonusData pokeBonusData;

    @Override
    public void onLoad() {
        gson = new Gson();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        pokeBonusData = PokeBonusData.getInstance();
        getCommand("pokebonus").setExecutor(new Commands());
        getLogger().info("§aPlugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("§rSaving pokemon bonus data!Please waiting!");
        PokeBonusData.save();
        getLogger().info("§aPlugin disabled!");
    }

    public static PokeBonus getInstance(){
        return ((PokeBonus) Bukkit.getPluginManager().getPlugin("PokeBonus"));
    }
}
