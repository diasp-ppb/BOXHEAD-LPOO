package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.logic.Animation;

/**
 * Created by Madnar on 05/06/2016.
 */
public class Bomb extends Character {

    private int frame = 0;
    private float timer = 0;
    private int animationcicle = 0;

    public Bomb(int x, int y,Animation bomb) {
        super(x, y);
        loadAnimations(bomb);
    }

    @Override
    public void loadAnimations() {

    }


    public void loadAnimations(Animation bomb) {
        super.animations.add(bomb);
    }

    public void update(float dt) {
        timer += dt;
        if (timer > animations.get(0).getFrameTime()) {
            frame++;
            timer = 0;

        }
        if (frame >= animations.get(0).getFramesCount()) {
            frame = 0;
            animationcicle = 1;
        }
    }

    public int getAnimationcicle()
    {
        return animationcicle;
    }
    public void draw(SpriteBatch batch)
    {
        float scale  = 0.5f;
        TextureRegion temp = animations.get(0).getFrame(frame);
        float Rotation = MathUtils.atan2(direction.y,direction.x)* MathUtils.radiansToDegrees;

        batch.draw(temp,(float)(getWidth()/2  + getX() - temp.getRegionWidth()*0.5f),(float)(getHeight()/2  + getY() - temp.getRegionHeight()*0.5f),
                temp.getRegionWidth()*0.5f, temp.getRegionHeight()*0.5f,
                temp.getRegionWidth(), temp.getRegionHeight(),scale,scale,Rotation);

    }

}
