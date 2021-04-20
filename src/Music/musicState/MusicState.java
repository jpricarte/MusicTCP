package Music.musicState;

import java.util.Random;
import Music.Enums.NoteEnum;

public class MusicState {
    public static final int NO_NOTE = -1;
    protected static final int OCTAVE_SIZE = 12;
    public static final int DEFAULT_MIN_VOLUME = 0;
    public static final int DEFAULT_MAX_VOLUME = 127;
    public static final int DEFAULT_DEFAULT_VOLUME = 32;
    public static final int DEFAULT_MIN_OCTAVE = 1;
    public static final int DEFAULT_MAX_OCTAVE = 9;
    public static final int DEFAULT_DEFAULT_OCTAVE = 5;
    public static final int DEFAULT_MIN_BPM = 50;
    public static final int DEFAULT_MAX_BPM = 250;
    public static final int DEFAULT_DEFAULT_BPM = 100;
    public static final double DEFAULT_STEP_FACTOR_VOLUME = 2.0;
    public static final int DEFAULT_STEP_BPM = 50;
    public static final int DEFAULT_STEP_OCTAVE = 1;
    public static final int DEFAULT_CURRENT_INSTRUMENT = 0;


    private int default_volume;
    private int min_volume;
    private int max_volume;
    private double step_factor_volume;
    private int min_octave;
    private int max_octave;
    private int step_octave;
    private int default_octave;
    private int min_BPM;
    private int max_BPM;
    private int step_BPM;
    private int default_BPM;
    private int[] instrument_list;
    private int default_instrument;

    private int currentOctave;
    private int currentVolume;
    private int currentBPM;
    private int currentInstrument;
    private NoteEnum currentNote;

    {
        min_volume = DEFAULT_MIN_VOLUME;
        max_volume = DEFAULT_MAX_VOLUME;

        min_octave = DEFAULT_MIN_OCTAVE;
        max_octave = DEFAULT_MAX_OCTAVE;

        min_BPM = DEFAULT_MIN_BPM;
        max_BPM = DEFAULT_MAX_BPM;

        step_factor_volume = DEFAULT_STEP_FACTOR_VOLUME;
        step_BPM = DEFAULT_STEP_BPM;
        step_octave = DEFAULT_STEP_OCTAVE;

        currentNote = NoteEnum.NONE;
    }
    
    public MusicState(){
        default_volume = DEFAULT_DEFAULT_VOLUME;
        default_octave = DEFAULT_DEFAULT_OCTAVE;
        default_BPM = DEFAULT_DEFAULT_BPM;

        currentInstrument = DEFAULT_CURRENT_INSTRUMENT;
        currentOctave = default_octave;
        currentVolume = default_volume;
        currentBPM = default_BPM;
        instrument_list = getInstrumentRange(0, 127);
    }

    public void setVolumeConstraints(int min_vol, int max_vol, int step_fact_vol, int default_vol) {
        if (min_vol > max_vol)
            throw new IllegalArgumentException("min_vol cannot be bigger than max_vol!");
        if (min_vol < 0)
            throw new IllegalArgumentException("min_vol cannot be smaller than zero!");
        if (step_fact_vol <= 0)
            throw new IllegalArgumentException("volume factor cannot be smaller or equal to zero!");
        if (min_vol > default_vol || default_vol > max_vol)
            throw new IllegalArgumentException("default volume out of range!");
        min_volume = min_vol;
        max_volume = max_vol;
        step_factor_volume = step_fact_vol;
        default_volume = default_vol;
        currentVolume = default_vol;
    }
    public void setOctaveConstraints(int min_oct, int max_oct, int step_oct, int default_oct) {
        if (min_oct > max_oct)
            throw new IllegalArgumentException("min_oct cannot be bigger than max_oct!");
        if (min_oct < 0)
            throw new IllegalArgumentException("min_oct cannot be smaller than zero!");
        if (step_oct <= 0)
            throw new IllegalArgumentException("octave step cannot be smaller or equal to zero!");
        if (min_oct > default_oct || default_oct > max_oct)
            throw new IllegalArgumentException("default octave out of range!");
        min_octave = min_oct;
        max_octave = max_oct;
        step_octave = step_oct;
        default_octave = default_oct;
        currentOctave = default_octave;
    }
    public void setBPMConstraints(int min_bpm, int max_bpm, int step_bpm, int default_bpm) {
        if (min_bpm > max_bpm)
            throw new IllegalArgumentException("min_bpm cannot be bigger than max_bpm!");
        if (min_bpm < 0)
            throw new IllegalArgumentException("min_bpm cannot be smaller than zero!");
        if (step_bpm <= 0)
            throw new IllegalArgumentException("bpm step cannot be smaller or equal to zero!");
        if (min_bpm > default_bpm || default_bpm > max_bpm)
            throw new IllegalArgumentException("default bpm out of range!");
        min_BPM = min_bpm;
        max_BPM = max_bpm;
        step_BPM = step_bpm;
        default_BPM = default_bpm;
        currentBPM = default_bpm;
    }

    public void setInstrumentConstraints(int[] instruments, int default_inst) {
        if (instruments.length == 0)
            throw new IllegalArgumentException("needs to specify at least one instrument!");
        boolean default_in_array = false;
        for (int element : instruments) {
            if (element == default_inst) {
                default_in_array = true;
                break;
            }
        }
        if (!default_in_array)
            throw new IllegalArgumentException("default has to be in array!");
        instrument_list = instruments;
        default_instrument = default_inst;
        currentInstrument = default_inst;
    }

    public void setInstrumentConstraints(int initial_inst, int final_inst, int default_inst) {
        instrument_list = getInstrumentRange(initial_inst, final_inst);
        default_instrument = default_inst;
        currentInstrument = default_inst;
    }
    
    public int getOctave() {
        return currentOctave;
    }
    public int getVolume() {
        return currentVolume;
    }
    public int getBPM() {
        return currentBPM;
    }
    public int getInstrument() {
        return currentInstrument;
    }
    public int getNote() {
        if (currentNote == NoteEnum.NONE)
            return NO_NOTE;
        else
            return (currentNote.getValue() + (OCTAVE_SIZE*currentOctave));
    }
    public void setOctave(int octave) {
        if (octave <= max_octave && octave >= min_octave)
            currentOctave = octave;
        else
            currentOctave = default_octave;
    }
    public void setVolume(int volume) {
        if (volume <= max_volume && volume >= min_volume)
            currentVolume = volume;
        else
            currentVolume = default_volume;
    }
    public void setBPM(int bpm) {
        if (bpm <= max_BPM && bpm >= min_BPM)
            currentBPM = bpm;
        else
            currentBPM = default_BPM;
    }
    public void setInstrument(int instrument) {
        boolean inst_in_array = false;
        for (int element : instrument_list) {
            if (element == instrument) {
                inst_in_array = true;
                break;
            }
        }
        if (inst_in_array)
            currentInstrument = instrument;
        else
            currentInstrument = default_instrument;
    }
    public void setNote(NoteEnum note) {
        currentNote = note;
    }
    public void setRandomNote() {
        currentNote = NoteEnum.values()[new Random().nextInt(NoteEnum.values().length)];
    }

    public void increaseOctave() {
        setOctave(currentOctave + step_octave);
    }
    public void increaseVolume() {
        setVolume( (int) (currentVolume * step_factor_volume));
    }
    public void increaseBPM() {
        setBPM(currentBPM + step_BPM);
    }
    public void decreaseOctave() {
        setOctave(currentOctave - step_octave);
    }
    public void decreaseVolume() {
        setVolume( (int) (currentVolume / step_factor_volume));
    }
    public void decreaseBPM() {
        setBPM(currentBPM - step_BPM);
    }
    public void nextInstrument() {
        int index = 1;
        for (int element : instrument_list) {
            if (element == currentInstrument)
                break;
            index++;
        }
        if (index < instrument_list.length)
            currentInstrument = instrument_list[index];
        else
            currentInstrument = instrument_list[0];
    }
    public void setRandomInstrument() {
        currentInstrument = new Random().nextInt(instrument_list.length);
    }

    private int[] getInstrumentRange(int first, int last) {
        int inst_array_length = last-first+1;
        int[] inst_array = new int[inst_array_length];
        if (first < 0 || last > 127)
            throw new IllegalArgumentException("Instruments range is out of MIDI range!");
        for (int i=0; i<inst_array_length; i++) {
            inst_array[i] = first + i;
        }
        return inst_array;
    }
}