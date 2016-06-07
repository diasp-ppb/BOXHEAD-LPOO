package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.logic.Animation;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.GameObjectFactory;
import com.mygdx.game.logic.sprites.Bomb;
import com.mygdx.game.logic.sprites.Bullet;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.GameObject;
import com.mygdx.game.logic.sprites.Player;



import java.util.ArrayList;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameTest {

    @Test
    public void testGame()
    {

        Game test = new Game(500,500);

        //Chech pause state
        test.setPause(true);
        assertTrue(test.isPause());


        //Check Player
        Player gamePlayer = test.getPlayer();

        assertEquals(0,gamePlayer.getInUse());

        //change weapon
        gamePlayer.setInUse(1);
        assertEquals(1,gamePlayer.getInUse());


        //moveplayer
        double moveX = gamePlayer.getVelocity()*0.5f + gamePlayer.getX();
        double moveY = gamePlayer.getVelocity()*0.5f + gamePlayer.getY();
        test.movePlayer(0.5f,0.5f);

        gamePlayer = test.getPlayer();

        assertEquals(moveX,gamePlayer.getX(),1);
        assertEquals(moveY,gamePlayer.getY(),1);

        //test player shoot
        int ammo = gamePlayer.getAmmo();

        test.shoot();

        gamePlayer= test.getPlayer();

        assertEquals(ammo -1 , gamePlayer.getAmmo());


        //test weapon change
        test.nextWeapon();

        gamePlayer = test.getPlayer();
        assertEquals(0,gamePlayer.getInUse());

        test.nextWeapon();

        gamePlayer = test.getPlayer();
        assertEquals(1,gamePlayer.getInUse());


        // test spawn time

        test.update(0.5f);

        assertEquals(1,test.getLevel());

    }

    public void testFactories(){
        // Zombie Spwaner

        GameObjectFactory zombie = new GameObjectFactory(500,500);

        ArrayList<Enemy> zombies =  zombie.create(10);

        assertEquals(10, zombies.size());


        // Bullet factorie
        BulletFactory bullets = new BulletFactory();

        Bullet test = bullets.create(new Vector2(0,1),1,new Vector2(0,1));

        assertEquals(1,test.getDurability());


        //AmmoFacorie

        AmmoFactory ammos = new AmmoFactory(500,500);

        ArrayList<GameObject> temp  = ammos.create(12);

        assertEquals(12,temp.size());

    }


    public void testBomb()
    {
        Animation bomb = new Animation(new TextureRegion(new Texture("explosion.png")),13,0.05f);
        Bomb test = new Bomb(10,10,bomb);

        assertEquals(10,test.getX(),0);
        assertEquals(10,test.getY(),0);


    }
}