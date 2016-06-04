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
public class ZombieSpawner {
    private double width;
    private double height;
    private int spriteSize;
    private Animation idle;
    private Animation move;
    private Animation attack;

    public ZombieSpawner(double w, double h,int spriteSize){
        width = w;
        height = h;
        this.spriteSize = spriteSize;
        idle = new Animation(new TextureRegion(new Texture("idle_zombie.png")),17,0.1f);
        move = new Animation(new TextureRegion(new Texture("move_zombie.png")),17,0.10f);
       attack = new Animation (new TextureRegion(new Texture("attack_zombie.png")),9,0.1f);;
    };

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
            Enemy e = new Enemy(entrance.x,entrance.y,spriteSize);
            e.loadAnimation(idle,move,attack);
            wave.add(e);
        }
        return wave;
    }
}
