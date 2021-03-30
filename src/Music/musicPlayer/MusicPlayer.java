package Music.musicPlayer;

import org.jfugue.player.Player;

import Music.music.MusicJFugue;

public class MusicPlayer {

    private Player player;
    private MusicJFugue music;

    public MusicPlayer() {
        player = new Player();
        music = new MusicJFugue(6, 14, 80, 32);
    }

    void setMusic(MusicJFugue music) {
        this.music = music;
    }

    void playMusic(){
        String m = music.getMusic();
        player.play(m);
    }             // toca a música

    void saveMusic(String filename){
    }     // salva a música
    
    

}
