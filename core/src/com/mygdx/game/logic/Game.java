package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.designPatterns.AmmoFactory;
import com.mygdx.game.logic.designPatterns.BulletFactory;
import com.mygdx.game.logic.designPatterns.ZombieSpawner;
import com.mygdx.game.logic.sprites.Bullet;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.GameObject;
import com.mygdx.game.logic.sprites.Player;
import com.mygdx.game.logic.sprites.Weapon;

import java.util.ArrayList;
import java.util.Random;


public class Game {
    private int level;
    private int score;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<GameObject> ammoBoxs;
    private com.mygdx.game.logic.sprites.Player player;
    private Rectangle map;
    private ZombieSpawner zombieSpawner;
    private BulletFactory bulletFactory;
    private AmmoFactory ammoFactory;
    private boolean pause;
    private int spriteSize;

    public Game(int map_width, int map_height,int spritesSize) {
        pause = false;
        level = 1;
        score = 0;
        map = new Rectangle(0, 0, map_width, map_height);

        spriteSize = spritesSize;

        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        ammoBoxs = new ArrayList<GameObject>();

        player = new Player(spritesSize);

        zombieSpawner = new ZombieSpawner(map.getWidth(),map.getHeight(),spritesSize);
        bulletFactory = new BulletFactory(spritesSize);
        ammoFactory = new AmmoFactory(map.getWidth(),map.getHeight(),spritesSize);
    }

    public void setPause(boolean p){
        pause = p;
    }

    public boolean isPause(){
        return pause;
    }

    public final Player getPlayer() {
        return player;
    }

    public Vector2 movePlayer(float x, float y) {

        double x_init = player.getX()  + x * player.getVelocity();
        double y_init = player.getY()  + y * player.getVelocity();
        double x_fin = player.getX() + player.sprite.getWidth() + x * player.getVelocity();
        double y_fin = player.getY() + player.sprite.getHeight() + y * player.getVelocity();

        if(x != 0 || y != 0)
            player.moveAnimation();

        Vector2 res = new Vector2(0,0);
        if(x_init >= 0 && x_fin <= map.getWidth()){
            player.addPosition(x*player.getVelocity(), 0);
            res.x = (float)(x*player.getVelocity());
            player.setDirection(new Vector2(x + player.getDirection().x,player.getDirection().y).nor());
        }
        if(y_init >= 0 && y_fin <= map.getHeight()){
            player.addPosition(0, y*player.getVelocity());
            res.y = (float)(y*player.getVelocity());
            player.setDirection(new Vector2(player.getDirection().x, player.getDirection().y + y).nor());
        }

            return res;
    }

    public void playerEnemiesColision(){
        for(int i = 0; i < enemies.size();i++) {
            boolean collidingPlayer = enemies.get(i).sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle());
            if (collidingPlayer) {
                player.die();
                gameOver();
            }
        }
    }

    public void bulletsEnemiesColision() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).sprite.getBoundingRectangle().overlaps(enemies.get(j).sprite.getBoundingRectangle())) {
                    enemies.get(j).die();
                    enemies.remove(j);
                    j--;
                    bullets.get(i).decDurability();
                    if(bullets.get(i).getDurability() == 0){
                        bullets.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public void playerBoxColision(){
        for(int i = 0; i < ammoBoxs.size();i++){
            if(ammoBoxs.get(i).sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())){
                player.setWeaponBehavior('r');
                player.rechargeWeapons(level);
                ammoBoxs.remove(i);
                i--;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        for (int b = 0; b < ammoBoxs.size();b++){
           ammoBoxs.get(b).draw(batch);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(batch);
        }
        for (int j = 0; j < enemies.size(); j++) {
            if(enemies.get(j).isVisible()) {
               enemies.get(j).draw(batch);
            }
        }

       player.draw(batch);
        batch.end();
    }

    public void shoot() {
        player.attackAnimation();
        Weapon w = player.getBag().get(player.getInUse());
        if(w.use()) {
            Bullet bullet = bulletFactory.create(player.getDirection(), w.getDurability(), new Vector2((float) player.getCenterX(), (float) player.getCenterY()));
            bullets.add(bullet);
        }
    }

    public void update(float dt) {
        if(enemies.size() == 0){    //new botWave
            enemies = zombieSpawner.create(10 + level);
            ammoBoxs = ammoFactory.create(3 + level/3);
            player.getBag().get(1).setDurability(2+level/7);
            level++;
        }
        moveEnemies();
        bulletsEnemiesColision();
        playerEnemiesColision();
        playerBoxColision();
        player.update(dt);
        for (int i = 0; i < bullets.size(); i++) {
            if (!map.overlaps(bullets.get(i).sprite.getBoundingRectangle()) || bullets.get(i).outOfRange()) {
                bullets.remove(i);
                i--;
            }
            else
                bullets.get(i).incPosition();
        }
        for(com.mygdx.game.logic.sprites.Enemy enemy: enemies)
        {
            enemy.update(dt);
        }
    }

    public void gameOver() {
        Gdx.app.log("End Game", "go");
    }

    public void dispose() {
       bulletFactory.dispose();
       ammoFactory.dispose();
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
                enemies.get(i).setTracking(true);
            }
            else
                enemies.get(i).setVisible(false);
        }
    }

    public void moveEnemies(){
        for(int i = 0; i < enemies.size(); i++) {

            if (enemies.get(i).isVisible() || enemies.get(i).isTracking()) {    //Tracks Player
                Vector2 playerDirection = new Vector2((int) (player.getCenterX() - enemies.get(i).getCenterX()), (int) (player.getCenterY() - enemies.get(i).getCenterY())).nor();
                if (playerDirection.x != 0 || playerDirection.y != 0)
                    enemies.get(i).setDirection(playerDirection);
                enemies.get(i).addPosition(playerDirection.x, playerDirection.y);

                if(Math.sqrt(Math.pow(player.getCenterX() - enemies.get(i).getX(),2) + Math.pow(player.getCenterY() - enemies.get(i).getCenterY(),2)) <= player.getWidth()*4){
                    enemies.get(i).attackAnimation();
                }
            }

             else{  //Random movement
                Random rnd = new Random();
                Vector2 newDirection = new Vector2((float)(rnd.nextDouble()/8 - 0.5/8 + enemies.get(i).getDirection().x), (float) (rnd.nextDouble() / 8 - 0.5/8 + enemies.get(i).getDirection().y)).nor();
                enemies.get(i).setDirection(newDirection);
                enemies.get(i).addPosition(newDirection.x, newDirection.y);
                if(!map.contains(enemies.get(i).sprite.getBoundingRectangle())) {
                    newDirection.set(-newDirection.x,-newDirection.y);
                    enemies.get(i).setDirection(newDirection);
                    enemies.get(i).addPosition(newDirection.x,newDirection.y);
                }
           }
            }
        }


}
