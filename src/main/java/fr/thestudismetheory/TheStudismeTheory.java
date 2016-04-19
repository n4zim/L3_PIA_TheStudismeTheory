package fr.thestudismetheory;

import com.sun.org.apache.xpath.internal.operations.Div;
import fr.thestudismetheory.data.global.dao.GlobalDAOFactory;
import fr.thestudismetheory.handler.*;
import fr.thestudismetheory.ui.MainWindow;

public class TheStudismeTheory {
    final private ModulesFactory modulesFactory = new ModulesFactory();

    final private GlobalDAOFactory globalDAOFactory;

    final private MainWindow mainWindow;

    final private GameHandler gameHandler;
    final private HomeHandler homeHandler;
    final private SchoolHandler schoolHandler;
    final private StudentHandler studentHandler;
    final private TeacherHandler teacherHandler;
    final private DivisionHandler divisionHandler;

    public TheStudismeTheory() {
        globalDAOFactory = modulesFactory.createGlobalDAOFactory();

        mainWindow = new MainWindow(this);
        gameHandler = new GameHandler(this);
        homeHandler = new HomeHandler(this);
        schoolHandler = new SchoolHandler(this);
        studentHandler = new StudentHandler(this);
        teacherHandler = new TeacherHandler(this);
        divisionHandler = new DivisionHandler(this);
    }

    public static void main(String[] args) {
        TheStudismeTheory tst = new TheStudismeTheory();
        tst.start();
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

    public SchoolHandler getSchoolHandler() {
        return schoolHandler;
    }

    public StudentHandler getStudentHandler() {
        return studentHandler;
    }

    public TeacherHandler getTeacherHandler() {
        return teacherHandler;
    }

    public DivisionHandler getDivisionHandler() {
        return divisionHandler;
    }

    public ModulesFactory getModulesFactory() {
        return modulesFactory;
    }

    public GlobalDAOFactory getGlobalDAOFactory() {
        return globalDAOFactory;
    }

    public void start() {
        homeHandler.start();
    }
}
