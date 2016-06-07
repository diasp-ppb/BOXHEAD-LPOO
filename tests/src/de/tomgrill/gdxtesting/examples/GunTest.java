package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mygdx.game.logic.sprites.Gun;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GunTest {
    @Test
    public void testGun()
    {

         Gun test = new Gun(1);
          assertEquals(1,test.getDurability());
          assertEquals(true,test.use());
          assertEquals(false,test.use());

        test.recharge(1);

        assertEquals(true,test.use());


    }
}
