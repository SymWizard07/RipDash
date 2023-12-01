package edu.graffwhitley.ripdash.Music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer {

    private static Music activeMusic;

    public static void playMusic(String filePath) {
        activeMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));

        // Start playing the music
        activeMusic.play();
    }
}
