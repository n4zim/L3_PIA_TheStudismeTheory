package fr.thestudismetheory;

import fr.thestudismetheory.handler.GameHandler;
import fr.thestudismetheory.handler.HomeHandler;
import fr.thestudismetheory.ui.MainWindow;

public class TheStudismeTheory {
    final private MainWindow mainWindow;
    final private GameHandler gameHandler;
    final private HomeHandler homeHandler;
    
    public TheStudismeTheory() {
        mainWindow = new MainWindow();
        gameHandler = new GameHandler(this);
        homeHandler = new HomeHandler(this);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }
    
    public void start(){
        homeHandler.home();
        mainWindow.setVisible(true);
    }
    
    public static void main(String[] args) {
        TheStudismeTheory tst = new TheStudismeTheory();
        tst.start();
    }
}
