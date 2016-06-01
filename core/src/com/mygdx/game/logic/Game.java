package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import javax.swing.text.Position;

/**
 * Created by Catarina Ramos on 29/05/2016.
 */
public class Game {
    private int level;
    private int score;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Player player;
    private Rectangle map;
    private World world;


    private Texture bullet_text;

    public Game(int map_width, int map_height, World world) {
        map = new Rectangle(0, 0, map_width, map_height);
        this.world = world;
        player = new Player(world);
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

    public void movePlayer(float x, float y) {
        player.setPosition(player.getX() + x, player.getY() + y);
       /* if(x != 0 || y != 0)
            player.setDirection(new Vector2(x/(Math.abs(x) + Math.abs(y)),y/(Math.abs(x) + Math.abs(y))));*/
    }

    public void bulletsEnemiesColision() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).getBoundingRectangle().contains(enemies.get(j).getBoundingRectangle())) {
                    enemies.get(j).decLife(bullets.get(i).getDamage());
                    bullets.get(i).dispose();
                    bullets.remove(i);
                    i--;
                    if (enemies.get(j).isDead()) {
                        enemies.get(i).dispose();
                        enemies.remove(j);
                        j--;
                    }
                }
            }
        }
    }

    public void playerEnemiesColision() {
        for (int i = 0; i < enemies.size(); i++) {
            if (player.getBoundingRectangle().contains(enemies.get(i).getBoundingRectangle())) {
                player.decLife(enemies.get(i).getDamage());
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
            batch.draw(bullet_text, bullets.get(i).getX()-5, bullets.get(i).getY()-5,10,10);
        }
        for (int j = 0; j < enemies.size(); j++) {
            batch.draw(enemies.get(j).getTexture(), enemies.get(j).getX() - 50, enemies.get(j).getY() - 50, 100, 100);
        }
        batch.draw(player.getTexture(), player.getX() - 50, player.getY() - 50, 100, 100);  //substituir por width e height
        batch.end();
    }

    public void shoot() {
        //ver qual é a arma
        //retirar ammo
        Bullet bullet = new Bullet(player.getDirection(), 10, null,world); //dir, vel , text
        bullet.setPosition(player.getX(), player.getY());    //+ metade da textura ALTERAR
        bullets.add(bullet);
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void update() {
        bulletsEnemiesColision();
        playerEnemiesColision();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).incPosition();
            if (!map.contains(bullets.get(i).getBoundingRectangle()) || bullets.get(i).outOfRange()) {
                bullets.get(i).dispose();
                bullets.remove(i);
                i--;
            }
        }
    }

    public void gameOver() {
        /*
        Fim do jogo
         */
    }

    public void dispose(){
        bullet_text.dispose();
    }

    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Gdx.app.log("beginContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Gdx.app.log("endContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }
}
