package com.mygdx.game.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.gui.controllers.HUD;
import com.mygdx.game.logic.Enemy;
import com.mygdx.game.logic.Game;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class Play extends State {
    //mapa
    private Texture map;
    //game
    private Game game;
    //controllers
    private HUD hud;

    public Play(GameStateManager manager) {
        super(manager);
        map = new Texture("map.jpg");

        super.cam.setToOrtho(false, width / 2, height / 2);
        cam.position.set(map.getWidth()/2, map.getHeight()/2, 0);

        game = new Game(map.getWidth(),map.getHeight());
        Enemy e = new Enemy(10,10,map.getWidth() / 3,map.getHeight() /3);
       // Gdx.app.log(e.sprite.getBoundingRectangle().toString() + " "," " + e.getX() + " "+e.getY() + " "+e.getWidth() + " " + e.getHeight());
        game.addEnemy(e);
        game.getPlayer().setPosition(map.getWidth() / 2 - game.getPlayer().getWidth()/2, map.getHeight() / 2 - game.getPlayer().getHeight()/2);//- width/2 e height/2 --> MUDAR
        //é preciso fazer o resize da imagem caso contrario se a desenhar escalada as colisões não seram detetadas pela imagem desenhada
        //mas sim pelo rect já definido

        hud = new HUD();
    }

    @Override
    public void handleInput() {
        hud.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                manager.set(new com.mygdx.game.gui.states.Menu(manager));
                dispose();
            }
        });
        hud.getShootButton().clearListeners();  //to not accumulate events
        hud.getShootButton().addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.shoot();
            }
        });
        hud.getaButton().clearListeners();
        hud.getaButton().addListener((ClickListener) new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.nextWeapon();
            }
        });
        hud.getbButton().clearListeners();
        hud.getbButton().addListener((ClickListener) new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.reloadWeapon();
            }
        });

    }

    @Override
    public void update(float delta_time){
        super.update(delta_time);
        game.update(delta_time);
        joystyckMove();
        cam.update();
    }

    @Override
    public void render(SpriteBatch batch){
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw(map, 0, 0, map.getWidth(), map.getHeight());
        batch.end();

        game.draw(batch);
        hud.draw();
    }

    @Override
    public void dispose(){
        map.dispose();
        hud.dispose();
        game.dispose();
    }

    public void joystyckMove(){
        float perc_x = hud.getTouchpadInput().x; //percentage
        float perc_y = hud.getTouchpadInput().y; //percentage
        Vector2 res = game.movePlayer(perc_x,perc_y);       //efetivamente deslocado
        cam.position.x += res.x;
        cam.position.y += res.y;
    }
}


