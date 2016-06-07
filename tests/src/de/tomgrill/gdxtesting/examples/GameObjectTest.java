package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */
/**
 * Created by Madnar on 06/06/2016.
 */


import com.mygdx.game.logic.sprites.GameObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.junit.runner.RunWith;



import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class GameObjectTest {

    @Test
    public  void GameObjectTest()
    {
        GameObject test = new GameObject(10,1,2);

        assertEquals(1,test.getX(),0);
        assertEquals(2,test.getY(),0);

        assertEquals(10,test.getSize(),0);

        assertEquals(0,test.getWidth(),0);
        assertEquals(0,test.getHeight(),0);

        assertEquals(1,test.getCenterX(),0);
        assertEquals(2,test.getCenterY(),0);


        assertTrue(test.isVisible());
        test.setVisible(false);
        assertFalse(test.isVisible());

        test.setPosition(0,0);

        assertEquals(0,test.getX(),0);
        assertEquals(0,test.getY(),0);

        test.addPosition(1,2);

        assertEquals(1,test.getX(),0);
        assertEquals(2,test.getY(),0);

    }

}
