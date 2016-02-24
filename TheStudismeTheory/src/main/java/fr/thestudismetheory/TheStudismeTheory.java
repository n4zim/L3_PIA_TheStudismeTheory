package fr.thestudismetheory;

import fr.thestudismetheory.ui.MainWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

/**
 *
 * @author q13000412
 */
public class TheStudismeTheory {
    public static void main(String[] args) {
        JFrame frame = new JFrame("The Studisme Theory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        //MainWindow mainWindow = new MainWindow();
        //mainWindow.setOpaque(true);
        //frame.setContentPane(mainWindow);
        
        JPanel gameSection = new JPanel();
        gameSection.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel buttonsSection = new JPanel();
        buttonsSection.setPreferredSize(new Dimension(200, 400));
        buttonsSection.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel statsSection = new JPanel();
        statsSection.setPreferredSize(new Dimension(0, 100));
        statsSection.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(gameSection, BorderLayout.CENTER);
        contentPane.add(statsSection, BorderLayout.SOUTH);
        contentPane.add(buttonsSection, BorderLayout.WEST);
        
        frame.setContentPane(contentPane);
        
        frame.pack();
        frame.setVisible(true);
    }
}
