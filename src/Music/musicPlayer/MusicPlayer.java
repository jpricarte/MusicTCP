package Music.musicPlayer;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;


public class MusicPlayer {

    private Player player;
    private Pattern music;

    public MusicPlayer() {
        player = new Player();
    }

    public void setMusic(Pattern music) {
        this.music = music;
    }

    public void playMusic(){
        player.play(music);
    }             // toca a música

    public void saveMusic(String filename) throws IOException {
        final File newFile = new File("./" + filename + ".midi");
        MidiFileManager.savePatternToMidi(music, newFile);
    }     // salva a música



}
