package Music.music;

public abstract class Music {

    protected static final int MIN_VOLUME = 0;
    protected static final int MAX_VOLUME = 127;
    protected static final int MIN_OCTAVE = 1;
    protected static final int MAX_OCTAVE = 9;
    protected static final int OCTAVE_SIZE = 12;
    protected static final int MIN_BPM = 50;
    protected static final int MAX_BPM = 250;
    protected static final int BPM_STEP = 50;
    protected static final int NUM_OF_INSTRUMENTS = 128;
    protected static final String PAUSE = " R";


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

