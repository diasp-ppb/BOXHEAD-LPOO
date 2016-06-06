package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;
import com.mygdx.game.logic.sprites.Bullet;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.Gun;
import com.mygdx.game.logic.sprites.Player;
import com.mygdx.game.logic.sprites.Rifle;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class BulletTest {

    @Test
    public void testBullet()
    {

        Texture bullet_text = new Texture("bullet.png");

        Bullet test = new Bullet(new Vector2(0,1),1,bullet_text);

        // test movement
        test.incPosition();

        assertEquals(0,test.getX(),0);
        assertEquals(40,test.getY(),0);

        //test durability
        assertEquals(1,test.getDurability());
        test.decDurability();
        assertEquals(0,test.getDurability());
    }

}