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
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.Gun;
import com.mygdx.game.logic.sprites.Player;
import com.mygdx.game.logic.sprites.Rifle;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class EnemyTest {

    @Test
    public void testEnemy()
    {



        Enemy test = new Enemy(1,2);

        test.setTracking(true);
        assertEquals(true,test.isTracking());

        //AssertNumber of animatiom cicles
        assertEquals(0,test.getAnimationcicle(),0);




    }
}