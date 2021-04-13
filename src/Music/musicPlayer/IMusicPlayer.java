package Music.musicPlayer;

import org.jfugue.pattern.Pattern;

public interface IMusicPlayer {
    public void setMusic(Pattern music);

    public void playMusic();

    public void saveMusic(String filename);

}
