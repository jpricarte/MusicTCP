package Music;

import java.util.Random;

public enum NoteEnum {
    DO(0), RE(2), MI(4), FA(5), SOL(7), LA(9), SI(11);

    public int noteValue;

    NoteEnum(int value) {
        noteValue=value;
    }

    public static NoteEnum randomNote(){
        int x = new Random().nextInt(NoteEnum.class.getEnumConstants().length);
        return NoteEnum.class.getEnumConstants()[x];
    }
}
