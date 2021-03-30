package Music.Enums;

import java.util.Random;

public enum NoteEnum {
    DO(0), RE(2), MI(4), FA(5), SOL(7), LA(9), SI(11), REST(-1);

    public int noteValue;

    NoteEnum(int value) {
        if (value > 0)
            noteValue=value;
        else
            noteValue=0;
    }

    public static NoteEnum randomNote(){
        int x = new Random().nextInt(NoteEnum.class.getEnumConstants().length);
        return NoteEnum.class.getEnumConstants()[x];
    }
}
