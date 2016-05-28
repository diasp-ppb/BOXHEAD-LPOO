package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Sprite{
    int damage;
    int life;

    public Enemy(int life, int damage){
        this.life = life;
        this.damage = damage;
    }
    public void setLife(int life){
        this.life = life;
    }
    public int getLife(){
        return life;
    }
    public void setDamage(int d){
        damage = d;
    }
    public int getDamage(){
        return damage;
    }
}
