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
import com.mygdx.game.logic.sprites.Gun;
import com.mygdx.game.logic.sprites.Rifle;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class RifleTest {
    @Test
    public void testRifle()
    {

        Rifle test = new Rifle(1);
        assertEquals(2,test.getDurability());
        assertEquals(true,test.use());
        assertEquals(false,test.use());

        test.recharge(1);

        assertEquals(true,test.use());

        assertEquals(2,test.getDurability());

        test.setDurability(3);

        assertEquals(3,test.getDurability());


        assertNotNull(test.getFrame());
        test.setAnimation('r');
        assertNotNull(test.getFrame());
        test.setAnimation('i');
        assertNotNull(test.getFrame());
        test.setAnimation('x');
        assertNotNull(test.getFrame());


    }
}
