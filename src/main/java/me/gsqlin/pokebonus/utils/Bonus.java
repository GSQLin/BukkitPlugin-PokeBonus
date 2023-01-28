package me.gsqlin.pokebonus.utils;

public class Bonus extends ParamParser {
    public Bonus(String params) {
        super(params);
    }

    public String getType(){
        return this.getValue("type");
    }

    public Object getValue(){
        return this.getValue("value");
    }
}
