package fr.thestudismetheory.ui;

import fr.thestudismetheory.TheStudismeTheory;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.ui.interfaces.InterfacesHandler;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Nazim on 24/02/2016.
 */
public class MainWindow extends JFrame {
    final private TheStudismeTheory app;
    
    final private CardLayout layout;
    final private InterfacesHandler interfacesHandler;

    public MainWindow(TheStudismeTheory app) {
        super(UIConstants.TITLE_GAME);
        
        this.app = app;
        interfacesHandler = new InterfacesHandler(this, app);

        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);

        //Crée la content pane avec pour layout un CardLayout
        layout = new CardLayout();
        setContentPane(new JPanel(layout));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
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

    public InterfacesHandler getInterfacesHandler() {
        return interfacesHandler;
    }
}
