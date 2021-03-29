package Music.music;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MusicJFugueTest {

    MusicJFugue music;

    @BeforeAll
    void init() {
        music = new MusicJFugue(6,20,120,60);
    }
}
