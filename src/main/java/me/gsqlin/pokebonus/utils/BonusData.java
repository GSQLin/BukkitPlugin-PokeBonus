package me.gsqlin.pokebonus.utils;

import java.util.ArrayList;
import java.util.List;

public class BonusData {
    public Bonus getMain() {
        return main;
    }

    public void setMain(Bonus main) {
        this.main = main;
    }

    public List<Bonus> getSub() {
        return sub;
    }

    public void setSub(List<Bonus> sub) {
        this.sub = sub;
    }

    private Bonus main;
    private List<Bonus> sub;

    public BonusData(){
    }

    public BonusData(Bonus main,List<Bonus> sub){
        this.main = main;
        this.sub = sub;
    }

    public <T> List<T> getTotalValue(String type,T valueT){
        List<T> list = new ArrayList<>();
        if (getMain() != null)if (getMain().getType().equals(type)) list.add((T) getMain().getValue());
        if (getSub() != null){
            for (Bonus bonus : getSub()) {
                if (bonus.getType().equals(type)) list.add((T) bonus.getValue());
            }
        }
        return list;
    }
}
