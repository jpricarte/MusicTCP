package Music;

public class MusicJFugue extends Music {
    public String music;

    public MusicJFugue (String raw_text, int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {

        super(initialOctave, initialInstrument, initialBPM, initialVolume);

        TextConversor textConversor = new TextConversor();

        music = textConversor.convert(raw_text);
    }

    public String toJFuguePlayableString() {
        return music;
    }
}
