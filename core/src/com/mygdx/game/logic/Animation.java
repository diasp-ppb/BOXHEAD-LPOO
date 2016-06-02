package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by syram on 01-06-2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFramesTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    public double animationcicle;

    public Animation(TextureRegion region, int frameCount, float cycletime)
    {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;

        for(int i = 0 ; i < frameCount; i++)
        {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth,region.getRegionHeight()));

        }
        this.frameCount = frameCount;

        maxFramesTime = cycletime;

        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFramesTime) {
            frame++;
            currentFrameTime = 0;

        }
        if(frame >= frameCount)
        {
            frame = 0;
            animationcicle ++;
        }
    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }


    public double getAnimationCount () {return animationcicle;}
    public void resetAnimation(){
        frame = 0;
        animationcicle = 0;
        currentFrameTime = 0;
    }
}
