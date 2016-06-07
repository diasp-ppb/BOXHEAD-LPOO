package de.tomgrill.gdxtesting.examples;

/**
 * Created by Madnar on 06/06/2016.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.logic.Animation;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AnimationTest {

    @Test
    public void testAnimation()
    {
      Animation test = new Animation(new TextureRegion(new Texture("idle_player.png")),1,0.5f);
        assertEquals(0.5f,test.getFrameTime(),0);
        assertEquals(1,test.getFramesCount(),0);

        test.update(0.5f);

        test.update(0.5f);

        assertEquals(1,test.getAnimationCount(),0);


    }

}