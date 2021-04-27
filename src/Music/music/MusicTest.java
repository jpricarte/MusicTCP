package Music.music;

import Music.Enums.InstructionEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MusicTest extends Music {

    public final String BASE_STRING = "I0 T100 :CON(7,32)";
    public final String BASE_PERSONALIZED_STRING = "I65 T110 :CON(7,67)";
    public final String EXPECTED_MUSIC_STRING = BASE_STRING + " 69 71 :CON(7,64) 60";
    public final String SIMPLE_MUSIC_TEXT = "AB C";
    public final String ERROR_MESSAGE = "getMusicPatternFromText needs a non Empty string as param";

    public List<InstructionEnum> expected_instruct_list;
    public Music music;


    @BeforeEach
    public void init() {
        music = new Music();
        expected_instruct_list = new ArrayList<InstructionEnum>();
        expected_instruct_list.add(InstructionEnum.A);
        expected_instruct_list.add(InstructionEnum.B);
        expected_instruct_list.add(InstructionEnum.VOL_UP);
        expected_instruct_list.add(InstructionEnum.C);
    }

    @Test
    public void testInitialize() {
        assertEquals(BASE_STRING, music.musicText);
    }

    @Test
    public void testPersonalizedInitializate() {
        Music personalizedMusic = new Music(6,67,110,65);
        assertEquals(BASE_PERSONALIZED_STRING, personalizedMusic.musicText);
    }

    @Test
    public void testPersonalizedInitializateAboveMax() {
        Music personalizedMusic = new Music(10,128,256,128);
        assertEquals(BASE_STRING, personalizedMusic.musicText);
    }

    @Test
    public void testPersonalizedInitializateBellowMin() {
        Music personalizedMusic = new Music(-1,-1,-1,-1);
        assertEquals(BASE_STRING, personalizedMusic.musicText);
    }

    @Test
    public void testTokenizeMusic() {
        music.tokenizeMusic(SIMPLE_MUSIC_TEXT);
        assertEquals(expected_instruct_list,music.instructions);
    }

    @Test
    public void testTokenizeMusicEmpty() {
        music.tokenizeMusic("");
        assertTrue(music.instructions.isEmpty());
    }

    @Test
    public void testConvertTokensToMusic() {
        music.instructions = expected_instruct_list;
        music.convertTokensToMusic();
        assertEquals(EXPECTED_MUSIC_STRING, music.musicText);
    }

    @Test
    public void testGetMusicPatternFromText() {
        music.getMusicPatternFromText(SIMPLE_MUSIC_TEXT);
        assertEquals(EXPECTED_MUSIC_STRING, music.musicText);
    }

    @Test
    public void testGetMusicPatternFromTextEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> music.getMusicPatternFromText(""));

        String actualMessage = exception.getMessage();

        assertEquals(ERROR_MESSAGE,actualMessage);
    }

}
