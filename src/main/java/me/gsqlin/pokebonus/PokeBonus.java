package me.gsqlin.pokebonus;

import com.google.gson.Gson;
import me.gsqlin.pokebonus.utils.PokeData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PokeBonus extends JavaPlugin {
    public Gson gson;

    public PokeData pokeData;

    @Override
    public void onLoad() {
        gson = new Gson();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        pokeData = PokeData.getInstance();
        getCommand("pokebonus").setExecutor(new Commands());
        getLogger().info("§aPlugin enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("§rSaving pokemon bonus data!Please waiting!");
        PokeData.save();
        getLogger().info("§aPlugin disabled!");
    }

    public static PokeBonus getInstance(){
        return ((PokeBonus) Bukkit.getPluginManager().getPlugin("PokeBonus"));
    }
}
