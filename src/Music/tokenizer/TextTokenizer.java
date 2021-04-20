package Music.tokenizer;

import java.util.List;
import java.util.ArrayList;
import Music.Enums.InstructionEnum;

public class TextTokenizer {

    private List<InstructionEnum> tokens;

    public TextTokenizer(String raw_text) {
        tokenizeMusic(raw_text);
    }

    public List<InstructionEnum> getTokens() {
        return tokens;
    }

    private void tokenizeMusic(String text) {
        this.tokens = new ArrayList<>();
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
                if (found_instruction)
                    break;
            }
            if (!found_instruction) {
                text = text.substring(1);
                tokens.add(InstructionEnum.REPEAT_NOTE);
            }
        }
    }
}