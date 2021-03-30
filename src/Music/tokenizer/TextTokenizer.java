package Music.tokenizer;

import java.util.List;
import java.util.ArrayList;
import Music.Enums.InstructionEnum;

public class TextTokenizer {

    private List<InstructionEnum> tokens;

    public TextTokenizer(String raw_text) {
        String musicado_text = raw_text.toUpperCase();
        tokenizeMusic(musicado_text);
    }

    public List<InstructionEnum> getTokens() {
        return tokens;
    }

    private void tokenizeMusic(String text) {
        this.tokens = new ArrayList<InstructionEnum>();
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
    }
}