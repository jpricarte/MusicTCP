package Music.Enums;

import Music.musicState.MusicState;

public enum InstructionEnum {
    BPM_UP(new String[]{"BPM+"}) {
        @Override
        public String getTranslation(MusicState state){
            state.increaseBPM();
            return " T" + state.getBPM();
        }
    },
    BPM_DOWN(new String[]{"BPM-"}) {
        @Override
        public String getTranslation(MusicState state){
            state.decreaseBPM();
            return " T" + state.getBPM();
        }
    },
    OCTAVE_UP(new String[]{"T+"}) {
        @Override
        public String getTranslation(MusicState state){
            state.increaseOctave();
            return "";
        }
    },
    OCTAVE_DOWN(new String[]{"T-"}) {
        @Override
        public String getTranslation(MusicState state){
            state.decreaseOctave();
            return "";
        }
    }, 
    VOL_UP(new String[]{"+"}) {
        @Override
        public String getTranslation(MusicState state){
            state.increaseVolume();
            return " :CON(7," + state.getVolume()+")";
        }
    },
    VOL_DOWN(new String[]{"-"}) {
        @Override
        public String getTranslation(MusicState state){
            state.decreaseVolume();
            return " :CON(7," + state.getVolume()+")";
        }
    },
    CHANGE_INSTRUMENT(new String[]{"\n"}) {
        @Override
        public String getTranslation(MusicState state){
            state.nextInstrument();
            return " I" + state.getInstrument();
        }
    },
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
    PAUSE(new String[]{" "}) {
        @Override
        public String getTranslation(MusicState state){
            return " R";
        }
    },
    RANDOM_NOTE(new String[]{"?", "."}) {
        @Override
        public String getTranslation(MusicState state){
            state.setRandomNote();
            return " " + state.getNote();
        }
    },
    REPEAT_NOTE(new String[]{"O", "U", "I"}) {
        @Override
        public String getTranslation(MusicState state){
            if (state.getNote() == MusicState.NO_NOTE)
                return " R";
            else
                return " " + state.getNote();
        }
    };

    private String[] instructionValue;

    InstructionEnum(String[] value) {
        this.instructionValue = value;
    }

    public abstract String getTranslation(MusicState state);

    public String[] getName() {
        return instructionValue;
    }
}
