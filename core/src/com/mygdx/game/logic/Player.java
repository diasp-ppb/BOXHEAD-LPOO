package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Vector;

public class Player extends Sprite {
    private ArrayList<Item> bag;
    private int life;
    private int damage = 1; //init
   // private Item inUse;
    private Vector2 direction;
    private Texture text;

    public Player(){
        bag = new ArrayList<Item>();
        direction = new Vector2(0,1);
        life = 100;
        damage = 1;
        super.setPosition(0,0);
        text = new Texture("play.png");
        super.setTexture(text); //teste
    }

    public Player(int life,int x,int y){
        bag = new ArrayList<Item>();
        direction = new Vector2(0,0);
        this.life = life;
        damage = 1;
        super.setPosition(x,y);
        text = new Texture("play.png");
        super.setTexture(text); //teste
    }

   /* public void equipItem(int i){
        inUse = bag.get(i);
        if(inUse.getClass() == Weapon.class){
            damage = ((Weapon)inUse).getDamage();
        }
        else
            damage = 0;
    }*/

    public void setLife(int life){
        life = life;
    }

    public void decLife(int damage){
        life -= damage;
    }

    public int getLife(){
        return life;
    }

    public boolean addItem(Item i){
        if(!bag.contains(i))
        {
            bag.add(i);
            return true;
        }
        return false;
    }

    public final boolean isDead(){
        return (life <= 0);
    }

    public final Vector2 getDirection(){
        return direction;
    }

    public void setDirection(Vector2 d){
        direction = d;
    }


}
