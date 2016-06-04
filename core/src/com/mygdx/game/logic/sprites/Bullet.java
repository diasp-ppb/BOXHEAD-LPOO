package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Catarina Ramos on 29/05/2016.
 */
public class Bullet extends GameObject {
    private int durability;
    private final int velocity = 40;
    private int range;
    private Vector2 direction;

    public Bullet(Vector2 direction , int dur, Texture text, int spriteSize){
        super(spriteSize);
        this.direction = direction;
        durability =dur;
        sprite.setTexture(text);
        sprite.setBounds((float)getX(), (float)getY(), spriteSize, spriteSize);
        range = 15;
    }

    public boolean outOfRange(){
        return (range <= 0);
    }

    public void incPosition(){
        setPosition(direction.x*velocity + getX(),direction.y*velocity + getY());
        range--;
    }

    public void decDurability(){
        durability--;
    }

    public int getDurability(){
        return durability;
    }
}
