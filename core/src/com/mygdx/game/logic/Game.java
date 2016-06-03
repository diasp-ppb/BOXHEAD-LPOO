package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;



public class Game {
    private int level;
    private int score;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Player player;
    private Rectangle map;
    private Texture bullet_text;

    public Game(int map_width, int map_height) {
        map = new Rectangle(0, 0, map_width, map_height);
        player = new Player();
        bullet_text = new Texture("play.png");
        level = 1;
        score = 0;
    }

    public int getLevel() {
        return level;
    }

    public final Player getPlayer() {
        return player;
    }

    public Vector2 movePlayer(float x, float y) {

        double x_init = player.getX()  + x * player.velocity;
        double y_init = player.getY()  + y * player.velocity;
        double x_fin = player.getX() + player.sprite.getWidth() + x * player.velocity;
        double y_fin = player.getY() + player.sprite.getHeight() + y * player.velocity;

        if(x != 0 || y != 0)
            player.moveAnimation();

        Vector2 res = new Vector2(0,0);
        if(x_init >= 0 && x_fin <= map.getWidth()){
            player.addPosition(x*player.velocity, 0);
            res.x = (float)(x*player.velocity);
            player.setDirection(new Vector2(x + player.getDirection().x,player.getDirection().y).nor());
        }
        if(y_init >= 0 && y_fin <= map.getHeight()){
            player.addPosition(0, y*player.velocity);
            res.y = (float)(y*player.velocity);
            player.setDirection(new Vector2(player.getDirection().x,player.getDirection().y+y).nor());
        }
        Gdx.app.log(player.getDirection().toString() + " ","");

            return res;
    }

    public void bulletsEnemiesColision() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).getBoundingRectangle().overlaps(enemies.get(j).sprite.getBoundingRectangle())) {
                    enemies.get(j).damageLife(bullets.get(i).getDamage());
                    bullets.remove(i);
                    i--;
                    if (enemies.get(j).isDead()) {
                        enemies.get(j).dispose();
                        enemies.remove(j);
                        j--;
                    }
                }
            }
        }
    }

    public void playerEnemiesColision() {
        for (int i = 0; i < enemies.size(); i++) {
            if (player.sprite.getBoundingRectangle().overlaps(enemies.get(i).sprite.getBoundingRectangle())) {
                player.damageLife(enemies.get(i).getDamage());
                if (player.isDead()){
                    player.dispose();
                    gameOver();
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(batch,bullets.get(i).getWidth(),bullets.get(i).getHeight());
        }
        for (int j = 0; j < enemies.size(); j++) {
            if(enemies.get(j).isVisible()) {
                enemies.get(j).draw(batch, enemies.get(j).getWidth(), enemies.get(j).getHeight());//TMMP !!!!!!!!
                enemies.get(j).draw(batch);
            }
            else
                Gdx.app.log("nao é visivel","not visible");
        }
        player.draw(batch, player.getWidth(), player.getHeight());
        player.draw(batch);
        batch.end();
    }

    public void shoot() {
        Bullet bullet = new Bullet(player.getDirection(), 10, bullet_text); //dir, vel , text
        bullet.setPosition(player.sprite.getX() + player.sprite.getWidth() / 2 - bullet.getWidth() / 2, player.sprite.getY() + player.sprite.getHeight() / 2 - bullet.getHeight() / 2);
        bullets.add(bullet);
        player.attackAnimation();
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void update(float dt) {
        player.update(dt);
        bulletsEnemiesColision();
        playerEnemiesColision();
        for (int i = 0; i < bullets.size(); i++) {
            if (!map.overlaps(bullets.get(i).getBoundingRectangle()) || bullets.get(i).outOfRange()) {
                bullets.remove(i);
                i--;
            }
            else
                bullets.get(i).incPosition();
        }
        for(Enemy enemy: enemies)
        {
            enemy.update(dt);
        }
    }

    public void gameOver() {
        Gdx.app.log("End Game", "go");
    }

    public void dispose(){
        bullet_text.dispose();
    }

    public void playerAmmoCollision(){
        player.setWeaponBehavior('r');
        player.rechargeWeapons(level);
    }

    public void nextWeapon(){
        int inUse = player.getInUse();
        int size = player.getBag().size();

        if(inUse == size-1)
            player.setInUse(0);
        else
            player.setInUse(inUse+1);
    }

    public void reloadWeapon(){
        player.reloadAnimation();
    }

    public void loadVisibleObjects(float x, float y, float width, float height){
        Rectangle visibleRegion = new Rectangle(x,y,width,height);
        for (int i = 0;i < enemies.size();i++){
            if(visibleRegion.overlaps(enemies.get(i).sprite.getBoundingRectangle())){
                enemies.get(i).setVisible(true);
            }
            else
                enemies.get(i).setVisible(false);
        }
    }


}
