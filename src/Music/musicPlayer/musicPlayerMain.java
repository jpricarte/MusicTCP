package Music.musicPlayer;

import Music.music.Music;
import org.jfugue.pattern.Pattern;

import java.io.IOException;

public class musicPlayerMain {
    public static void main(String[] args) {
        Music music = new Music();
        Pattern musicPattern = music.getMusicPatternFromText(
                "DINOSSAURO PETECA -+BPMT ?LIGACAO CAFE COM BISCOITO 43A");

        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setMusic(musicPattern);

//        musicPlayer.playMusic();
        try {
            musicPlayer.saveMusic("dinossauro_peteca");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}