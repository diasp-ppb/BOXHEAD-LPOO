package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.logic.Animation;

/**
 * Responsible for draw small explosion
 */
public class Bomb extends Animated {
    /**
     * Bomb Constructor
     * @param x Coordinate X of the map
     * @param y Coordinate Y of the map
     * @param bomb Animation that will be displayed
     */
    public Bomb(int x, int y,Animation bomb) {
        super(48,x,y);
        frame = 0;
        timer = 0;
        animationcicle = 0;
        loadAnimations(bomb);
    }

    /**
     * Load animations
     */
    @Override
    public void loadAnimations() {
    }

    /**
     * Add a animation to  animations array
     * @param bomb
     */
    public void loadAnimations(Animation bomb) {
        super.animations.add(bomb);
    }

    /**
     * Update animation
     * @param dt time between frames
     */
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

    /**
     * Draw explosion in a Spritebatch
     * @param batch
     */
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
