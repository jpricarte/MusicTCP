package Music.music;

import Music.Enums.InstructionEnum;
import Music.musicState.MusicState;
import Music.tokenizer.TextTokenizer;
import org.jfugue.pattern.Pattern;

import java.util.List;

public class Music {

    protected List<InstructionEnum> instructions;
    protected MusicState musicState;
    protected String musicText;

    public Music(int initialOctave, int initialVolume, int initialBPM, int initialInstrument) {
        musicState = new MusicState();

        musicState.setInstrument(initialInstrument);
        musicState.setBPM(initialBPM);
        musicState.setOctave(initialOctave);
        musicState.setVolume(initialVolume);

        musicText = "I"+musicState.getInstrument();
        musicText += " T"+musicState.getBPM();
        musicText += " :CON(7,"+musicState.getVolume()+")";
    }

    public Music() {
        musicState = new MusicState();
        musicText = "I"+musicState.getInstrument();
        musicText += " T"+musicState.getBPM();
        musicText += " :CON(7,"+musicState.getVolume()+")";
    }

    //TODO: Throw exception if music has no notes
    protected void tokenizeMusic(String rawText) {
        var textTokenizer = new TextTokenizer(rawText);
        instructions = textTokenizer.getTokens();
    }

    protected void convertTokensToMusic() {
        for ( InstructionEnum instruction : instructions)
            musicText += instruction.getTranslation(musicState);
    }

    public Pattern getMusicPatternFromText(String rawText) throws IllegalArgumentException{
        tokenizeMusic(rawText);
        convertTokensToMusic();
        return new Pattern(this.musicText);
    }



}

