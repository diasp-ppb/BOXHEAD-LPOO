package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.designPatterns.AmmoFactory;
import com.mygdx.game.logic.designPatterns.BulletFactory;
import com.mygdx.game.logic.designPatterns.ZombieSpawner;
import com.mygdx.game.logic.sprites.Bomb;
import com.mygdx.game.logic.sprites.Bullet;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.GameObject;
import com.mygdx.game.logic.sprites.Player;
import com.mygdx.game.logic.sprites.Weapon;
import java.util.ArrayList;
import java.util.Random;


public class Game {
    private int level;
    private long score;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<GameObject> ammoBoxs;
    private ArrayList<Bomb> bombs;
    private com.mygdx.game.logic.sprites.Player player;
    private Rectangle map;
    private ZombieSpawner zombieSpawner;
    private BulletFactory bulletFactory;
    private AmmoFactory ammoFactory;
    private boolean pause;
    private boolean endGame;
    private Animation bomb;

    public Game(int map_width, int map_height) {
        pause = false;
        level = 1;
        score = 0;
        map = new Rectangle(0, 0, map_width, map_height);

        enemies = new ArrayList<Enemy>();
        bullets = new ArrayList<Bullet>();
        ammoBoxs = new ArrayList<GameObject>();


        player = new Player();

        zombieSpawner = new ZombieSpawner(map.getWidth(),map.getHeight());
        bulletFactory = new BulletFactory();
        ammoFactory = new AmmoFactory(map.getWidth(),map.getHeight());
        endGame = false;

        bombs = new ArrayList<Bomb>();
        bomb =new Animation(new TextureRegion(new Texture("explosion.png")),13,0.05f);
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

                    bombs.add(new Bomb((int) enemies.get(j).getX(),(int)enemies.get(j).getY(),bomb));
                    enemies.get(j).die();
                    enemies.remove(j);
                    score += level;
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

    //Only draws the visible objects
    public void draw(SpriteBatch batch) {
        batch.begin();
        for (int b = 0; b < ammoBoxs.size();b++){
            if(ammoBoxs.get(b).isVisible())
                ammoBoxs.get(b).draw(batch);
        }
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i).isVisible())
                bullets.get(i).draw(batch);
        }
        for (int j = 0; j < enemies.size(); j++) {
            if(enemies.get(j).isVisible()) {
               enemies.get(j).draw(batch);
            }
        }
        for(Bomb bomb : bombs)
        {
            bomb.draw(batch);
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
        //new level ?
        if(enemies.size() == 0){
            enemies = zombieSpawner.create(10 + level);
            ammoBoxs = ammoFactory.create(3 + level/3);
            player.getBag().get(1).setDurability(2+level/7);
            level++;
        }

        //moves
        moveEnemies();
        moveBullets();

        //collisions
        bulletsEnemiesColision();
        playerEnemiesColision();
        playerBoxColision();

        //animation updates
        player.update(dt);
        for(Enemy enemy: enemies)
        {
            enemy.update(dt);
        }
        for(int i = 0; i < bombs.size(); i ++){
            if(bombs.get(i).getAnimationcicle() == 1)
            {
                bombs.remove(i);
                i--;
            }
            else
            bombs.get(i).update(dt);
        }
    }

    public void gameOver() {
        endGame = true;
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
                Gdx.app.log("in range", "zumbie");

            }
            else
                enemies.get(i).setVisible(false);
        }
        for(int j = 0; j < bullets.size();j++){
            if(visibleRegion.overlaps(bullets.get(j).sprite.getBoundingRectangle())){
                bullets.get(j).setVisible(true);
            }
            else
                bullets.get(j).setVisible(false);
        }
        for(int b = 0; b < ammoBoxs.size();b++){
            if(visibleRegion.overlaps(ammoBoxs.get(b).sprite.getBoundingRectangle())){
                ammoBoxs.get(b).setVisible(true);
            }
            else
                ammoBoxs.get(b).setVisible(false);
        }
    }

    public void moveEnemies(){
        for(int i = 0; i < enemies.size(); i++) {

            if (enemies.get(i).isVisible() || enemies.get(i).isTracking()) {    //Tracks Player
                Vector2 playerDirection = new Vector2((int) (player.getCenterX() - enemies.get(i).getCenterX()), (int) (player.getCenterY() - enemies.get(i).getCenterY())).nor();
                if (playerDirection.x != 0 || playerDirection.y != 0)
                    enemies.get(i).setDirection(playerDirection);
                enemies.get(i).addPosition(playerDirection.x*enemies.get(i).getVelocity(), playerDirection.y*enemies.get(i).getVelocity());

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
                    enemies.get(i).addPosition(newDirection.x*enemies.get(i).getVelocity(),newDirection.y*enemies.get(i).getVelocity());
                }
           }
            }
        }

    public void moveBullets(){
        for (int i = 0; i < bullets.size(); i++) {
            if (!map.overlaps(bullets.get(i).sprite.getBoundingRectangle())) {
                bullets.remove(i);
                i--;
            }
            else
                bullets.get(i).incPosition();
        }
    }

    public boolean getEndGame(){
        return  endGame;
    }

    public long getScore()
    {
        return score;
    }

}
