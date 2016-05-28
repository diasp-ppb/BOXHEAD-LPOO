package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Catarina Ramos on 12/05/2016.
 * NOTES: Para ser usado como stack
 */
public abstract class State {
    protected OrthographicCamera cam;   //camera
    protected Vector3 mouse;            //mouse position
    protected GameStateManager manager;
    protected Texture background;
    protected Viewport viewp;
    protected Stage stage;

    public State(GameStateManager manager){
        this.manager = manager;
        mouse = new Vector3();
        cam = new OrthographicCamera();
    }

    public State(GameStateManager manager, String bg){
        this.manager = manager;
        mouse = new Vector3();
        background = new Texture(bg);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, background.getWidth(), background.getHeight());
        viewp = new FillViewport(cam.viewportWidth,cam.viewportHeight);

        stage = new Stage(viewp);
        stage.clear();
        Gdx.input.setInputProcessor(stage);
    }


    public abstract void handleInput();

    public abstract void update(float delta_time);

    public abstract void render(SpriteBatch batch); //sprite batch - container

    public abstract void dispose();
}
