package com.mygdx.game.logic;

import java.util.Vector;


public class Weapon{
    private int damage;

    public Weapon(){};
    public Weapon(int d){
        damage = d;
    }
    public void setDamage(int d){
        damage =d;
    }
    public int getDamage(){
        return damage;
    }

}
