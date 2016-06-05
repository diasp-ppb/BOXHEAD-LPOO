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
public class Save {
    public static GameData gd;

    public static void save () {
        FileHandle temp = Gdx.files.local("highscore.txt");
        if(!temp.exists())
            Gdx.app.log("File","dont exits");
        else
            Gdx.app.log("File","" +
                    " exits");


      for(int i = 0; i < 5; i++)
      {
          if(i == 0)
          temp.writeString(gd.getNames()[i]+"\n",false);
          else
              temp.writeString(gd.getNames()[i]+"\n",true);
          temp.writeString(gd.getHighScores()[i]+"\n",true);
      }

    }
    public static void load() {
        FileHandle temp = Gdx.files.local("highscore.txt");
        Gdx.app.log("READ",temp.readString());
        String teste  = temp.readString();
        String[] teste2 = teste.split("\n");
        Gdx.app.log("Files count",Integer.toString( teste2.length));
       /* for(int i = 0; i < 5; i++)
        {
            gd.addHighScore();
        }*/
    }

    public static boolean saveFileExists() {
        try{
            FileHandle temp = Gdx.files.local("highscores.json");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(temp.file()));
            out.close();
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public static void init() {
        gd = new GameData();
        gd.init();
        save();
    }

}
