package Music.musicPlayer;

import org.jfugue.player.Player;

import Music.music.MusicJFugue;

public class MusicPlayer {

    private Player player;
    private MusicJFugue music;

    public MusicPlayer(int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {
        player = new Player();
        music = new MusicJFugue(initialOctave, initialInstrument, initialBPM, initialVolume);
    }

    public void setMusic(MusicJFugue music) {
        this.music = music;
    }

    public void playMusic(){
        String m = music.getMusic();
        player.play(m);
    }             // toca a música

    public void saveMusic(String filename){
    }     // salva a música
    
    

}
