package Music;

import java.util.Random;

// Não esquecer de colocar um espaço entre cada valor, pq se não vai dar ruim

public class TextConversor {
    private static final String PAUSE = " R";
    private int currentOctave;      // oitava do momento
    private int currentInstrument;  // instrumento atual (primeiramente escolhido pelo tal do usuário)
    private int currentVolume;      // volume atual da música
    private int currentBpm;         // BPM atual (primeiramente escolhido pelo tal do usuário)
    private NoteEnum currentNote;   //nota atual

    private Randomizer randomizer = new Randomizer();
    public String convert(String raw_text) {
        String musicado_text = raw_text.toUpperCase();
        cleanString(musicado_text);
        addPauses(musicado_text);
        convertNotes(musicado_text);
        setBpms(musicado_text);
        setInstruments(musicado_text);
        setVolume(musicado_text);

        return musicado_text;
    }

    // Passo 1: remover todas os caracteres que não importam
    private void cleanString(String text) {}

    // Passo 2: adicionar as pausas
    private void addPauses(String text) {
        text.replace(" ", PAUSE);
    }

    // Passo 3: conversão das notas nas oitavas certas
    private void convertNotes(String text) {
        text.replace(".", " "+Integer.toString(NoteEnum.randomNote().noteValue + currentOctave));
        text.replace("?", " "+Integer.toString(NoteEnum.randomNote().noteValue + currentOctave));

        // substituir as notas com as oitavas certas
    }

    //Passo 4: ajuste nos BPM's
    private void setBpms(String text) {
        text = " T" + currentBpm + " " + text;
    }

    // Passo 5: ajuste dos instrumentos
    private void setInstruments(String text) {
        text = "I" + currentInstrument + " " + text;
        text.replace("\n", " I" + randomizer.getRandomInstrument() + " ");
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