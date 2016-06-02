package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Character {

    public Enemy(int life, int damage,double x,double y){
        super(life,damage);
        sprite.setPosition((float) x, (float) y);

        sprite.setTexture(new Texture("start.png")); //teste
        sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
    }

    @Override
    public void loadAnimations() {
        /*
        juntar parte de animações
         */
    }
}
