package Music.Enums;

import Music.musicState.MusicState;

public enum InstructionEnum {
    A(new String[]{"A"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.A);
            return " " + state.getNote();
        }
    },
    B(new String[]{"B"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.B);
            return " " + state.getNote();
        }
    },
    C(new String[]{"C"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.C);
            return " " + state.getNote();
        }
    },
    D(new String[]{"D"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.D);
            return " " + state.getNote();
        }
    },
    E(new String[]{"E"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.E);
            return " " + state.getNote();
        }
    },
    F(new String[]{"F"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.F);
            return " " + state.getNote();
        }
    },
    G(new String[]{"G"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setNote(NoteEnum.G);
            return " " + state.getNote();
        }
    },
    REPEAT_NOTE(new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h",
            "j", "k", "l", "m", "n", "p", "q", "r",
            "s", "t", "v", "w", "x", "y", "z", "H",
            "J", "K", "L", "M", "N", "P", "Q", "R",
            "S", "T", "V", "W", "X", "Y", "Z"
    }) {
        @Override
        public String getTranslation(MusicState state){
            if (state.getNote() == MusicState.NO_NOTE)
                return " R";
            else
                return " " + state.getNote();
        }
    },
    VOL_UP(new String[]{" "}) {
        @Override
        public String getTranslation(MusicState state){
            state.increaseVolume();
            return " :CON(7," + state.getVolume()+")";
        }
    },
    CHANGE_TO_AGOGO(new String[]{"!"}) {
      @Override
      public String getTranslation(MusicState state){
          state.setInstrument(114);
          return " I" + state.getInstrument();
      }
    },
    CHANGE_TO_HARPSICHORD(new String[]{"O", "o", "I", "i", "U", "u"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setInstrument(7);
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_1_INSTRUMENT(new String[]{"1"}) {
        @Override
        public String getTranslation(MusicState state){
            state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_2_INSTRUMENT(new String[]{"2"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 2; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_3_INSTRUMENT(new String[]{"3"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 3; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_4_INSTRUMENT(new String[]{"4"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 4; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_5_INSTRUMENT(new String[]{"5"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 5; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_6_INSTRUMENT(new String[]{"6"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 6; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_7_INSTRUMENT(new String[]{"7"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 7; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_8_INSTRUMENT(new String[]{"8"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 8; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    INCREMENT_9_INSTRUMENT(new String[]{"9"}) {
        @Override
        public String getTranslation(MusicState state){
            for(int instrument = 0; instrument < 9; instrument++)
                state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
    OCTAVE_UP(new String[]{"?", "."}) {
        @Override
        public String getTranslation(MusicState state){
            state.increaseOctave();
            return "";
        }
    },
    CHANGE_TO_TUBULAR_BELLS(new String[]{"\n"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setInstrument(15);
            return " I" + state.getInstrument();
        }
    },
    CHANGE_TO_PAN_FLUE(new String[]{";"}) {
        @Override
        public String getTranslation(MusicState state){
            state.setInstrument(76);
            return " I" + state.getInstrument();
        }
    },
    CHANGE_TO_CHURCH_ORGAN(new String[]{","}) {
        @Override
        public String getTranslation(MusicState state){
            state.setInstrument(20);
            return " I" + state.getInstrument();
        }
    };

    private final String[] instructionValue;

    InstructionEnum(String[] value) {
        this.instructionValue = value;
    }

    public abstract String getTranslation(MusicState state);

    public String[] getName() {
        return instructionValue;
    }
}
