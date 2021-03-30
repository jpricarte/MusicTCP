package Music.Enums;

public enum InstructionEnum {
    BPM_UP("BPM+"), BPM_DOWN("BPM-"), OCTAVE_UP("T+"), OCTAVE_DOWN("T-"), 
    VOL_UP("+"), VOL_DOWN("-"), CHANGE_INSTRUMENT("\n"),
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"),
    PAUSE(" "), RANDOM_NOTE_1("?"), RANDOM_NOTE_2("."),
    REPEAT_NOTE_1("O"), REPEAT_NOTE_2("U"), REPEAT_NOTE_3("I"),;

    private String instuctionValue;

    InstructionEnum(String value) {
        this.instuctionValue = value;
    }

    public String getValue(){
        return this.instuctionValue;
    }

}
