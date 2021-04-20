package Music.music;

import Music.Enums.InstructionEnum;
import Music.tokenizer.TextTokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MusicTest extends Music {

    public final String BASE_STRING = "I0 T100 :CON(7,32)";
    public final String BASE_PERSONALIZED_STRING = "I65 T110 :CON(7,67)";
    public final String EXPECTED_MUSIC_STRING = BASE_STRING + " 69 71 :CON(7,64) 60";

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

    // TODO: Update InstructionEnum to use the new definition
    @Disabled
    @Test
    public void testTokenizeMusic() {
        music.tokenizeMusic("AB C");
        assertEquals(expected_instruct_list,music.instructions);
    }

    @Test
    public void testConvertTokensToMusic() {
        music.instructions = expected_instruct_list;
        music.convertTokensToMusic();
        assertEquals(EXPECTED_MUSIC_STRING, music.musicText);
    }


}
