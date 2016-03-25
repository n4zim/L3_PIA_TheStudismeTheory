package fr.thestudismetheory.ui;

import fr.thestudismetheory.ui.interfaces.GameInterface;
import fr.thestudismetheory.ui.interfaces.NewGameInterface;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Nazim on 24/02/2016.
 */
public class MainWindow extends JFrame {
    final private CardLayout layout;

    public MainWindow() {
        super(UIConstants.TITLE_GAME);

        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);

        //Crée la content pane avec pour layout un CardLayout
        layout = new CardLayout();
        setContentPane(new JPanel(layout));

        // Changé depuis NewGameInterface
        addInterface(new GameInterface(), "a");
        switchInterface("a");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Ajoute une nouvelle interface à la fenêtre.
     *
     * @param component l'interface à ajouter
     * @param name      Le nom de l'interface
     */
    public void addInterface(JComponent component, String name) {
        add(component, name);
    }

    /**
     * Change l'interface du jeu
     *
     * @param name Le nom de l'interface
     */
    public void switchInterface(String name) {
        layout.show(getContentPane(), name);
    }
}
