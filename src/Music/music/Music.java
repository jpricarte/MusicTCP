package Music;

public abstract class Music {

    private final int MAX_VOLUME = 127;

    private int initialOctave;
    private int initialBPM;
    private int initialVolume;
    private int initialInstrument;

    public Music(int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {
        this.initialOctave = initialOctave;
        this.initialInstrument = (int) (initialInstrument * 0.20);
        this.initialBPM = initialBPM;
        this.initialVolume = initialVolume;
    }

}

