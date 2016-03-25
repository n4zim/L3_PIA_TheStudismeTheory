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
    final private InterfacesHandler interfacesHandler;

    public MainWindow() {
        super(UIConstants.TITLE_GAME);
        interfacesHandler = new InterfacesHandler(this);

        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);

        //Crée la content pane avec pour layout un CardLayout
        layout = new CardLayout();
        setContentPane(new JPanel(layout));
        
        interfacesHandler.init();

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
     * @warning Ne pas utiliser directement !
     * @param component l'interface à ajouter
     * @param name      Le nom de l'interface
     * @see InterfacesHandler#registerInterface(fr.thestudismetheory.ui.interfaces.AbstractGameInterface) 
     */
    public void addInterface(JComponent component, String name) {
        add(component, name);
    }

    /**
     * Change l'interface du jeu
     * @warning Ne pas utiliser directement !
     * @param name Le nom de l'interface
     * @see InterfacesHandler#switchInterface(java.lang.Class) 
     */
    public void switchInterface(String name) {
        layout.show(getContentPane(), name);
    }
}
