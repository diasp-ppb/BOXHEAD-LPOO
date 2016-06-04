package com.mygdx.game.gui.states;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Madnar on 04/06/2016.
 */
public class SoundManager {

    boolean play;
    private Sound shootRifle;
    private Sound switchSoud;
    private Music music;
    public SoundManager()
    {
        play = true;
        shootRifle = Gdx.audio.newSound(Gdx.files.internal("sound/shootRifle.mp3"));
        switchSoud =  Gdx.audio.newSound(Gdx.files.internal("sound/click3.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sound/Dexter_Britain_-_07_-_The_Time_To_Run.mp3"));
    }

    public void Play() {
        play = true;
        music.play();
    }
    public void Mute() {
        play = false;
        music.pause();
    }

    public void PlayShootRifle(){if(play) shootRifle.play();}
    public void PlayClick(){if(play) switchSoud.play();}
    public void PlayMusic() {
        music.setLooping(true);
        music.play();
    }
    public void StopMusic(){
        music.pause();
    }
    public boolean getPlayStatus(){
        return play;
    }


}
