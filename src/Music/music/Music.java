package Music.music;

import Music.Enums.InstructionEnum;
import Music.musicState.MusicState;
import Music.tokenizer.TextTokenizer;
import org.jfugue.pattern.Pattern;

import java.util.List;

public class Music {

    private List<InstructionEnum> instructions;
    private MusicState musicState;
    private String musicText;

    public Music(int initialOctave, int initialVolume, int intialBPM, int initialInstrument) {
        musicState = new MusicState();
        musicState.setInstrumentConstraints(0,127,65);
        musicText = "I"+musicState.getInstrument();
        musicText = " T"+musicState.getBPM();
        musicText = " CON(7,"+musicState.getVolume()+")";
    }

    public Music() {
        musicState = new MusicState();
        musicState.setInstrumentConstraints(0,127,65);
        musicText = "I"+musicState.getInstrument();
        musicText = " T"+musicState.getBPM();
        musicText = " CON(7,"+musicState.getVolume()+")";
    }

    private void tokenizeMusic(String rawText) {
        var textTokenizer = new TextTokenizer(rawText);
        instructions = textTokenizer.getTokens();
    }

    private void convertTokensToMusic() {
        for ( InstructionEnum instruction : instructions)
            musicText += instruction.getTranslation(musicState);
    }

    public Pattern getMusicPatternFromText(String rawText) {
        tokenizeMusic(rawText);
        convertTokensToMusic();
        return new Pattern(this.musicText);
    }

}

