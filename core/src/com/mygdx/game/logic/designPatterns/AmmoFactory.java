package com.mygdx.game.logic.designPatterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.sprites.GameObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Catarina Ramos on 04/06/2016.
 */
public class AmmoFactory {
    private Texture box_text;
    private double width;
    private double height;
    private int spriteSize;

    public AmmoFactory(double w, double h,int spriteSize){
        this.spriteSize = spriteSize;
        width = w;
        height = h;
        box_text = new Texture("play.png");
    }

    public ArrayList<GameObject> create(int number) {
        Random rnd = new Random();
        ArrayList<GameObject> boxs = new ArrayList<GameObject>();
        for (int i = 0; i < number; i++) {
            GameObject box = new GameObject(spriteSize);
            box.sprite.setTexture(box_text);
            box.setPosition(rnd.nextInt((int) width - 50),rnd.nextInt((int) height - 50));
            boxs.add(box);
        }
        return boxs;
    }

    public void dispose(){
        box_text.dispose();
    }
}
