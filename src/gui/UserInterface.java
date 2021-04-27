package gui;

import Music.music.Music;
import Music.musicPlayer.MusicPlayer;
import Music.musicState.MusicState;
import org.jfugue.pattern.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class UserInterface {

    private final String OPEN_FILE_ERROR_MESSAGE = "Falha ao abrir arquivo";
    private final String SAVE_FILE_ERROR_MESSAGE = "Falha ao salvar arquivo";
    private final String NO_FILE_WARNING_MESSAGE = "Nenhum texto foi carregado pois nenhum arquivo foi selecionado";
    private final String CANCELLED_SAVE_WARNING_MESSAGE = "Operação Cancelada, a música não foi salva";

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

    private final MusicPlayer musicPlayer;
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
        int result = chooser.showSaveDialog(jPanel);

        if(result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showInternalMessageDialog(null,NO_FILE_WARNING_MESSAGE, "Atenção!",JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                String text = new String(Files.readAllBytes(Paths.get(chooser.getSelectedFile().getAbsolutePath())));
                textArea.setText(text);
            } catch (IOException e) {
                JOptionPane.showInternalMessageDialog(null,OPEN_FILE_ERROR_MESSAGE, "Erro!",ERROR_MESSAGE);
            } catch (NullPointerException e) {
                JOptionPane.showInternalMessageDialog(null,NO_FILE_WARNING_MESSAGE, "Atenção!",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void generateMusicFromInfos() {
        int volume = volumeSelector.getValue();
        int instrument = (int) instrumentSelector.getValue();
        int bpm = (int) bpmSelector.getValue();
        int octave = (int) octaveSelector.getValue();
        String text = textArea.getText();
        music = new Music(octave, volume, bpm, instrument);

        try {
            Pattern musicPattern = music.getMusicPatternFromText(text);
            musicPlayer.setMusic(musicPattern);

            playMusicButton.setEnabled(true);
            saveMusicButton.setEnabled(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showInternalMessageDialog(null,e.getMessage(), "Erro!",ERROR_MESSAGE);
        }

    }

    private void playMusic() {
        Runnable playMusicThread = () -> musicPlayer.playMusic();
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
        int result = chooser.showSaveDialog(jPanel);

        if(result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showInternalMessageDialog(null,CANCELLED_SAVE_WARNING_MESSAGE,
                    "Atenção!",JOptionPane.WARNING_MESSAGE);
        }
        else {
            File file = chooser.getSelectedFile();
            try {
                musicPlayer.saveMusic(file);
            } catch (IOException e) {
                JOptionPane.showInternalMessageDialog(null, SAVE_FILE_ERROR_MESSAGE, "Erro!", ERROR_MESSAGE);
            }
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
