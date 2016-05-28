package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class MenuState extends State{
    private ImageButton playButton;
    private ImageButton highScoresButton;
    private ImageButton settingsButton;
    private ImageButton exitButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;


    public MenuState(GameStateManager manager) {
        super(manager, "MenuBackground.png");

        //buttons
        ButtonsPack = new TextureAtlas("MenuButtons.atlas");        //pack de botões
        skin = new Skin();                                      //skin de botão
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Play");       //estilo quando carregado
        style.down = skin.getDrawable("Play");
        playButton = new ImageButton(style);
        playButton.setPosition(cam.viewportWidth/5 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight()/2);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Scores");       //estilo quando carregado
        style.down = skin.getDrawable("Scores");
        highScoresButton = new ImageButton(style);
        highScoresButton.setPosition(2*cam.viewportWidth/5 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight()/2);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Settings");       //estilo quando carregado
        style.down = skin.getDrawable("Settings");
        settingsButton = new ImageButton(style);
        settingsButton.setPosition(3*cam.viewportWidth/5 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight()/2);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Exit");       //estilo quando carregado
        style.down = skin.getDrawable("Exit");
        exitButton = new ImageButton(style);
        exitButton.setPosition(4*cam.viewportWidth/5 - playButton.getWidth()/2, cam.viewportHeight/2 - playButton.getHeight()/2);


        stage.addActor(playButton);
        stage.addActor(highScoresButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);
    }

    @Override
    public void handleInput() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.set(new PlayState(manager));         //demora tempo porque está a carregar imagens e etc
                dispose();  //free memory
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.set(new SettingsState(manager));         //demora tempo porque está a carregar imagens e etc
                dispose();  //free memory
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();  //free memory
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void update(float delta_time) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        stage.draw();

    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        ButtonsPack.dispose();
        skin.dispose();
    }
}
