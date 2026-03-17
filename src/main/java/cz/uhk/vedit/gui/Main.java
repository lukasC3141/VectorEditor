package cz.uhk.vedit.gui;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        //spusteni GUI, okna
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VeditFrame().setVisible(true);
            }
        });

        //SwingUtilities.invokeLater(() -> new VeditFrame().setVisible(true));
    }
}
