package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


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
        try{
            FileHandle temp = Gdx.files.local("highscores.txt");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(temp.file()));
            out.writeObject(gd);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
    public static void load() {
        try {
            if(!saveFileExists()) {
                init();
                return;
            }
            FileHandle temp = Gdx.files.internal("highscores.txt");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(temp.file()));
            gd = (GameData) in.readObject();
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static boolean saveFileExists() {
        try{
            FileHandle temp = Gdx.files.internal("highscores.txt");
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
