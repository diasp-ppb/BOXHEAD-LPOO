package com.mygdx.game.logic;

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
public class Bullet extends Sprite {
    private int damage;
    private final int velocity = 40;
    private int range;
    private Vector2 direction;

    public Bullet(Vector2 direction , int damage,Texture text){
        this.damage = damage;
        this.direction = direction;
        super.setTexture(text);
        setBounds(getX(),getY(),30,30);
        range = 15; //temp
    }

    public final int getDamage(){
        return damage;
    }

    public boolean outOfRange(){
        return (range <= 0);
    }

    public void incPosition(){
        setPosition(direction.x*velocity + getX(),direction.y*velocity + getY());
        range--;
    }

    public void draw(SpriteBatch batch, double width, double height){
        setBounds((float) getX(), (float) getY(), (float) width, (float) height);
        batch.draw(getTexture(), getX(), getY(), (float) width, (float) height);
    }
}
