package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Animation;


/**
 * Created by Catarina Ramos on 02/06/2016.
 */
public abstract class Character {
    protected boolean alive;
    public Sprite sprite;
    protected Vector2 direction;
    protected double velocity;
    protected final double size = 48;
    protected Array<Animation> animations;


    public Character(){
        this.alive = true;
        sprite = new Sprite();
        direction = new Vector2(0,0); //N - influencia a maneira como o rectangular é formatado em set bounds
        sprite.rotate(direction.angle());
        animations = new Array<Animation>();
    }

    public abstract void loadAnimations();

    public double getVelocity(){
        return velocity;
    }

    public double getX(){
        return sprite.getX();
    }

    public double getY(){
        return sprite.getY();
    }

    public double getCenterX(){return sprite.getX()+sprite.getWidth()/2;}

    public double getCenterY(){return sprite.getY()+sprite.getWidth()/2;}

    public void addPosition(double x,double y){
        sprite.setPosition((float)(x+getX()), (float)(y+getY()));
    }

    public void setPosition(double x, double y){
        sprite.setPosition((float)x,(float)y);
    }

    public Vector2 getDirection(){
        return direction;
    }

    public double getSize(){
        return size;
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

    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;
    }

    public void draw(SpriteBatch batch,double width, double height){
        sprite.setBounds(sprite.getX(),sprite.getY(),(float)width,(float)height);
        batch.draw(sprite.getTexture(),sprite.getX(),sprite.getY(),(float)getWidth(),(float)getHeight());
    }
}
