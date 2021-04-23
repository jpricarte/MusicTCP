package gui;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        UserInterface ui = new UserInterface();

        frame.setContentPane(ui.getjPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("MusicFlow");
        frame.setBackground(Color.WHITE);

        frame.setVisible(true);
    }
}
