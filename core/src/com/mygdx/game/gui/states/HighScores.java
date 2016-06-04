package com.mygdx.game.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;

/**
 * Created by Madnar on 04/06/2016.
 */
public class HighScores extends State  {

    private long[] highScores;
    private String[] names;
    private BitmapFont font;
    private SoundManager  soundManager;
    public HighScores(GameStateManager manager, SoundManager soundManager) {
        super(manager);

        this.soundManager = soundManager;

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                Gdx.files.internal("chicagoexpress.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter config = new FreeTypeFontGenerator.FreeTypeFontParameter();
        config.size = 30;
        config.magFilter = Texture.TextureFilter.Nearest;
        config.minFilter = Texture.TextureFilter.Nearest;
        font = gen.generateFont(config);


        GameData teste = new GameData();
        teste.init();
        Save.gd = teste;

          Save.save();
          Save.load();
        highScores = Save.gd.getHighScores();
        names = Save.gd.getNames();

    }


    @Override
    public void handleInput() {
        if(Gdx.input.isTouched())
        {
            manager.set(new Menu(manager,soundManager));
            dispose();
        }
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        float GameWidth = Gdx.app.getGraphics().getHeight()/2;

        GlyphLayout glyphLayout = new GlyphLayout();

        String s;
        float w;



        s = "High Scores";

        glyphLayout.setText(font,s);

        w = glyphLayout.width;
        font.draw(batch, s, (GameWidth - w) / 2, 300);

        for(int i = 0; i < highScores.length; i++) {
            s = String.format(
                    "%2d. %7s %s",
                    i + 1,
                    highScores[i],
                    names[i]
            );
            glyphLayout.setText(font,s);
            w = glyphLayout.width;
            font.draw(batch, s, (GameWidth - w) / 2, 270 - 20 * i);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
