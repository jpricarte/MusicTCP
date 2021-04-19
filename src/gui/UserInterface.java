package gui;

import Music.music.Music;
import Music.musicPlayer.MusicPlayer;
import org.jfugue.pattern.Pattern;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class UserInterface {

    private JPanel jPanel;
    private JButton generateMusicButton;
    private JSlider volumeSelector;
    private JSpinner instrumentSelector;
    private JSpinner bpmSelector;
    private JProgressBar progressBar1;
    private JButton playMusicButton;
    private JButton saveMusicButton;
    private JButton importTextButton;
    private JTextArea textArea;

    private MusicPlayer musicPlayer;
    private Music music;

    public UserInterface() {
        musicPlayer = new MusicPlayer();

        generateMusicButton.addActionListener(ActionEvent -> generateMusicFromInfos());
        playMusicButton.addActionListener(ActionEvent -> playMusic());

    }

    private void generateMusicFromInfos() {
        int volume = volumeSelector.getValue();
        int instrument = (int) instrumentSelector.getValue();
        int bpm = (int) bpmSelector.getValue();
        String text = textArea.getText();

        music = new Music(5, volume, bpm, instrument);
        Pattern musicPattern = music.getMusicPatternFromText(text);

        musicPlayer.setMusic(musicPattern);
    }

    private void playMusic() {
        musicPlayer.playMusic();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        instrumentSelector = new JSpinner();
        instrumentSelector.setModel(new SpinnerNumberModel(0,0,127,1));

        bpmSelector = new JSpinner();
        bpmSelector.setModel(new SpinnerNumberModel(110, 0, 255,1));

        volumeSelector = new JSlider(0,127,63);
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        UserInterface ui = new UserInterface();

        frame.setContentPane(ui.getjPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
