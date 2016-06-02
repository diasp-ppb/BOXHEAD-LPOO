package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Weapon> bag;
    private int inUseIndex;

    private Animation walk;
    private Animation idle;
    private boolean move = false;
    private double AnimationclycleCount;

    public Player(){
        super(100,2);
        bag = new ArrayList<Weapon>();
        //bag add
        inUseIndex = 0;
        bag.add(new Gun(5));
        bag.add(new Rifle(5));



        life = 100;
        damage = 1;
        velocity = 5f;

        sprite.setPosition(0, 0);
        direction = new Vector2(0,1);
        sprite.setTexture(new Texture("play.png")); //teste

        sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
        loadAnimations();
    }

    @Override
    public void loadAnimations() {
        walk = new Animation(new TextureRegion(new Texture("walk.png")),20,0.10f);
        idle = new Animation(new TextureRegion(new Texture("idle_player.png")),1,0.5f);
    }

    public void nextWeapon(){
        if(inUseIndex+1 == bag.size())
            inUseIndex = 0;
        else
            inUseIndex++;
    }

    public void setLife(int life){
        life = life;
    }

    public void decLife(int damage){
        life -= damage;
    }

    public int getLife(){
        return life;
    }

    public final boolean isDead(){
        return (life <= 0);
    }

    public void draw(SpriteBatch batch) {
        float scale = 0.5f;
        TextureRegion legs = getFrame();


        float Rotation = MathUtils.atan2(direction.y,direction.x)* MathUtils.radiansToDegrees;;


        float legsOffsetX = 0;
        float legsOffsetY = 0;



        TextureRegion temp = bag.get(inUseIndex).getFrame();

        if (inUseIndex  == 0) {

            if(move)
            batch.draw(legs,(float)(getWidth()/2+ legsOffsetX + getX() - legs.getRegionWidth()*0.5f),(float)(getHeight()/2 +legsOffsetY + getY() - legs.getRegionHeight()*0.5f),
                    legs.getRegionWidth()*0.5f, legs.getRegionHeight()*0.5f,
                    legs.getRegionWidth(), legs.getRegionHeight(),scale,scale,Rotation);
            else
                batch.draw(idle.getFrame(),(float)(getWidth()/2+ legsOffsetX + getX() - legs.getRegionWidth()*0.5f),(float)(getHeight()/2 +legsOffsetY + getY() - legs.getRegionHeight()*0.5f),
                        legs.getRegionWidth()*0.5f, legs.getRegionHeight()*0.5f,
                        legs.getRegionWidth(), legs.getRegionHeight(),scale,scale,Rotation);
            batch.draw(temp,(float)( getWidth()/2+ getX() - temp.getRegionWidth() * 0.5f),(float)(getHeight()/2 + getY() - temp.getRegionHeight() * 0.5f),
                    temp.getRegionWidth() * 0.5f, temp.getRegionHeight() * 0.5f,
                    temp.getRegionWidth(), temp.getRegionHeight(), scale, scale, Rotation);
        }
        else if (inUseIndex == 1) {
            float cos = MathUtils.cosDeg(Rotation);
            float sin = MathUtils.sinDeg(Rotation);
            float offsetX = 20* cos;
            float offsetY = 20* sin;

            legsOffsetX = 10 * cos;
            legsOffsetY = 10* sin;
            if(move)
            batch.draw(legs,(float)(legsOffsetX +getWidth()/2+ getX() - legs.getRegionWidth()*0.5f),(float)(getHeight()/2 + getY() - legs.getRegionHeight()*0.5f),
                    legs.getRegionWidth()*0.5f, legs.getRegionHeight()*0.5f,
                    legs.getRegionWidth(), legs.getRegionHeight(),scale,scale,Rotation);
            else
                batch.draw(idle.getFrame(),(float)(legsOffsetX +getWidth()/2+  getX() - legs.getRegionWidth()*0.5f),(float)(getHeight()/2 +legsOffsetY + getY() - legs.getRegionHeight()*0.5f),
                        legs.getRegionWidth()*0.5f, legs.getRegionHeight()*0.5f,
                        legs.getRegionWidth(), legs.getRegionHeight(),scale,scale,Rotation);
            batch.draw(temp,(float)(offsetX +getWidth()/2+  getX() - temp.getRegionWidth() *0.5f), (float)(getHeight()/2 +offsetY + getY() - temp.getRegionHeight()  *0.5f),
                    temp.getRegionWidth()*0.5f,temp.getRegionHeight()*0.5f,
                    temp.getRegionWidth(), temp.getRegionHeight(),scale,scale,Rotation);
        }
    }


    public void update(float dt)
    {
        if(move)
        walk.update(dt);

        bag.get(inUseIndex).current_anim.update(dt);

        if(bag.get(inUseIndex).current_anim.getAnimationCount() >= 1)
        {
            bag.get(inUseIndex).current_anim.resetAnimation();
            idleAnimation();

        }
        if(walk.getAnimationCount() >= 1)
        {
            walk.resetAnimation();
            move = false;
        }
    }


    public TextureRegion getFrame()
    {
        return walk.getFrame();
    }



    public void setInUse(int u){
        inUseIndex = u;
    }


    public int getInUse(){
        return inUseIndex;
    }
    public final ArrayList<Weapon> getBag(){
        return bag;
    }

    public void setWeaponBehavior(char b){
        bag.get(inUseIndex).setAnimation(b);
    }

    public void rechargeWeapons(int level){


        for(int i = 0; i < bag.size(); i++){
            bag.get(i).recharge(level);
        }
    }
    public void attackAnimation()
    {
        bag.get(inUseIndex).setAnimation('a');
    }
    public void reloadAnimation()
    {
        bag.get(inUseIndex).setAnimation('r');
    }

    public void idleAnimation()
    {
        bag.get(inUseIndex).setAnimation('i');
    }
    public void moveAnimation()
    {
        move = true;
    }

}
