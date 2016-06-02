package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Weapon> bag;
    private int inUseIndex;

    public Player(){
        super(100,2);
        bag = new ArrayList<Weapon>();
        //bag add
        inUseIndex = 0;

        life = 100;
        damage = 1;
        velocity = 5f;

        sprite.setPosition(0, 0);
        direction = new Vector2(0,1);
        sprite.setTexture(new Texture("play.png")); //teste
        sprite.setBounds(sprite.getX(), sprite.getY(), sprite.getTexture().getWidth(), sprite.getTexture().getHeight());

    }

    @Override
    public void loadAnimations() {
        //carregra animações
    }

    public void nextWeapon(){
        if(inUseIndex+1 == bag.size())
            inUseIndex = 0;
        else
            inUseIndex++;
    }


}
