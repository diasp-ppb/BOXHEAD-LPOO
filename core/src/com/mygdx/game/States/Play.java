package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
    protected Texture background;
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


    //game
    private Game game;
    //map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;


    public Play(GameStateManager manager) {
        super(manager);
        background = new Texture("GameSettings.jpg");
        super.width = background.getWidth();
        super.height = background.getHeight();

        super.cam.setToOrtho(false, width/2, height/2);
        viewp = new FillViewport(width,height);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1);
        cam.position.set(viewp.getWorldWidth()/2, viewp.getWorldHeight() /2,0);
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();


        stage = new Stage(viewp);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        //Game
        game = new Game();
        game.getPlayer().setSize(game.getPlayer().getWidth()/2,game.getPlayer().getHeight()/2);
        game.getPlayer().setPosition(width/4 - game.getPlayer().getWidth(),height/4 - game.getPlayer().getHeight());

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
        touchpad.setBounds(15, 15, 200, 200);
        stage.addActor(touchpad);


        /*
        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Joystick");
        style.down = skin.getDrawable("Joystickp");
        joystick = new ImageButton(style);
        joystick.setPosition(joystick.getWidth() / 4, joystick.getHeight() / 8);
        stage.addActor(joystick);*/

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Shoot");
        style.down = skin.getDrawable("Shootp");
        shootButton = new ImageButton(style);
        shootButton.setPosition(width - shootButton.getWidth() * 3 / 2, shootButton.getHeight() / 3);
        stage.addActor(shootButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("A");
        style.down = skin.getDrawable("Ap");
        aButton = new ImageButton(style);
        aButton.setPosition(width - aButton.getWidth() * 8 / 3, aButton.getHeight() / 3);
        stage.addActor(aButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("B");
        style.down = skin.getDrawable("Bp");
        bButton = new ImageButton(style);
        bButton.setPosition(width - bButton.getWidth() * 3 / 2, bButton.getHeight() * 3 / 2);
        stage.addActor(bButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Play");
        style.down = skin.getDrawable("Pause");
        playButton = new ImageButton(style);
        playButton.setPosition(width / 2 - playButton.getWidth() / 2, height - playButton.getHeight() * 4 / 3);
        stage.addActor(playButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Sound");
        style.down = skin.getDrawable("Mute");
        soundButton = new ImageButton(style);
        soundButton.setPosition(soundButton.getWidth() * 2/3, height - soundButton.getHeight() * 4 / 3);
        stage.addActor(soundButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Back");
        backButton = new ImageButton(style);
        backButton.setPosition(width - backButton.getWidth()*5/3, height - backButton.getHeight()* 4/3);
        stage.addActor(backButton);




    }

    @Override
    public void handleInput() {

       /** joystick.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                //do your stuff
                //it will work when finger is released..

            }
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                boolean touchdown=true;
                float deltax = x - (joystick.getX() / 2 + joystick.getWidth() / 2);
                float deltay = y - (joystick.getY() / 2 + joystick.getHeight() / 2);

                if (Math.abs(deltax) > Math.abs(deltay)) {
                    if (deltax > 0){
                        cam.translate(0.01f, 0);
                        game.movePlayer(0.01f, 0);
                    }
                    else{
                        cam.translate(-0.01f,0);
                        game.movePlayer(-0.01f, 0);
                    }

                } else if (Math.abs(deltax) < Math.abs(deltay)) {
                    if (deltay > 0){
                        cam.translate(0, 0.01f);
                        game.movePlayer(0, 0.01f);
                    }
                    else {
                        cam.translate(0, -0.01f);
                        game.movePlayer(0, -0.01f);
                    }
                }
                return false;
            }
        });*/
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
        world.step(1/60f,6,2);
        cam.update();
        renderer.setView(cam);
    }

    @Override
    public void render(SpriteBatch batch){
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        /*batch.begin();
        batch.draw(background, 0, 0);
        batch.end();*/

        //MOVES
        cam.translate(touchpad.getKnobPercentX()*5,touchpad.getKnobPercentY()*5);

        //
        renderer.render();


        //rederer B2D
        b2dr.render(world, cam.combined);

        game.draw(batch);
        stage.draw();
    }

    @Override
    public void dispose(){
        skin.dispose();
        ButtonsPack.dispose();
        background.dispose();
        stage.dispose();
    }
}
