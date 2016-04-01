package fr.thestudismetheory;

import fr.thestudismetheory.data.City;
import fr.thestudismetheory.data.global.dao.GlobalDAOFactory;
import fr.thestudismetheory.generator.PeopleGenerator;
import fr.thestudismetheory.handler.GameHandler;
import fr.thestudismetheory.handler.HomeHandler;
import fr.thestudismetheory.ui.MainWindow;

import java.util.Date;

public class TheStudismeTheory {
    final private ModulesFactory modulesFactory = new ModulesFactory();
    
    final private GlobalDAOFactory globalDAOFactory;
    
    final private MainWindow mainWindow;
    
    final private GameHandler gameHandler;
    final private HomeHandler homeHandler;
    
    public TheStudismeTheory() {
        globalDAOFactory = modulesFactory.createGlobalDAOFactory();
        
        mainWindow = new MainWindow(this);
        gameHandler = new GameHandler(this);
        homeHandler = new HomeHandler(this);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public HomeHandler getHomeHandler() {
        return homeHandler;
    }

    public ModulesFactory getModulesFactory() {
        return modulesFactory;
    }

    public GlobalDAOFactory getGlobalDAOFactory() {
        return globalDAOFactory;
    }
    
    public void start(){
        homeHandler.start();
    }
    
    public static void main(String[] args) {
        TheStudismeTheory tst = new TheStudismeTheory();
        tst.start();
    }
}
