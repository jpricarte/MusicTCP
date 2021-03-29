package Music.music;

import Music.conversor.TextConversor;

public class MusicJFugue extends Music {
    public String music;

    public MusicJFugue (String raw_text, int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {

        super(initialOctave, initialInstrument, initialBPM, initialVolume);

        TextConversor textConversor = new TextConversor();

        music = textConversor.convert(raw_text);
    }
    
    // For Tests, because I can't use Mockito 
    public MusicJFugue(String music) {
        super(5, 0, 120, 64);
        this.music = music;

    }

    public String toJFuguePlayableString() {
        return music;
    }
}
