package Music.musicPlayer;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;


public class MusicPlayer {

    private final Player player;
    private Pattern music;

    public MusicPlayer() {
        player = new Player();
    }

    public void setMusic(Pattern music) {
        this.music = music;
    }

    public void playMusic(){
        player.play(music);
    }   // toca a música

    public void saveMusic(File file) throws IOException, NullPointerException {
        System.out.println(file);
        if (file == null) {
            throw new NullPointerException("saveMusic cannot accept null pointers as file param");
        }
        MidiFileManager.savePatternToMidi(music, file);
    }   // salva a música

}
