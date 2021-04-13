package Music.tokenizer;

import java.util.List;
import java.util.ArrayList;
import Music.Enums.InstructionEnum;
import Music.musicState.MusicState;

public class TextTokenizer {

    private List<InstructionEnum> tokens;

    public TextTokenizer(String raw_text) {
        tokenizeMusic(raw_text);
    }

    public List<InstructionEnum> getTokens() {
        return tokens;
    }

    private void tokenizeMusic(String text) {
        this.tokens = new ArrayList<InstructionEnum>();
        while (text.length() > 0){
            boolean found_instruction=false;
            for (InstructionEnum instruction : InstructionEnum.values()) {
                String[] words = instruction.getName();
                for (String word : words){
                    if (text.startsWith(word)){
                        tokens.add(instruction);
                        text = text.substring(word.length());
                        found_instruction=true;
                        break;
                    }
                }
                if (found_instruction == true)
                    break;
            }
            if (!found_instruction) {
                text = text.substring(1);
            }
        }
    }

    public static void main (String[] args) {
        TextTokenizer t = new TextTokenizer("A B C DEFG");
        List<InstructionEnum> l = t.getTokens();
        for (InstructionEnum e : l) {
            System.out.print(e.getName()[0]);
        }
        MusicState state = new MusicState();
        state.setVolumeConstraints(0, 20, 2, 2);
        state.setOctaveConstraints(1, 4, 1, 1);
        state.setBPMConstraints(40, 400, 10, 80);
        System.out.println("");
        for (InstructionEnum e : l) {
            System.out.print(e.getTranslation(state));
        }
    }
}