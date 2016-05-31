package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Catarina Ramos on 29/05/2016.
 */
public class Bullet extends Sprite {
    private Vector2 direction;
    private int damage;
    private final int velocity = 20;
    private int range;

    public Bullet(Vector2 direction , int damage,Texture text){
        this.direction = direction;
        this.damage = damage;
        super.setTexture(text);
        range = 35; //temp
    }

    public final int getDamage(){
        return damage;
    }

    public final Vector2 getDirection(){
        return direction;
    }

    public void incPosition(){
        setPosition(getX() + velocity * direction.x, getY() + velocity * direction.y);
        range--;
    }

    public boolean outOfRange(){
        return (range <= 0);
    }
}
