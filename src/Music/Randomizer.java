package Music;

public class Randomizer {
    public String getRandomNote(int currentOctave) {
        int value = (int) Math.random();
        return Integer.toString(value);
    }

    public String getRandomInstrument() {
        int value = 0;
        return Integer.toString(value);
    }
}
