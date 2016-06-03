package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Character {



    private Array<Animation> animations;
    private static final int IDLE = 0;
    private static final int MOVE = 1;
    private static final int ATTACK = 2;
    private int currentAnimation = 1;
    private int frame = 0;
    private float timer = 0;
    private double animationcicle = 0;

    public Enemy(int life, int damage,double x,double y){
        super(life,damage);
        sprite.setPosition((float) x, (float) y);

        sprite.setTexture(new Texture("start.png")); //teste
        sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getTexture().getWidth(), sprite.getTexture().getHeight());

        loadAnimations();

    }

    @Override
    public void loadAnimations() {
        animations = new Array<Animation>();
        animations.add( new Animation(new TextureRegion(new Texture("idle_zombie.png")),17,0.1f));
        animations.add( new Animation(new TextureRegion(new Texture("move_zombie.png")),17,0.10f));
        animations.add( new Animation (new TextureRegion(new Texture("attack_zombie.png")),9,0.1f));
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
    public void draw(SpriteBatch batch)
    {
        TextureRegion temp = animations.get(currentAnimation).getFrame(frame);
        float Rotation = MathUtils.atan2(direction.y,direction.x)* MathUtils.radiansToDegrees;

        batch.draw(temp,(float)(getWidth()/2  + getX() - temp.getRegionWidth()*0.5f),(float)(getHeight()/2  + getY() - temp.getRegionHeight()*0.5f),
                temp.getRegionWidth()*0.5f, temp.getRegionHeight()*0.5f,
                temp.getRegionWidth(), temp.getRegionHeight(),0.5f,0.5f,Rotation);

    }
}
