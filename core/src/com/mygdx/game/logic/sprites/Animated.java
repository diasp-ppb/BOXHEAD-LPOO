package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Animation;

/**
 * Created by Catarina Ramos on 05/06/2016.
 */
public abstract class Animated extends GameObject{
    protected Array<Animation> animations;
    protected int currentAnimation;
    protected int frame;
    protected float timer;
    protected double animationcicle;

    public Animated(int size, int x, int y){
        super(size,x,y);
        currentAnimation = 0;
        frame = 0;
        timer = 0;
        animationcicle = 0;
        animations = new Array<Animation>();
    }

    public abstract void loadAnimations();

    public abstract void update(float dt);

    public double getAnimationcicle()
    {
        return animationcicle;
    }


}
