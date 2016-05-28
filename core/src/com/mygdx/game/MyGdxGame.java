package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class MyGdxGame extends ApplicationAdapter {
    //public float screen_width = Gdx.graphics.getWidth();
    //public float screen_height = Gdx.graphics.getHeight();
    public static float screen_width = 1280;
    public static float screen_height = 720;

	public static final String GameTitle = "BoxHead";
	private GameStateManager stateManager;
	private SpriteBatch batch; //1 per game
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1); //black
		stateManager.push(new MenuState(stateManager));
	}

	@Override
	public void render () {	//method that is executed in a loop
		//clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}

	/*SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("MenuBackground.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); //black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
