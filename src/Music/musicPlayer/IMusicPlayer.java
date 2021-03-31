package Music.musicPlayer;

import Music.music.MusicJFugue;

public interface IMusicPlayer {
    public void setMusic(MusicJFugue music);

    public void playMusic();

    public void saveMusic(String filename);

}
