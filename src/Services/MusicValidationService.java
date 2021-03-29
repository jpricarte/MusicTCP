package Services;

import Music.Music;

public class MusicValidationService {
    public static String errorMessage = null;

    public static boolean validateString(String text, String instrument) {
        if(text.trim().equals("")) {
           errorMessage = "Por acaso você é burro? Como eu vou musicar uma string vazia?";
            return false;
        } else if(text.length() > 240){
            errorMessage = "O texto não pode ter mais que 240 caracteres, meu consagradinho";
            return false;
        } else if(instrument == null) {
            errorMessage = "Velho, preciso de um instrumento pra tocar, né??";
            return false;
        }
        return true;
    }

    public static int parseBPM(String bpm_string){
        int bpm;
        try {
            bpm = Integer.parseInt(bpm_string);

            if(bpm < 40 || bpm > 220) {
                errorMessage = "O BPM deve ser um valor entre 40 e 220";
                bpm = -1;
            }
        } catch (NumberFormatException exception) {
            errorMessage = "Mano, na moral, BPM é número, né caralho?";
            bpm = -1;
        }
        return bpm;
    }
}
