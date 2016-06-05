package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Animation;

public abstract class Character extends Animated {
    protected boolean alive;
    protected Vector2 direction;
    protected double velocity;

    public Character(int x,int y, double velocity){
        super(48,x,y);
        this.alive = true;
        this.velocity = velocity;
        direction = new Vector2(0,0); //N - influencia a maneira como o rectangular é formatado em set bounds
        sprite.rotate(direction.angle());
        sprite.setBounds(sprite.getX(), sprite.getY(), (float)getSize(), (float)getSize());
    }

    public double getVelocity(){
        return velocity;
    }

    public Vector2 getDirection(){
        return direction;
    }

    public void setDirection(Vector2 d){
        direction = d;
    }

    public void die(){
        alive = false;
    }

}
