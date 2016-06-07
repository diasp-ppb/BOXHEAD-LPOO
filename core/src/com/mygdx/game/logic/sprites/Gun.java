package com.mygdx.game.logic.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.logic.Animation;

public class Gun extends Weapon {

    public Gun(int a) {
        super(a,1);
        idle = new Animation(new TextureRegion(new Texture(Gdx.files.internal("idle_gun.png"))),1,0.30f);
        reload = new Animation(new TextureRegion(new Texture("reload_gun.png")),15,0.10f);
        current_anim = idle;
    }

    @Override
    public void recharge(int level) {
        ammo += 10 + level;
    }
}
