package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Catarina Ramos on 01/06/2016.
 */
public class Rifle extends Weapon {

    public Rifle(int d) {
        super(d);
        idle = new Animation(new TextureRegion(new Texture("idle_rifle.png")),1,0.20f);
        reload = new Animation(new TextureRegion(new Texture("reload_rifle.png")),20,0.10f);
      //  attack = new Animation(new TextureRegion(new Texture("attack_rifle.png")),15,0.10f);

        current_anim = idle;
    }
}
