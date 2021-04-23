package gui;

import Music.music.Music;
import Music.musicPlayer.MusicPlayer;
import Music.musicState.MusicState;
import org.jfugue.pattern.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class UserInterface {

    private JPanel jPanel;
    private JButton generateMusicButton;
    private JSlider volumeSelector;
    private JSpinner instrumentSelector;
    private JSpinner bpmSelector;
    private JButton playMusicButton;
    private JButton saveMusicButton;
    private JButton importTextButton;
    private JTextArea textArea;
    private JSpinner octaveSelector;

    private MusicPlayer musicPlayer;
    private Music music;

    public UserInterface() {
        musicPlayer = new MusicPlayer();

        importTextButton.addActionListener(ActionEvent -> openTextFile());
        generateMusicButton.addActionListener(ActionEvent -> generateMusicFromInfos());
        playMusicButton.addActionListener(ActionEvent -> playMusic());
        saveMusicButton.addActionListener(ActionEvent -> saveMusic());
    }

    private void createUIComponents() {

        instrumentSelector = new JSpinner();
        instrumentSelector.setModel(new SpinnerNumberModel(MusicState.DEFAULT_CURRENT_INSTRUMENT,
                0,127,1));

        bpmSelector = new JSpinner();
        bpmSelector.setModel(new SpinnerNumberModel(MusicState.DEFAULT_DEFAULT_BPM,
                MusicState.DEFAULT_MIN_BPM, MusicState.DEFAULT_MAX_BPM,MusicState.DEFAULT_STEP_BPM));

        octaveSelector = new JSpinner();
        octaveSelector.setModel(new SpinnerNumberModel(MusicState.DEFAULT_DEFAULT_OCTAVE,
                MusicState.DEFAULT_MIN_OCTAVE, MusicState.DEFAULT_MAX_OCTAVE, MusicState.DEFAULT_STEP_OCTAVE));

        volumeSelector = new JSlider(MusicState.DEFAULT_MIN_VOLUME,
                MusicState.DEFAULT_MAX_VOLUME, MusicState.DEFAULT_DEFAULT_VOLUME);
    }

    private void openTextFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new FileNameExtensionFilter("Text file","txt", "text"));
        chooser.showSaveDialog(jPanel);

        try {
            String text = new String(Files.readAllBytes(Paths.get(chooser.getSelectedFile().getAbsolutePath())));
            textArea.setText(text);

        } catch (IOException e) {
            JOptionPane.showInternalMessageDialog(null,e, "Falha ao abrir arquivo",ERROR_MESSAGE);
        }
    }

    private void generateMusicFromInfos() {
        int volume = volumeSelector.getValue();
        int instrument = (int) instrumentSelector.getValue();
        int bpm = (int) bpmSelector.getValue();
        int octave = (int) octaveSelector.getValue();
        String text = textArea.getText();

        music = new Music(octave, volume, bpm, instrument);
        Pattern musicPattern = music.getMusicPatternFromText(text);

        musicPlayer.setMusic(musicPattern);
        playMusicButton.setEnabled(true);
        saveMusicButton.setEnabled(true);
    }

    private void playMusic() {
        Runnable playMusicThread = new Runnable() {
            @Override
            public void run() {
                musicPlayer.playMusic();
            }
        };

        new Thread(playMusicThread).start();
    }

    private void saveMusic() {

        // TODO: create static method to get path
        String filename = (File.separator +  "my_music.midi");
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setSelectedFile(new File(filename));
        chooser.setFileFilter(new FileNameExtensionFilter("MIDI file","midi"));
        // Mostra a dialog de save file
        chooser.showSaveDialog(jPanel);
        File file = chooser.getSelectedFile();

        try {
            musicPlayer.saveMusic(file);
        } catch (IOException e) {
            JOptionPane.showInternalMessageDialog(null,e, "Erro ao salvar arquivo",ERROR_MESSAGE);
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
