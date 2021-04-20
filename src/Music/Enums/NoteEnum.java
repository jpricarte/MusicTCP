package Music.Enums;

public enum NoteEnum {
    C(0), D(2), E(4), F(5), G(7), A(9), B(11), NONE(-1);

    private final int noteValue;

    NoteEnum(int value) {
        noteValue = value;
    }

    public int getValue() {
        return noteValue;
    }
}
