package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.logic.Animation;


public class Bomb extends Animated {
    public Bomb(int x, int y,Animation bomb) {
        super(48,x,y);
        frame = 0;
        timer = 0;
        animationcicle = 0;
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

    public void draw(SpriteBatch batch)
    {
        float scale  = 0.5f;
        TextureRegion temp = animations.get(0).getFrame(frame);
        //float Rotation = MathUtils.atan2(direction.y,direction.x)* MathUtils.radiansToDegrees;

        batch.draw(temp,(float)(getWidth()/2  + getX() - temp.getRegionWidth()*0.5f),(float)(getHeight()/2  + getY() - temp.getRegionHeight()*0.5f),
                temp.getRegionWidth()*0.5f, temp.getRegionHeight()*0.5f,
                temp.getRegionWidth(), temp.getRegionHeight(),scale,scale,0);

    }
}
