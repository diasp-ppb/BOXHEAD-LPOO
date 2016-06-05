package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Catarina Ramos on 04/06/2016.
 */
public class GameObject {
    private boolean visible;
    public int spriteSize;
    public Sprite sprite;

    public GameObject(int size,int x,int y){
        sprite = new Sprite();
        sprite.setPosition(x,y);
        visible = true;
        spriteSize = size;
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

    public double getSize(){
        return spriteSize;
    }

    public double getWidth(){
        return sprite.getWidth();
    }

    public double getHeight(){
        return sprite.getHeight();
    }

    public final boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean v){
        visible = v;
    }

    public void draw(SpriteBatch batch){
        sprite.setBounds(sprite.getX(),sprite.getY(),spriteSize,spriteSize);
        batch.draw(sprite.getTexture(),sprite.getX(),sprite.getY(),spriteSize,spriteSize);
    }
}
