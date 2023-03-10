package me.gsqlin.pokebonus.utils;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import me.gsqlin.pokebonus.PokeBonus;

import java.util.*;

public class PokeData {
    static PokeData pokeData;
    static PokeBonus plugin = PokeBonus.getInstance();


    private Map<UUID,BonusData> bonusDataMap = new HashMap<>();
    public Map<UUID, BonusData> getBonusDataMap() {
        return bonusDataMap;
    }
    public void setBonusDataMap(Map<UUID, BonusData> bonusDataMap) {
        this.bonusDataMap = bonusDataMap;
    }
    public BonusData getBonusData(Pokemon pokemon){
        UUID uuid = pokemon.getUUID();
        if (this.bonusDataMap.get(uuid) == null) {
            BonusData bonusData = new BonusData();
            this.bonusDataMap.put(uuid,bonusData);
        }
        return this.bonusDataMap.get(uuid);
    }
    public static void updatePokemon(Pokemon pokemon){
        pokemon.getStats().recalculateStats();
        UUID uuid = pokemon.getUUID();
        BonusData bonusData = pokeData.bonusDataMap.get(uuid);
        if (bonusData == null) return;
        Double speed = 1.0;
        Double attack = 1.0;
        Double sAttack = 1.0;
        Double defense = 1.0;
        Double sDefense = 1.0;
        Double hp = 1.0;
        if (bonusData.get() != null){
            for (Bonus bonus : bonusData.get()) {
                String subType = bonus.getType();
                Double subValue = Double.valueOf(bonus.getValue());
                if (subType.equals("Stat-Speed")) speed += subValue;
                if (subType.equals("Stat-Attack")) attack += subValue;
                if (subType.equals("Stat-SAttack")) sAttack += subValue;
                if (subType.equals("Stat-Defense")) defense += subValue;
                if (subType.equals("Stat-SDefense")) sDefense += subValue;
                if (subType.equals("Stat-HP")) hp += subValue;
            }
        }
        if (speed > 1.0)pokemon.getStats().setSpeed((int) (pokemon.getStats().getSpeed()*speed));
        if (attack > 1.0)pokemon.getStats().setAttack((int) (pokemon.getStats().getAttack()*attack));
        if (sAttack > 1.0)pokemon.getStats().setSpecialAttack((int) (pokemon.getStats().getSpecialAttack()*sAttack));
        if (defense > 1.0)pokemon.getStats().setDefense((int) (pokemon.getStats().getDefense()*defense));
        if (sDefense > 1.0)pokemon.getStats().setSpecialDefense((int) (pokemon.getStats().getSpecialDefense()*sDefense));
        if (hp > 1.0)pokemon.getStats().setHP((int) (pokemon.getStats().getHP()*hp));
        PlayerPartyStorage pps = StorageProxy.getParty(pokemon.getOwnerPlayer());
        pps.set(0,pokemon);
    }
    public static PokeData getInstance(){
        if (pokeData == null){
            String jsonString = plugin.getConfig().getString("data");
            if (jsonString == null||jsonString.equals("")){
                pokeData = new PokeData();
            }else{
                pokeData = plugin.gson.fromJson(jsonString, PokeData.class);
            }
        }
        for (Map.Entry<UUID, BonusData> dataEntry : pokeData.bonusDataMap.entrySet()) {
            if (dataEntry.getValue().get() == null||dataEntry.getValue().get().size() < 1){
                pokeData.bonusDataMap.remove(dataEntry.getKey());
            }
        }
        return pokeData;
    }
    public static void save(){
        if (pokeData == null)return;
        String jsonString = plugin.gson.toJson(pokeData);
        plugin.getConfig().set("data",jsonString);
        plugin.saveConfig();
    }
}
