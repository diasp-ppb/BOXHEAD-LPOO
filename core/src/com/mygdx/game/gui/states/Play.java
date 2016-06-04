package com.mygdx.game.gui.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.gui.controllers.HUD;
import com.mygdx.game.logic.Game;

/**
 * Created by Catarina Ramos on 12/05/2016.
 */
public class Play extends State {

    private Texture map;
    private Game game;
    private HUD hud;
    private SoundManager soundManager;

    public Play(GameStateManager manager, final SoundManager soundManager) {



        super(manager);

        this.soundManager = soundManager;
        this.soundManager.StopMusic();
        map = new Texture("map.jpg");

        super.cam.setToOrtho(false, width / 2, height / 2);
        cam.position.set(map.getWidth()/2, map.getHeight()/2, 0);

        game = new Game(map.getWidth(),map.getHeight(),width/20);
        game.getPlayer().setPosition(map.getWidth() / 2 - game.getPlayer().getWidth() / 2, map.getHeight() / 2 - game.getPlayer().getHeight() / 2);//- width/2 e height/2 --> MUDAR


        hud = new HUD();

        hud.getShootButton().addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayShootRifle();
                game.shoot();
            }
        });

        hud.getaButton().addListener((ClickListener) new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayClick();
                game.nextWeapon();
            }
        });

        hud.getbButton().addListener((ClickListener) new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayClick();
                game.reloadWeapon();
            }
        });

        hud.getPlayButton().addListener((ClickListener) new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayClick();
                if (game.isPause()){
                    hud.setPlay();
                    game.setPause(false);
                }
                else{
                    hud.setPause();
                    game.setPause(true);
                }
            }
        });
        hud.getSoundButton().addListener((ClickListener) new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayClick();
                if(soundManager.getPlayStatus())
                soundManager.Mute();
                else
                {
                    soundManager.Play();
                    soundManager.PlayMusic();
                }
            }
        });
    }

    @Override
    public void handleInput() {
        hud.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                soundManager.PlayClick();
                manager.set(new com.mygdx.game.gui.states.Menu(manager,soundManager));
                dispose();
            }
        });
    }

    @Override
    public void update(float delta_time){
        super.update(delta_time);
        if(!game.isPause()) {
            game.loadVisibleObjects(cam.position.x - cam.viewportWidth / 2, cam.position.y - cam.viewportHeight / 2, cam.viewportWidth, cam.viewportHeight);
            game.update(delta_time);
            joystyckMove();
        }
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


