package Music.musicPlayer;

import Music.music.MusicJFugue;

public class musicPlayerMain {
    public static void main (String[] args) {
        MusicPlayer mp = new MusicPlayer(6, 0, 160, 127);
        MusicJFugue musicJFugue = new MusicJFugue(6, 0, 160, 127);
        musicJFugue.toJFuguePlayableString("DINOSSAURO PETECA -+BPMT ?LIGACAO CAFE COM BISCOITO 43A");
        mp.setMusic(musicJFugue);
        mp.playMusic();
    }
}