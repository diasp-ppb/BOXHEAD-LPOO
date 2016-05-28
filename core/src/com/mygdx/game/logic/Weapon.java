package com.mygdx.game.logic;

import java.util.Vector;


public class Weapon extends Item {
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

    @Override
    public void useItem(Vector<Integer> direction){
        //Colisões até intersectar algo
    };
}