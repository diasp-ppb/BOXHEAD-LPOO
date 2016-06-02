package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;


/**
 * Created by Catarina Ramos on 01/06/2016.
 */
public class Gun extends Weapon {

    public Gun(int d) {
        super(d);
        idle = new Animation(new TextureRegion(new Texture("idle_gun.png")),1,0.30f);
        reload = new Animation(new TextureRegion(new Texture("reload_gun.png")),15,0.10f);
        attack = new Animation(new TextureRegion(new Texture("attack_gun.png")),15,0.10f);

        current_anim = idle;
    }
}
