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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.logic.Animation;
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;
import com.mygdx.game.logic.sprites.Bullet;
import com.mygdx.game.logic.sprites.Enemy;
import com.mygdx.game.logic.sprites.Gun;
import com.mygdx.game.logic.sprites.Player;
import com.mygdx.game.logic.sprites.Rifle;

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