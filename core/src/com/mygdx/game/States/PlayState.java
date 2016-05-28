package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class PlayState extends State {
    private Texture start_button;
    private Texture background;

    public PlayState(GameStateManager manager) {
        super(manager,"GameSettings.jpg");
        start_button = new Texture("start.png");

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Gdx.app.log("Pressed","Pressed");
            manager.set(new MenuState(manager));
            dispose();
        }

    }

    @Override
    public void update(float delta_time) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        //for the camera necessary !!!!
        super.cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background,0, 0);
        batch.draw(start_button,background.getWidth()/2 - start_button.getWidth()/2, background.getHeight()/2 - start_button.getHeight()/2);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
