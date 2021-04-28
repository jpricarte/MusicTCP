package Music.musicPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class MusicPlayerTest {

    final Pattern MUSIC_PATTERN = new Pattern("A B C I65 R D");
    final String NO_MUSIC_MESSAGE = "Pattern music not set yet";

    MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer();
    }

    @Test
    void testSetMusic() {
        musicPlayer.setMusic(MUSIC_PATTERN);
        assertEquals(MUSIC_PATTERN, musicPlayer.getMusic());
    }

    @Test
    void testPlayMusicEmpty() {
        Exception exception = assertThrows(NullPointerException.class, () -> musicPlayer.playMusic());
        String actualMessage = exception.getMessage();

        assertEquals(NO_MUSIC_MESSAGE,actualMessage);
    }

    @Test
    void testSaveMusicEmpty() {
        Exception exception = assertThrows(NullPointerException.class, () -> musicPlayer.saveMusic(new File("")));
        String actualMessage = exception.getMessage();

        assertEquals(NO_MUSIC_MESSAGE,actualMessage);
    }
}
