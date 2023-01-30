package me.gsqlin.pokebonus.utils;

import java.util.ArrayList;
import java.util.List;

public class BonusData {
    private List<Bonus> data;
    public List<Bonus> get() {
        return data;
    }
    public void set(List<Bonus> data) {
        this.data = data;
    }
    public BonusData(){
    }
    public BonusData(List<Bonus> data){
        this.data = data;
    }
    public List<String> getTotalValue(String type){
        List<String> list = new ArrayList<>();
        if (get() != null){
            for (Bonus bonus : get()) {
                if (bonus.getType().equals(type)) list.add(bonus.getValue());
            }
        }
        return list;
    }
}
