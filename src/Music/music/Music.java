package Music.music;

public abstract class Music {

    protected final int MAX_VOLUME = 127;
    protected static final String PAUSE = " R";
    protected static final int OCTAVE_SIZE = 12;

    protected int initialOctave;
    protected int initialBPM;
    protected int initialVolume;
    protected int initialInstrument;

    public Music(int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {
        this.initialOctave = initialOctave;
        this.initialInstrument = initialInstrument;
        this.initialBPM = initialBPM;
        this.initialVolume = initialVolume;
    }

}

