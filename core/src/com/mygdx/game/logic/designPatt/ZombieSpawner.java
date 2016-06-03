package com.mygdx.game.logic.designPatt;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.Enemy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Catarina Ramos on 02/06/2016.
 */
public class ZombieSpawner {
    private ArrayList<Enemy> enemiesWave;
    private double width;
    private double height;

    public ZombieSpawner(double w, double h){
        width = w;
        height = h;
        enemiesWave = new ArrayList<Enemy>();
    };

    public void create(int number, int life, int damage){
        Random rnd = new Random();
        Vector2 entrance = new Vector2(0,0);

        for(int i = 0; i < number;i++){
            switch (rnd.nextInt() % 4){
                case 0:{//N
                    entrance.set((float)width/2,(float)height - 50);
                    break;
                }
                case 1:{//E
                    entrance.set((float)width - 50,(float)height/2);
                    break;
                }
                case 2:{//S
                    entrance.set((float)width ,50);
                    break;
                }
                case 3:{//W
                    entrance.set(50,(float)height/2);
                    break;
                }
            }
            Enemy e = new Enemy(life,damage,entrance.x,entrance.y);
            enemiesWave.add(e);
        }
    }

    public void cleanWave(){
        enemiesWave.clear();
    }

    public ArrayList<Enemy> getEnemiesWave(){
        return enemiesWave;
    }
}
