package Music.music;

import java.util.List;
import Music.Enums.NoteEnum;
import Music.Enums.InstructionEnum;
import Music.tokenizer.TextTokenizer;

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
    }

    public void initializeMusic() {
        music = "I" + initialInstrument;
        music += " T" + initialBPM;
        music += " :CON(7," + initialVolume+") ";
    }

    public void insertNote(NoteEnum note) {
        int convertedOctave = 12*currentOctave;
        music += " " + (convertedOctave + note.noteValue);
        currentNote = note;
    };

    public void insertRandomNote() {
        NoteEnum.randomNote();
        int convertedOctave = 12*currentOctave;
        music += " " + (convertedOctave + currentNote.noteValue);
        
    }

    public void increaseBPM(){
        currentBpm = Math.min(250, currentBpm+50);
        music += " T" + currentBpm;
    }

    public void decraseBPM(){
        currentBpm = Math.max(50, currentBpm-50);
        music += " T" + currentBpm;
    }

    public void increaseOctave(){
        currentOctave = Math.min(9, currentOctave+1);
    }

    public void decreaseOctave(){
        currentOctave = Math.max(1, currentOctave-1);
    }

    public void increaseVolume(){
        currentVolume = Math.min(127, 2*currentVolume);
        music += " :CON(7," + currentVolume + ") ";
    }

    public void decreaseVolume(){

    }

    public void resetVolume() {
        currentVolume = initialVolume;
        music += " :CON(7," + initialVolume+") ";
    }

    public void insertRest(){
        music += " R";
        currentNote = NoteEnum.REST;
    }

    public void repeatNote(){
        if (currentNote == NoteEnum.REST) {
            insertRest();
        }
        else {
            insertNote(currentNote);
        }
    }

    public void chooseRandomInstrument() {
        currentInstrument = new Random()
                .ints(0,128)
                .findFirst()
                .getAsInt();
        
        music += " I"+currentInstrument;
    }

    public void nextInstrument() {
        currentInstrument++;
        
        music += " I"+currentInstrument;
    }

    public String toJFuguePlayableString(String text) {
        TextTokenizer textTokenizer = new TextTokenizer(text);
        List<InstructionEnum>tokensList = textTokenizer.getTokens();
        
        initializeMusic();

        convertTokens(tokensList);

        return music;
    }

    public String getMusic() {
        return music;
    }


    private String convertTokens(List<InstructionEnum> tokens) {
        for (InstructionEnum instruction : tokens) {
            switch(instruction){
                case BPM_UP:
                    increaseBPM(); break;
                case BPM_DOWN:
                    decraseBPM(); break;
                case OCTAVE_UP:
                    increaseOctave(); break;
                case OCTAVE_DOWN: 
                    decreaseOctave(); break;
                case VOL_UP:
                    increaseVolume(); break; 
                case VOL_DOWN:
                    resetVolume(); break;
                case CHANGE_INSTRUMENT:
                    chooseRandomInstrument(); break;
                case A:
                    insertNote(NoteEnum.LA); break;
                case B: 
                    insertNote(NoteEnum.SI); break;
                case C:
                    insertNote(NoteEnum.DO); break;
                case D:
                    insertNote(NoteEnum.RE); break;
                case E:
                    insertNote(NoteEnum.MI); break;
                case F:
                    insertNote(NoteEnum.FA); break;
                case G:
                    insertNote(NoteEnum.SOL); break;
                case PAUSE:
                    insertRest(); break;
                case RANDOM_NOTE_1:
                case RANDOM_NOTE_2:
                    insertNote(NoteEnum.randomNote()); break;
                case REPEAT_NOTE_1:
                case REPEAT_NOTE_2:
                case REPEAT_NOTE_3:
                    repeatNote();
                    break;
            }
        }
        return "";
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
