package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    private boolean visible;
    private int spriteSize;
    private Sprite sprite;

    public GameObject(int size,int x,int y){
        sprite = new Sprite();
        sprite.setPosition(x,y);
        visible = true;
        spriteSize = size;
    }

    public Sprite getSprite(){return sprite;}

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public float getCenterX(){return sprite.getX()+sprite.getWidth()/2;}

    public float getCenterY(){return sprite.getY()+sprite.getWidth()/2;}

    public void addPosition(float x,float y){
        sprite.setPosition(x+getX(), y+getY());
    }

    public Vector2 getPosition(){
        return new Vector2(getX(),getY());
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x,y);
    }

    public float getSize(){
        return spriteSize;
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
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
