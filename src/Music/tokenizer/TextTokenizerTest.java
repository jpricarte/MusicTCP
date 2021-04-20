package Music.tokenizer;

import Music.Enums.InstructionEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextTokenizerTest {

    @Test
    void emptyTextTest() {
        String input_text = "";
        TextTokenizer tokenizer = new TextTokenizer(input_text);
        List<InstructionEnum> token_list = tokenizer.getTokens();
        List<InstructionEnum> empty_list = new ArrayList<>();
        assertEquals(empty_list, token_list);
    }

    @Test
    void singleNoteTest() {
        String input_text = "" + InstructionEnum.A.getName()[0];
        TextTokenizer tokenizer = new TextTokenizer(input_text);
        List<InstructionEnum> token_list = tokenizer.getTokens();
        List<InstructionEnum> reference_list = new ArrayList<>();
        reference_list.add(InstructionEnum.A);
        assertEquals(reference_list, token_list);
    }

    @Test
    void allNoteTest() {
        StringBuilder input_text = new StringBuilder();
        List<String> token_names = new ArrayList<>();
        for (InstructionEnum token : InstructionEnum.values()) {
            token_names.add(token.getName()[0]);
            input_text.append(token.getName()[0]);
        }
        TextTokenizer tokenizer = new TextTokenizer(input_text.toString());
        List<InstructionEnum> token_list = tokenizer.getTokens();
        List<String> tokenized_token_names = new ArrayList<>();
        for (InstructionEnum token : token_list)
            tokenized_token_names.add(token.getName()[0]);
        assertEquals(token_names, tokenized_token_names);
    }

    @Test
    void invalidNoteTest() {
        int invalid_note_code = 80;
        boolean note_is_valid = true;
        while (note_is_valid) {
            note_is_valid = false;
            for (InstructionEnum token : InstructionEnum.values()) {
                for (String word : token.getName()) {
                    if (Character.toString((char) invalid_note_code).equals(word)) {
                        note_is_valid = true;
                        invalid_note_code++;
                        break;
                    }
                }
                if (note_is_valid)
                    break;
            }
        }
        String invalidString = Character.toString((char) invalid_note_code);
        String input_text = "" + invalidString;
        TextTokenizer tokenizer = new TextTokenizer(input_text);
        List<InstructionEnum> token_list = tokenizer.getTokens();
        List<InstructionEnum> reference_list = new ArrayList<>();
        reference_list.add(InstructionEnum.REPEAT_NOTE);
        assertEquals(reference_list, token_list);
    }

}