package Music.music;

import Music.Enums.NoteEnum;
import Music.conversor.TextConversor;

import java.util.Random;

public class MusicJFugue extends Music {

    private int currentOctave;      // oitava do momento
    private int currentInstrument;  // instrumento atual (primeiramente escolhido pelo tal do usuário)
    private int currentVolume;      // volume atual da música
    private int currentBpm;         // BPM atual (primeiramente escolhido pelo tal do usuário)
    private NoteEnum currentNote;   //nota atual
    public String music;

    public MusicJFugue (int initialOctave, int initialInstrument, int initialBPM, int initialVolume) {

        super(initialOctave, initialInstrument, initialBPM, initialVolume);

        currentBpm = initialBPM;
        currentOctave = initialOctave;
        currentInstrument = initialInstrument;
        currentVolume = initialVolume;

        TextConversor textConversor = new TextConversor();

    }

    public void initializeMusic() {
        music = "I" + initialInstrument;
        music += " T" + initialBPM;
        music += " :CON(7," + initialVolume+") ";
    }

    public void insertNote(NoteEnum note) {
        int convertedOctave = 12*currentOctave;
        music += " " + (convertedOctave + note.noteValue);
    };

    public void insertRandomNote() {
        int convertedOctave = 12*currentOctave;
        music += " " + (convertedOctave + NoteEnum.randomNote().noteValue);
    }



    public String toJFuguePlayableString() {
        return music;
    }

    public void chooseRandomInstrument() {
        currentInstrument = new Random()
                .ints(0,128)
                .findFirst()
                .getAsInt();
    }

    public int getCurrentOctave() {
        return currentOctave;
    }

    public void setCurrentOctave(int currentOctave) {
        this.currentOctave = currentOctave;
    }

    public int getCurrentInstrument() {
        return currentInstrument;
    }

    public void setCurrentInstrument(int currentInstrument) {
        this.currentInstrument = currentInstrument;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    public int getCurrentBpm() {
        return currentBpm;
    }

    public void setCurrentBpm(int currentBpm) {
        this.currentBpm = currentBpm;
    }

    public NoteEnum getCurrentNote() {
        return currentNote;
    }

    public void setCurrentNote(NoteEnum currentNote) {
        this.currentNote = currentNote;
    }
}
