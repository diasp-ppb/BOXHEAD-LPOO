package com.mygdx.game.logic.designPatterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.sprites.Bullet;

/**
 * Created by Catarina Ramos on 02/06/2016.
 */
public class BulletFactory {
    private Texture bullet_text;

    public BulletFactory(){
        bullet_text = new Texture("play.png");
    }

    public Bullet create(Vector2 direction, int durability,Vector2 position){
        Bullet bullet = new Bullet(direction,durability,bullet_text);
        bullet.setPosition(position.x, position.y);
        return bullet;
    }

    public void dispose(){
        bullet_text.dispose();
    }
}
