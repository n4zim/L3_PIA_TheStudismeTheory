package fr.thestudismetheory.ui;

import javax.swing.*;
/**
 * Created by Maeva on 11/03/2016.
 */
public class WindowConstants extends JFrame {
    public WindowConstants(String title){
        super();
        setTitle(title);
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        pack();
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void closeWindow()
    {
        this.setVisible(false);
    }
}
