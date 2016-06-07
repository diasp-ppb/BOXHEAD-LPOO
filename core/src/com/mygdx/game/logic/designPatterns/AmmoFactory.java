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

/**
 * Class responsible for generate ammoBoxs
 */
public class AmmoFactory {
    private Texture box_text;
    private double width;
    private double height;
    private int spriteSize;

    /**
     * AmmoFactory constructor
     * @param w width of the map
     * @param h heigth of the map
     */
    public AmmoFactory(double w, double h){
        this.spriteSize = 48;
        width = w;
        height = h;
        box_text = new Texture("ammo.png");
    }

    /**
     * Create a ArrayList of n ammoBoxs
     * @param number number of ammoBox to be created
     * @return ArryaList with ammoBoxs
     */
    public ArrayList<GameObject> create(int number) {
        Random rnd = new Random();
        ArrayList<GameObject> boxs = new ArrayList<GameObject>();
        for (int i = 0; i < number; i++) {
            GameObject box = new GameObject(spriteSize,rnd.nextInt((int) width - 50),rnd.nextInt((int) height - 50));
            box.sprite.setTexture(box_text);
            boxs.add(box);
        }
        return boxs;
    }

    /**
     * Release AmmoFactory resources
     */
    public void dispose(){
        box_text.dispose();
    }
}
