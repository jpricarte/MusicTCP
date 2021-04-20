package Music.musicState;

import static org.junit.jupiter.api.Assertions.*;

import Music.Enums.NoteEnum;
import org.jfugue.temporal.TemporalEvents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MusicStateTest {

    MusicState music_state;

    @BeforeEach
    void init () {
        music_state = new MusicState();
    }

    @Test
    void testMusicStateNote() {
        int new_octave = 2;
        NoteEnum note = NoteEnum.A;
        NoteEnum no_note = NoteEnum.NONE;
        int new_note_value = note.getValue() + new_octave * MusicState.OCTAVE_SIZE;
        int no_note_value = no_note.getValue();
        music_state.setNote(note);
        music_state.setOctave(new_octave);
        assertEquals(new_note_value, music_state.getNote());
        music_state.setNote(no_note);
        assertEquals(no_note_value, music_state.getNote());
    }

    @Test
    void testMusicStateRandomNote() {
        int new_octave = 2;
        music_state.setOctave(new_octave);
        music_state.setRandomNote();
        int note_code = music_state.getNote();
        int note_value = note_code -(new_octave * MusicState.OCTAVE_SIZE);
        boolean has_element = false;
        for (NoteEnum note : NoteEnum.values()){
            if (note.getValue() == note_value) {
                has_element = true;
                break;
            }
        }
        assertTrue(has_element);
    }

    @Test
    void testDefaultMusicStateVolume() {
        assertEquals(MusicState.DEFAULT_DEFAULT_VOLUME, music_state.getVolume());
    }

    @Test
    void testMusicStateVolumeChange() {
        int new_volume = (MusicState.DEFAULT_MAX_VOLUME + MusicState.DEFAULT_MIN_VOLUME) /2;
        music_state.setVolume(new_volume);
        assertEquals(new_volume, music_state.getVolume());
    }

    @Test
    void testMusicStateDefaultVolumeConstraints() {
        music_state.setVolume(MusicState.DEFAULT_MAX_VOLUME + 1);
        assertEquals(music_state.getVolume(), MusicState.DEFAULT_DEFAULT_VOLUME);
        music_state.setVolume(MusicState.DEFAULT_MIN_VOLUME - 1);
        assertEquals(MusicState.DEFAULT_DEFAULT_VOLUME, music_state.getVolume());
    }

    @Test
    void testMusicStateVolumeConstraintsUnderDefaultValue() {
        int new_min_volume = 10;
        int new_max_volume = 50;
        int new_step_volume = 2;
        int under_volume = 9;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setVolumeConstraints(
                        new_min_volume,
                        new_max_volume,
                        new_step_volume,
                        under_volume),
                "Exception not thrown"
        );
        assertEquals("default volume out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateVolumeConstraintsOverDefaultValue() {
        int new_min_volume = 10;
        int new_max_volume = 50;
        int new_step_volume = 2;
        int over_volume = 51;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setVolumeConstraints(
                        new_min_volume,
                        new_max_volume,
                        new_step_volume,
                        over_volume),
                "Exception not thrown"
        );
        assertEquals("default volume out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateVolumeConstraintsValidValue() {
        int new_min_volume = 10;
        int new_max_volume = 50;
        int new_step_volume = 2;
        int new_default_volume = 20;
        music_state.setVolumeConstraints(
                new_min_volume,
                new_max_volume,
                new_step_volume,
                new_default_volume);
        assertEquals(new_default_volume, music_state.getVolume());
        music_state.setVolume(new_min_volume);
        assertEquals(new_min_volume, music_state.getVolume());
        music_state.setVolume(new_max_volume);
        assertEquals(new_max_volume, music_state.getVolume());
    }

    @Test
    void testMusicStateVolumeConstraintsValue() {
        int new_min_volume = 70;
        int new_max_volume = 50;
        int new_step_volume = 2;
        int new_default_volume = 60;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setVolumeConstraints(
                        new_min_volume,
                        new_max_volume,
                        new_step_volume,
                        new_default_volume),
                "Exception not thrown"
        );
        assertEquals("min_vol cannot be bigger than max_vol!", thrown.getMessage());
    }

    @Test
    void testMusicStateVolumeConstraintsZeroFactorValue() {
        int new_min_volume = 10;
        int new_max_volume = 50;
        int new_step_volume = 0;
        int new_default_volume = 20;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setVolumeConstraints(
                        new_min_volume,
                        new_max_volume,
                        new_step_volume,
                        new_default_volume),
                "Exception not thrown"
        );
        assertEquals("volume factor cannot be smaller or equal to zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateVolumeConstraintsNegativeMinimalValue() {
        int new_min_volume = -10;
        int new_max_volume = 50;
        int new_step_volume = 0;
        int new_default_volume = 20;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setVolumeConstraints(
                        new_min_volume,
                        new_max_volume,
                        new_step_volume,
                        new_default_volume),
                "Exception not thrown"
        );
        assertEquals("min_vol cannot be smaller than zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateIncreaseVolume() {
        int new_volume = (int) (MusicState.DEFAULT_DEFAULT_VOLUME * MusicState.DEFAULT_STEP_FACTOR_VOLUME);
        music_state.increaseVolume();
        assertEquals(new_volume, music_state.getVolume());
    }

    @Test
    void testMusicStateDecreaseVolume() {
        int new_volume = (int) (MusicState.DEFAULT_DEFAULT_VOLUME / MusicState.DEFAULT_STEP_FACTOR_VOLUME);
        music_state.decreaseVolume();
        assertEquals(new_volume, music_state.getVolume());
    }

    @Test
    void testDefaultMusicStateOctave() {
        assertEquals(MusicState.DEFAULT_DEFAULT_OCTAVE, music_state.getOctave());
    }

    @Test
    void testMusicStateOctaveChange() {
        int new_octave = (MusicState.DEFAULT_MAX_OCTAVE + MusicState.DEFAULT_MIN_OCTAVE) /2;
        music_state.setOctave(new_octave);
        assertEquals(new_octave, music_state.getOctave());
    }

    @Test
    void testMusicStateDefaultOctaveConstraints() {
        music_state.setOctave(MusicState.DEFAULT_MAX_OCTAVE + 1);
        assertEquals(music_state.getOctave(), MusicState.DEFAULT_DEFAULT_OCTAVE);
        music_state.setOctave(MusicState.DEFAULT_MIN_OCTAVE - 1);
        assertEquals(MusicState.DEFAULT_DEFAULT_OCTAVE, music_state.getOctave());
    }

    @Test
    void testMusicStateOctaveConstraintsUnderDefaultValue() {
        int new_min_octave = 1;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int under_octave = 0;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setOctaveConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        under_octave),
                "Exception not thrown"
        );
        assertEquals("default octave out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateOctaveConstraintsOverDefaultValue() {
        int new_min_octave = 1;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int over_octave = 6;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setOctaveConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        over_octave),
                "Exception not thrown"
        );
        assertEquals("default octave out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateOctaveConstraintsValidValue() {
        int new_min_octave = 1;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int new_default_octave = 3;
        music_state.setOctaveConstraints(
                new_min_octave,
                new_max_octave,
                new_step_octave,
                new_default_octave);
        assertEquals(new_default_octave, music_state.getOctave());
        music_state.setOctave(new_min_octave);
        assertEquals(new_min_octave, music_state.getOctave());
        music_state.setOctave(new_max_octave);
        assertEquals(new_max_octave, music_state.getOctave());
    }

    @Test
    void testMusicStateOctaveConstraintsValue() {
        int new_min_octave = 7;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int new_default_octave = 6;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setOctaveConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        new_default_octave),
                "Exception not thrown"
        );
        assertEquals("min_oct cannot be bigger than max_oct!", thrown.getMessage());
    }

    @Test
    void testMusicStateOctaveConstraintsZeroFactorValue() {
        int new_min_octave = 1;
        int new_max_octave = 5;
        int new_step_octave = 0;
        int new_default_octave = 3;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setOctaveConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        new_default_octave),
                "Exception not thrown"
        );
        assertEquals("octave step cannot be smaller or equal to zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateOctaveConstraintsNegativeMinimalValue() {
        int new_min_octave = -1;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int new_default_octave = 3;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setOctaveConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        new_default_octave),
                "Exception not thrown"
        );
        assertEquals("min_oct cannot be smaller than zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateIncreaseOctave() {
        int new_octave = MusicState.DEFAULT_DEFAULT_OCTAVE + MusicState.DEFAULT_STEP_OCTAVE;
        music_state.increaseOctave();
        assertEquals(new_octave, music_state.getOctave());
    }

    @Test
    void testMusicStateDecreaseOctave() {
        int new_octave = MusicState.DEFAULT_DEFAULT_OCTAVE - MusicState.DEFAULT_STEP_OCTAVE;
        music_state.decreaseOctave();
        assertEquals(new_octave, music_state.getOctave());
    }

    @Test
    void testDefaultMusicStateBPM() {
        assertEquals(MusicState.DEFAULT_DEFAULT_BPM, music_state.getBPM());
    }

    @Test
    void testMusicStateBPMChange() {
        int new_BPM = (MusicState.DEFAULT_MAX_BPM + MusicState.DEFAULT_MIN_BPM) /2;
        music_state.setBPM(new_BPM);
        assertEquals(new_BPM, music_state.getBPM());
    }

    @Test
    void testMusicStateDefaultBPMConstraints() {
        music_state.setBPM(MusicState.DEFAULT_MAX_BPM + 1);
        assertEquals(music_state.getBPM(), MusicState.DEFAULT_DEFAULT_BPM);
        music_state.setBPM(MusicState.DEFAULT_MIN_BPM - 1);
        assertEquals(MusicState.DEFAULT_DEFAULT_BPM, music_state.getBPM());
    }

    @Test
    void testMusicStateBPMConstraintsUnderDefaultValue() {
        int new_min_BPM = 1;
        int new_max_BPM = 5;
        int new_step_BPM = 1;
        int under_BPM = 0;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setBPMConstraints(
                        new_min_BPM,
                        new_max_BPM,
                        new_step_BPM,
                        under_BPM),
                "Exception not thrown"
        );
        assertEquals("default bpm out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateBPMConstraintsOverDefaultValue() {
        int new_min_octave = 1;
        int new_max_octave = 5;
        int new_step_octave = 1;
        int over_octave = 6;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setBPMConstraints(
                        new_min_octave,
                        new_max_octave,
                        new_step_octave,
                        over_octave),
                "Exception not thrown"
        );
        assertEquals("default bpm out of range!", thrown.getMessage());
    }

    @Test
    void testMusicStateBPMConstraintsValidValue() {
        int new_min_BPM = 1;
        int new_max_BPM = 5;
        int new_step_BPM = 1;
        int new_default_BPM = 3;
        music_state.setBPMConstraints(
                new_min_BPM,
                new_max_BPM,
                new_step_BPM,
                new_default_BPM);
        assertEquals(new_default_BPM, music_state.getBPM());
        music_state.setBPM(new_min_BPM);
        assertEquals(new_min_BPM, music_state.getBPM());
        music_state.setBPM(new_max_BPM);
        assertEquals(new_max_BPM, music_state.getBPM());
    }

    @Test
    void testMusicStateBPMConstraintsValue() {
        int new_min_BPM = 7;
        int new_max_BPM = 5;
        int new_step_BPM = 1;
        int new_default_BPM = 6;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setBPMConstraints(
                        new_min_BPM,
                        new_max_BPM,
                        new_step_BPM,
                        new_default_BPM),
                "Exception not thrown"
        );
        assertEquals("min_bpm cannot be bigger than max_bpm!", thrown.getMessage());
    }

    @Test
    void testMusicStateBPMConstraintsZeroFactorValue() {
        int new_min_BPM = 1;
        int new_max_BPM = 5;
        int new_step_BPM = 0;
        int new_default_BPM = 3;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setBPMConstraints(
                        new_min_BPM,
                        new_max_BPM,
                        new_step_BPM,
                        new_default_BPM),
                "Exception not thrown"
        );
        assertEquals("bpm step cannot be smaller or equal to zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateBPMConstraintsNegativeMinimalValue() {
        int new_min_BPM = -1;
        int new_max_BPM = 5;
        int new_step_BPM = 1;
        int new_default_BPM = 3;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setBPMConstraints(
                        new_min_BPM,
                        new_max_BPM,
                        new_step_BPM,
                        new_default_BPM),
                "Exception not thrown"
        );
        assertEquals("min_bpm cannot be smaller than zero!", thrown.getMessage());
    }

    @Test
    void testMusicStateIncreaseBPM() {
        int new_BPM = MusicState.DEFAULT_DEFAULT_BPM + MusicState.DEFAULT_STEP_BPM;
        music_state.increaseBPM();
        assertEquals(new_BPM, music_state.getBPM());
    }

    @Test
    void testMusicStateDecreaseBPM() {
        int new_BPM = MusicState.DEFAULT_DEFAULT_BPM - MusicState.DEFAULT_STEP_BPM;
        music_state.decreaseBPM();
        assertEquals(new_BPM, music_state.getBPM());
    }

    @Test
    void testDefaultMusicStateInstrument() {
        assertEquals(MusicState.DEFAULT_CURRENT_INSTRUMENT, music_state.getInstrument());
    }

    @Test
    void testMusicStateInstrumentChange() {
        int new_instrument = MusicState.DEFAULT_CURRENT_INSTRUMENT + 1;
        music_state.setInstrument(new_instrument);
        assertEquals(new_instrument, music_state.getInstrument());
    }

    @Test
    void testMusicStateDefaultInstrumentInRangeValidConstraints() {
        int first_instrument = 5;
        int last_instrument = 32;
        int default_instrument = 10;
        music_state.setInstrumentConstraints(first_instrument, last_instrument, default_instrument);
        assertEquals(default_instrument, music_state.getInstrument());
        music_state.setInstrument(first_instrument);
        assertEquals(first_instrument, music_state.getInstrument());
        music_state.setInstrument(last_instrument);
        assertEquals(last_instrument, music_state.getInstrument());
    }

    @Test
    void testMusicStateDefaultInstrumentInRangeInvalidConstraints() {
        int first_instrument = -5;
        int last_instrument = 32;
        int default_instrument = 10;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setInstrumentConstraints(
                        first_instrument,
                        last_instrument,
                        default_instrument),
                "Exception not thrown"
        );
        assertEquals("Instruments range is out of MIDI range!", thrown.getMessage());
    }

    @Test
    void testMusicStateDefaultInstrumentConstraints() {
        music_state.setInstrument(128);
        assertEquals(music_state.getInstrument(), MusicState.DEFAULT_CURRENT_INSTRUMENT);
        music_state.setInstrument(-55);
        assertEquals(MusicState.DEFAULT_CURRENT_INSTRUMENT, music_state.getInstrument());
    }

    @Test
    void testMusicStateInstrumentConstraintsOutOfRange() {
        int[] new_instruments = {1, 5, 3, 7};
        int new_current_instrument = 95;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setInstrumentConstraints(
                        new_instruments,
                        new_current_instrument),
                "Exception not thrown"
        );
        assertEquals("default has to be in array!", thrown.getMessage());
    }

    @Test
    void testMusicStateInstrumentConstraintsValidValue() {
        int[] new_instruments = {1, 5, 3, 7};
        int new_current_instrument = 3;

        music_state.setInstrumentConstraints(
                new_instruments,
                new_current_instrument);
        assertEquals(new_current_instrument, music_state.getInstrument());
        music_state.setInstrument(new_instruments[3]);
        assertEquals(new_instruments[3], music_state.getInstrument());
        music_state.setInstrument(new_instruments[0]);
        assertEquals(new_instruments[0], music_state.getInstrument());
    }

    @Test
    void testMusicStateInstrumentConstraints() {
        int[] new_instruments = {};
        int new_current_instrument = 3;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> music_state.setInstrumentConstraints(
                        new_instruments,
                        new_current_instrument),
                "Exception not thrown"
        );
        assertEquals("needs to specify at least one instrument!", thrown.getMessage());
    }

    @Test
    void testMusicStateNextInstrument() {
        music_state.setInstrument(0);
        for (int instrument = 0; instrument <= 127; instrument++) {
            assertEquals(instrument, music_state.getInstrument());
            music_state.nextInstrument();
        }
        assertEquals(0, music_state.getInstrument());
    }

    @Test
    void testMusicStateRandomInstrument() {
        music_state.setRandomInstrument();
        int instrument = music_state.getInstrument();
        assertTrue(instrument <= 127 && instrument >= 0);
    }
}