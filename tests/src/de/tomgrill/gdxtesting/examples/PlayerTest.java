package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mygdx.game.logic.sprites.Player;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    @Test
    public void testPlayer()
    {
        Player test = new Player();
        assertEquals(0,test.getInUse());

        //change weapon
        test.setInUse(1);
        assertEquals(1,test.getInUse());

        //ammo in weapon 0;
        test.setInUse(0);
        assertEquals(15,test.getAmmo());


        //2 weapons on bag;
        assertEquals(2,test.getBag().size());


    }
}
