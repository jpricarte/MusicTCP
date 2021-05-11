package Music.musicPlayer;

import org.jfugue.pattern.Pattern;
import java.io.File;
import java.io.IOException;

public interface IMusicPlayer {
    public void setMusic(Pattern music);

    public void playMusic();

    public void saveMusic(File file) throws IOException, NullPointerException;

}
