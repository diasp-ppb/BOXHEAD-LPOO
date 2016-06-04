package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Animation;


/**
 * Created by Catarina Ramos on 02/06/2016.
 */
public abstract class Character extends GameObject {
    protected boolean alive;
    protected Vector2 direction;
    protected double velocity;
    protected Array<Animation> animations;


    public Character(int spriteSize){
        super(spriteSize);
        this.alive = true;
        direction = new Vector2(0,0); //N - influencia a maneira como o rectangular é formatado em set bounds
        sprite.rotate(direction.angle());
        animations = new Array<Animation>();
    }

    public abstract void loadAnimations();

    public double getVelocity(){
        return velocity;
    }

    public Vector2 getDirection(){
        return direction;
    }

    public void setDirection(Vector2 d){
        direction = d;
    }

    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;
    }

}
