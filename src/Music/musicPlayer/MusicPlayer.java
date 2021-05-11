package Music.musicPlayer;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

public class MusicPlayer implements IMusicPlayer {

    private final Player player;
    private Pattern music;

    public MusicPlayer() {
        player = new Player();
    }

    public void setMusic(Pattern music) {
        this.music = music;
    }

    public void playMusic() throws NullPointerException {
        if (music == null) {
            throw new NullPointerException("Pattern music not set yet");
        }
        else {
            player.play(music);
        }
    }

    public void saveMusic(File file) throws IOException, NullPointerException {
        System.out.println(file);
        if (music == null) {
            throw new NullPointerException("Pattern music not set yet");
        }
        FileOutputStream outStream = new FileOutputStream(file);
        MidiFileManager.savePatternToMidi(music, outStream);
        outStream.close();
    }

    public Pattern getMusic(){
        return music;
    }

}
