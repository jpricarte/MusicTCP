package Music.conversor;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import Music.Enums.NoteEnum;
import Music.Enums.InstructionEnum;

public class TextConversor {
    private static final String PAUSE = " R";
    private int currentOctave;      // oitava do momento
    private int currentInstrument;  // instrumento atual (primeiramente escolhido pelo tal do usuário)
    private int currentVolume;      // volume atual da música
    private int currentBpm;         // BPM atual (primeiramente escolhido pelo tal do usuário)
    private NoteEnum currentNote;   //nota atual

    public String convert(String raw_text) {
        String musicado_text = raw_text.toUpperCase();
        List<InstructionEnum> tokens = tokenizeMusic(musicado_text);
    
        musicado_text = convertNotes(musicado_text);

        /*
        musicado_text = setBpms(musicado_text);
        musicado_text = setInstruments(musicado_text);
        musicado_text = setVolume(musicado_text);*/
        return musicado_text;
    }


    private List<InstructionEnum> tokenizeMusic(String text) {
        List<InstructionEnum> tokens = new ArrayList<InstructionEnum>();
        while (text.length() > 0){
            boolean flag=false;
            for (InstructionEnum instruction : InstructionEnum.values()) {
                String word = instruction.getValue();
                if (text.startsWith(word)){
                    tokens.add(instruction);
                    text = text.substring(word.length());
                    flag=true;
                    break;
                }
            }
            if (!flag) {
                text = text.substring(1);
            }
        }
        return tokens;
    }

    public static void main(String[] args) throws Exception {
        String ins = " q a b 43244 - + 96 TBPM-BPM+CP IP T+ T+ T- OIU -+ OIUT?.A-G0-9";
        System.out.println(ins);
        TextConversor c = new TextConversor();
        System.out.println(c.convert(ins));
    }

    // Passo 2: adicionar as pausas
    private String addPauses(String text) {
        return text.replace(" ", PAUSE);
    }

    // Passo 3: conversão das notas nas oitavas certas
    private String convertNotes(String text) {
        text.replace(".", " "+Integer.toString(NoteEnum.randomNote().noteValue + currentOctave));
        text.replace("?", " "+Integer.toString(NoteEnum.randomNote().noteValue + currentOctave));

        // substituir as notas com as oitavas certas
        for (int index = 0; index < text.length(); index++){
            if (text.charAt(index) == 'T'){
                while ()
            }
        }
        return text;
    }

    //Passo 4: ajuste nos BPM's
    private void setBpms(String text) {
        text = " T" + currentBpm + " " + text;
    }

    // Passo 5: ajuste dos instrumentos
    private void setInstruments(String text) {
        text = "I" + currentInstrument + " " + text;
        text.replace("\n", " I" + NoteEnum.randomNote() + " ");
    }

    // Passo 6: ajuste de volume
    private void setVolume(String text) {}

    public void chooseRandomInstrument() {
        currentInstrument = new Random()
                .ints(0,128)
                .findFirst()
                .getAsInt();
    }
}