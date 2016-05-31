package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import javax.swing.text.Position;

/**
 * Created by Catarina Ramos on 29/05/2016.
 */
public class Game{
    private int level;
    private int score;
    private ArrayList<Enemy> enemies= new ArrayList<Enemy>();
    private ArrayList<Bullet> bullets= new ArrayList<Bullet>();
    private Player player = new Player();
    private Rectangle map;

    private Texture bullet_text;

    public Game(int map_width, int map_height){
        map = new Rectangle(0,0,map_width,map_height);
        bullet_text = new Texture("play.png");
        level = 1;
        score = 0;
    }

    public int getLevel(){
        return level;
    }

    public final Player getPlayer(){
        return player;
    }

    public void movePlayer(float x, float y){
        player.setPosition(player.getX() + x, player.getY() + y);
       /* if(x != 0 || y != 0)
            player.setDirection(new Vector2(x/(Math.abs(x) + Math.abs(y)),y/(Math.abs(x) + Math.abs(y))));*/
    }

    public void bulletsEnemiesColision(){
        for(int i = 0; i < bullets.size();i++){
            for(int j = 0; j < enemies.size();j++){
                if(bullets.get(i).getBoundingRectangle().contains(enemies.get(j).getBoundingRectangle())){
                    enemies.get(j).decLife(bullets.get(i).getDamage());
                    bullets.remove(i);
                    i --;
                    if(enemies.get(j).isDead()){
                        enemies.remove(j);
                        j--;
                    }
                }
            }
        }
    }

    public void playerEnemiesColision(){
        for(int i= 0; i < enemies.size();i++){
            if(player.getBoundingRectangle().contains(enemies.get(i).getBoundingRectangle())){
                player.decLife(enemies.get(i).getDamage());
                if(player.isDead())
                    gameOver();
            }
        }
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        for(int i = 0; i < bullets.size();i++){
            batch.draw(bullet_text,bullets.get(i).getX(),bullets.get(i).getY());
        }
        for(int j = 0; j < enemies.size();j++){
            batch.draw(enemies.get(j).getTexture(),enemies.get(j).getX(),enemies.get(j).getY(),100,100);
        }
        batch.draw(player.getTexture(), player.getX(), player.getY(), 100, 100);
        batch.end();
    }

    public void shoot(){
        //ver qual é a arma
        //retirar ammo
        Bullet bullet = new Bullet(player.getDirection(),10,bullet_text); //dir, vel , text
        bullet.setPosition(player.getX(), player.getY());    //+ metade da textura ALTERAR
        bullets.add(bullet);
    }

    public void update(){
        for(int i = 0;i < bullets.size();i++){
            bullets.get(i).incPosition();
            if(!map.contains(bullets.get(i).getBoundingRectangle()) || bullets.get(i).outOfRange()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    public void gameOver(){
        /*
        Fim do jogo
         */
    }

    public void dispose(){
        bullet_text.dispose();
    }



}
