package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void Game(){
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
        player.setPosition(player.getX() + x,player.getY() + y);
    }

    public void bulletsEnemiesColision(){
        for(int i = 0; i < bullets.size();i++){
            for(int j = 0; j < enemies.size();j++){
                if(bullets.get(i).getBoundingRectangle().contains(enemies.get(j).getBoundingRectangle())){
                    enemies.get(j).decLife(bullets.get(i).getDamage());
                    bullets.remove(i);
                    i --;
                    if(enemies.get(j).isDead())
                        enemies.remove(j);
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
            batch.draw(bullets.get(i).getTexture(),bullets.get(i).getX(),bullets.get(i).getY());
        }
        for(int j = 0; j < enemies.size();j++){
            batch.draw(enemies.get(j).getTexture(),enemies.get(j).getX(),enemies.get(j).getY());
        }
        batch.draw(player.getTexture(),player.getX(),player.getY(),player.getWidth()/2,player.getHeight()/2);
        batch.end();
    }

    public void gameOver(){
        /*
        Fim do jogo
         */
    }



}
