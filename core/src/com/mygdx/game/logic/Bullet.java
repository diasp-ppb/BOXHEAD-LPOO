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

    public Bullet(Vector2 direction , int damage, String texture){
        this.direction = direction;
        this.damage = damage;
        super.setTexture(new Texture(texture));
    }

    public final int getDamage(){
        return damage;
    }
}
