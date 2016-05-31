package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.logic.Game;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class Play extends State {
    //botões
    private ImageButton joystick;
    private ImageButton aButton;
    private ImageButton bButton;
    private ImageButton shootButton;
    private ImageButton playButton;
    private ImageButton soundButton;
    private ImageButton backButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Viewport viewp;
    private Stage stage;



    //touchpad
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;

    //mapa
    private Texture map;

    //game
    private Game game;


    public Play(GameStateManager manager) {
        super(manager);
        map = new Texture("Mapa2.png");

        super.cam.setToOrtho(false, map.getWidth()/4,map.getHeight()/4 * height/(map.getHeight()/4));
        viewp = new FillViewport(width,height);
        cam.position.set(map.getWidth()/2, map.getHeight()/2, 0);

        stage = new Stage(viewp);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        //Game
        game = new Game(map.getWidth(),map.getHeight());
        game.getPlayer().setSize(game.getPlayer().getWidth()/2,game.getPlayer().getHeight()/2);
        game.getPlayer().setPosition(map.getWidth()/2, map.getHeight()/2);

        //buttons
        ButtonsPack = new TextureAtlas("PlayButtons.atlas");        //pack de botões
        skin = new Skin();                                      //skin de botão
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = skin.getDrawable("Joystick");
        touchKnob = skin.getDrawable("Joystickp");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(30, 30,height/3, height/3);
        stage.addActor(touchpad);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Shoot");
        style.down = skin.getDrawable("Shootp");
        shootButton = new ImageButton(style);
        shootButton.setWidth(height/5);
        shootButton.setHeight(height / 5);
        shootButton.setPosition(viewp.getScreenWidth() - shootButton.getWidth() -15, 15);
        stage.addActor(shootButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("A");
        style.down = skin.getDrawable("Ap");
        aButton = new ImageButton(style);
        aButton.setWidth(height/5);
        aButton.setHeight(height / 5);
        aButton.setPosition(viewp.getScreenWidth() - aButton.getWidth() * 2 - 30, 15);
        stage.addActor(aButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("B");
        style.down = skin.getDrawable("Bp");
        bButton = new ImageButton(style);
        bButton.setWidth(height/5);
        bButton.setHeight(height / 5);
        bButton.setPosition(viewp.getScreenWidth() - bButton.getWidth() - 15, bButton.getHeight() + 30);
        stage.addActor(bButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Play");
        style.down = skin.getDrawable("Pause");
        playButton = new ImageButton(style);
        playButton.setWidth(height/8);
        playButton.setHeight(height / 8);
        playButton.setPosition(viewp.getScreenWidth() / 2 - playButton.getWidth() / 2, viewp.getScreenHeight() - playButton.getHeight() * 4 / 3);
        stage.addActor(playButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Sound");
        style.down = skin.getDrawable("Mute");
        soundButton = new ImageButton(style);
        soundButton.setWidth(height/8);
        soundButton.setHeight(height / 8);
        soundButton.setPosition(soundButton.getWidth() * 2 / 3, viewp.getScreenHeight() - soundButton.getHeight() * 4 / 3);
        stage.addActor(soundButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Back");
        backButton = new ImageButton(style);
        backButton.setWidth(height/8);
        backButton.setHeight(height / 8);
        backButton.setPosition(viewp.getScreenWidth() - backButton.getWidth()*5/3, viewp.getScreenHeight() - backButton.getHeight()* 4/3);
        stage.addActor(backButton);
    }

    @Override
    public void handleInput() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.set(new Menu(manager));
                dispose();
            }
        });
    }

    @Override
    public void update(float delta_time){
        super.update(delta_time);
      //  world.step(1/60f,6,2);

        joystyckMove();



        cam.update();
       // renderer.setView(cam);
    }

    @Override
    public void render(SpriteBatch batch){
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw(map, 0, 0, map.getWidth(), map.getHeight());
        batch.end();

        game.draw(batch);
        stage.draw();
    }

    @Override
    public void dispose(){
        skin.dispose();
        ButtonsPack.dispose();
       // background.dispose();
        stage.dispose();
    }

    public void joystyckMove(){
        double x_init = cam.position.x - cam.viewportWidth/2 + touchpad.getKnobPercentX()*5f;
        double y_init = cam.position.y - cam.viewportHeight/2 + touchpad.getKnobPercentY()*5f;
        double x_fin = cam.position.x + cam.viewportWidth/2 + touchpad.getKnobPercentX()*5f;
        double y_fin = cam.position.y + cam.viewportHeight/2 + touchpad.getKnobPercentY()*5f;

        if(x_init >= 0 && x_fin <= map.getWidth()){
            game.movePlayer(touchpad.getKnobPercentX()*5f, 0);

            cam.position.x += touchpad.getKnobPercentX()*5f;
        }
        if(y_init >= 0 && y_fin <= map.getHeight()){
            game.movePlayer(0, touchpad.getKnobPercentY() * 5f);
            cam.position.y += touchpad.getKnobPercentY()*5f;
        }
    }
}


