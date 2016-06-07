package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Created by Madnar on 04/06/2016.
 */

/**
 * Class responsable for load and save  Highscore file
 */
public class Save {
    public static GameData gd;
    private static String FILENAME = "highscore.txt";

    /**
     * Saves GameData content into highscores.txt
     */
    public static void save () {
        FileHandle temp = Gdx.files.local(FILENAME);
      for(int i = 0; i < 5; i++)
      {
          if(i == 0)
          temp.writeString(gd.getNames()[i]+"\n",false);
          else
              temp.writeString(gd.getNames()[i]+"\n",true);
          temp.writeString(gd.getHighScores()[i]+"\n",true);
      }

    }

    /**
     * Load highscore.txt content.
     * If empty initialize GameData.
     */
    public static void load() {
        FileHandle temp = Gdx.files.local(FILENAME);


        String content  = temp.readString();

        String[] lines = content.split("\n");

        if(lines.length < 10)
        {
            gd.init();
            return;
        }
        GameData nova = new GameData();
        long scores[] = new long[5];
        String names[] = new String[5];
        for(int i = 0; i < 5; i++)
        {
            names[i] = lines[2*i];
            scores[i] = Long.parseLong(lines[i*2+1],10);
        }
        nova.setArrays(scores,names);

        gd = nova;
    }


    /**
     * Inicialize GameData
     */
    public static void init() {
        gd = new GameData();
        gd.init();
        FileHandle temp = Gdx.files.local(FILENAME);
        temp.writeString("",true);

    }

}
