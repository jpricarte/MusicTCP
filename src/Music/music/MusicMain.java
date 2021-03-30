package Music.music;

public class MusicMain {

    public static void main(String[] args) {
        MusicJFugue musicJFugue = new MusicJFugue(6, 32, 80, 32);
        System.out.println(musicJFugue.toJFuguePlayableString("DINOSSAURO PETECA -+BPMT ?LIGACAO CAFE COM BISCOITO 43A"));
    }
    
}
