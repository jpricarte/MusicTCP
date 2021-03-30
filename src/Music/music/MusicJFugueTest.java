package Music.music;

import Music.Enums.NoteEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Jordi: Por motivos óbvios, eu não estou testando os métodos Random
// Jordi: Tive que parar antes de montar os testes do tokenizar, mas vou fazer esses testes

public class MusicJFugueTest {

    MusicJFugue musicJFugue;
    final String MUSIC_BEGIN = "I0 T120 :CON(7,64)";

    @BeforeEach
    void init () {
        musicJFugue = new MusicJFugue(5,0,120,64);
        musicJFugue.initializeMusic();
    }

    @Test
    void testinitializeMusic() {
        assertEquals(MUSIC_BEGIN,musicJFugue.getMusic());
    }

    @Test
    void testInsertNote() {
        musicJFugue.insertNote(NoteEnum.DO);
        musicJFugue.insertNote(NoteEnum.RE);
        musicJFugue.insertNote(NoteEnum.MI);
        musicJFugue.insertNote(NoteEnum.REST);
        musicJFugue.insertNote(NoteEnum.FA);
        musicJFugue.insertNote(NoteEnum.SOL);
        musicJFugue.insertNote(NoteEnum.LA);
        musicJFugue.insertNote(NoteEnum.SI);
        String expected = MUSIC_BEGIN +" 60 62 64 R 65 67 69 71";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testInsertRest() {
        musicJFugue.insertRest();
        String expected = MUSIC_BEGIN +" R";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testRepeatNote() {
        musicJFugue.insertNote(NoteEnum.DO);
        musicJFugue.repeatNote();
        String expected = MUSIC_BEGIN +" 60 60";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testRepeatNoteWithRest() {
        musicJFugue.insertRest();
        musicJFugue.repeatNote();
        String expected = MUSIC_BEGIN +" R R";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testIncreaseVolume() {
        musicJFugue.increaseVolume();
        assertEquals(MUSIC_BEGIN +" :CON(7,127)",musicJFugue.getMusic());
    }

    @Test
    void testMaxIncreaseVolume() {
        musicJFugue.increaseVolume(); //Vol=127
        musicJFugue.increaseVolume(); //Vol=127
        String expected = MUSIC_BEGIN +" :CON(7,127) :CON(7,127)";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testDecreaseVolume() {
        musicJFugue.decreaseVolume();
        assertEquals(MUSIC_BEGIN +" :CON(7,32)",musicJFugue.getMusic());
    }

    @Test
    void testMinDecreaseVolume() {
        musicJFugue.decreaseVolume(); //Vol=32
        musicJFugue.decreaseVolume(); //Vol=16
        musicJFugue.decreaseVolume(); //Vol=08
        musicJFugue.decreaseVolume(); //Vol=04
        musicJFugue.decreaseVolume(); //Vol=02
        musicJFugue.decreaseVolume(); //Vol=01
        musicJFugue.decreaseVolume(); //Vol=00
        musicJFugue.decreaseVolume(); //Vol=00

        String expected = MUSIC_BEGIN +
                " :CON(7,32) :CON(7,16) :CON(7,8) :CON(7,4) :CON(7,2) :CON(7,1) :CON(7,0) :CON(7,0)";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testResetVolume() {
        musicJFugue.decreaseVolume();
        musicJFugue.resetVolume();
        String expected = MUSIC_BEGIN + " :CON(7,32) :CON(7,64)";
        assertEquals(expected,musicJFugue.getMusic());
    }

    @Test
    void testNextInstrument() {
        musicJFugue.nextInstrument();
        String expected = MUSIC_BEGIN + " I1";
        assertEquals(expected,musicJFugue.getMusic());
    }

}
