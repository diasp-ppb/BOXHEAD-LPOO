package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.Animation;

public class Enemy extends Character {

    private static final int IDLE = 0;
    private static final int MOVE = 1;
    private static final int ATTACK = 2;
    private boolean tracking;   //Track player

    public Enemy(int x,int y){
        super(x, y, 0.75f);
        setDirection(new Vector2(1, 0));
        setVisible(true);
        tracking = false;
        currentAnimation = 1;
        frame = 0;
        timer = 0;
        animationcicle = 0;
    }

    public final boolean isTracking(){return tracking;}

    public void setTracking(boolean t){tracking = t;}

    @Override
    public void loadAnimations() {

        super.animations.add( new Animation(new TextureRegion(new Texture("idle_zombie.png")),17,0.1f));
        super.animations.add( new Animation(new TextureRegion(new Texture("move_zombie.png")),17,0.10f));
        super.animations.add( new Animation (new TextureRegion(new Texture("attack_zombie.png")),9,0.1f));
    }

    public void loadAnimation(Animation idle, Animation move, Animation attack){
        super.animations.add(idle);
        super.animations.add(move);
        super.animations.add(attack);
    }

    public void idleAnimation()
    {
        frame  = 0;
        timer = 0;
        currentAnimation = 0;
    }

    public void moveAnimation()
    {
        frame  = 0;
        timer = 0;
        currentAnimation  = 1;
    }
    public void attackAnimation()
    {
        timer = 0;
        frame  = 0;
        currentAnimation = 2;
    }
    public void update(float dt)
    {
        timer += dt;
        if (timer > animations.get(currentAnimation).getFrameTime()) {
            frame++;
            timer = 0;

        }
        if(frame >= animations.get(currentAnimation).getFramesCount())
        {
            frame = 0;
            animationcicle = 1;
        }
        if(animationcicle == 1)
        {
            animationcicle = 0;
            currentAnimation = 0;
            frame = 0;
            timer = 0;
        }
    }

    @Override
    public void draw(SpriteBatch batch)
    {
        float scale  = 0.5f;
        TextureRegion temp = animations.get(currentAnimation).getFrame(frame);
        float Rotation = MathUtils.atan2(direction.y,direction.x)* MathUtils.radiansToDegrees;

        batch.draw(temp,getWidth()/2  + getX() - temp.getRegionWidth()*scale,getHeight()/2  + getY() - temp.getRegionHeight()*scale,
                temp.getRegionWidth()*scale, temp.getRegionHeight()*scale,
                temp.getRegionWidth(), temp.getRegionHeight(),scale,scale,Rotation);

    }
}
