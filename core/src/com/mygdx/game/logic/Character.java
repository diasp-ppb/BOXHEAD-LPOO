package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

import javafx.animation.Animation;

/**
 * Created by Catarina Ramos on 02/06/2016.
 */
public abstract class Character {
    protected int life;
    protected int damage;
    public Sprite sprite;
    protected Vector2 direction;
    protected double velocity;

    protected Animation idle;
    protected Animation walk;
    protected Animation attack;

    public Character(int life, int damage){
        this.life = life;
        this.damage = damage;
        sprite = new Sprite();
        direction = new Vector2(0,0); //N - influencia a maneira como o rectangular é formatado em set bounds
        sprite.rotate(direction.angle());
    }

    public abstract void loadAnimations();

    public int getLife(){return life;}

    public void setLife(int life){this.life = life;}

    public void damageLife(int damage){life -= damage;}

    public int getDamage() {return damage;}

    public void setDamage(int damage){this.damage = damage;}

    public double getX(){
        return sprite.getX();
    }

    public double getY(){
        return sprite.getY();
    }

    public void addPosition(double x,double y){
        sprite.setPosition((float)(x+getX()), (float)(y+getY()));
    }

    public void setPosition(double x, double y){
        sprite.setPosition((float)x,(float)y);
    }

    public Vector2 getDirection(){
        return direction;
    }

    public void setDirection(Vector2 d){
        direction = d;
    }

    public double getWidth(){
        return sprite.getWidth();
    }

    public double getHeight(){
        return sprite.getHeight();
    }

    public boolean isDead(){
        return (life == 0);
    }

    public void draw(SpriteBatch batch,double width, double height){
        //rotate ?
        sprite.setBounds(sprite.getX(),sprite.getY(),(float)width,(float)height);
        batch.draw(sprite.getTexture(),sprite.getX(),sprite.getY(),(float)getWidth(),(float)getHeight());
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }
}
