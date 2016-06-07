package com.mygdx.game.logic.designPatterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.Animation;
import com.mygdx.game.logic.sprites.Enemy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Catarina Ramos on 02/06/2016.
 */

/**
 * Class responsible for generate enemies
 */
public class ZombieSpawner {
    private double width;
    private double height;
    private int spriteSize;
    private Animation idle;
    private Animation move;
    private Animation attack;

    /**
     * Zombie Spawner constructor
     * @param w  map width
     * @param h  map heigth
     */
    public ZombieSpawner(double w, double h){
        width = w;
        height = h;
        idle = new Animation(new TextureRegion(new Texture("idle_zombie.png")),17,0.1f);
        move = new Animation(new TextureRegion(new Texture("move_zombie.png")),17,0.10f);
       attack = new Animation (new TextureRegion(new Texture("attack_zombie.png")),9,0.1f);;
    };

    /**
     * Generata a specific number of enemies
     * @param number number of enemies that will be generated
     * @return  ArrayList with enemies
     */
    public ArrayList<Enemy> create(int number){
        Random rnd = new Random();
        Vector2 entrance = new Vector2(0,0);
        ArrayList<Enemy> wave = new ArrayList<Enemy>();
        for(int i = 0; i < number;i++){
            switch (rnd.nextInt(4)){
                case 0:{//N
                    entrance.set(rnd.nextInt((int)width-60),(float)height - 60);
                    break;
                }
                case 1:{//E
                    entrance.set((float)width - 60,rnd.nextInt((int)height-60));
                    break;
                }
                case 2:{//S
                    entrance.set(rnd.nextInt((int)width-60) ,60);
                    break;
                }
                case 3:{//W
                    entrance.set(60,rnd.nextInt((int)height-60));
                    break;
                }
            }
            Enemy e = new Enemy((int)entrance.x,(int)entrance.y);
            e.loadAnimation(idle,move,attack);
            wave.add(e);
        }
        return wave;
    }

}
