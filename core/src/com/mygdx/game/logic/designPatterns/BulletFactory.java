package com.mygdx.game.logic.designPatterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.sprites.Bullet;

/**
 * Created by Catarina Ramos on 02/06/2016.
 */

/**
 * Class responsible for create bullets
 */
public class BulletFactory {
    private Texture bullet_text;

    /**
     * BulletFactory constructor
     * Loads bullet Texture
     */
    public BulletFactory(){
        bullet_text = new Texture("bullet.png");
    }

    /**
     * Cretate  a bullet in certain position with a direction
     * @param direction movement direction
     * @param durability number of enemies that bullet can kill
     * @param position start position
     * @return new Bullet
     */
    public Bullet create(Vector2 direction, int durability,Vector2 position){
        Bullet bullet = new Bullet(direction,durability,bullet_text);
        bullet.setPosition(position.x, position.y);
        return bullet;
    }

    /**
     * Release BulletFactory resources
     */
    public void dispose(){
        bullet_text.dispose();
    }
}
