package fr.thestudismetheory.ui;

import fr.thestudismetheory.Resources;

import javax.swing.*;
import java.awt.*;

public class WindowConstants extends JFrame {
    public WindowConstants(String title){
        super();
        setTitle(title);
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
    }

    public void closeWindow()
    {
        this.setVisible(false);
    }
}
