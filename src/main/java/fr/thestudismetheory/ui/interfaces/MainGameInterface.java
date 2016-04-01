package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.ui.gamepanel.CentralGamePanel;
import fr.thestudismetheory.ui.listener.GameDataListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * Fenetre principale du jeu
 */
public class MainGameInterface extends AbstractGameInterface {
    final private CentralGamePanel centralPanel;

    private boolean saved = false;
    private JFrame gameWindow;
    
    final private JLabel statResources = new JLabel("???");
    final private JLabel statTime = new JLabel("???");
    
    final private GameDataListener gameDataListener;

    public MainGameInterface() {
        gameDataListener = new GameDataListener(this);
        
        centralPanel = new CentralGamePanel();
        
        setLayout(new BorderLayout());

        // Titre de la fenetre
        JPanel north = title();
        add(north, BorderLayout.NORTH);

        // Creation du panel contenant les boutons de navigation du jeu
        JPanel west = navGame();
        add(west, BorderLayout.WEST);

        // Panel des statistiques générales du jeu
        JPanel south = stat();
        add(south, BorderLayout.SOUTH);

        // Panel des cartes
        //JPanel map = new WorldCityGenerator();
        add(centralPanel, BorderLayout.CENTER);
    }

    @Override
    public String getTitle() {
        return UIConstants.TITLE_GAME;
    }

    @Override
    public String getId() {
        return "MAIN_GAME";
    }

    public JPanel navGame() {
        JPanel west = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacemet des boutons
        west.setLayout(new GridBagLayout());

        // Bouton gerant le déploiement de la fenêtre institution
        JButton b_inst = new JButton(UIConstants.BUTTON_INST);
        b_inst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchInstitution();
            }
        });

        // Bouton gerant le déploiement de la fenêtre écoles
        JButton b_school = new JButton(UIConstants.BUTTON_SCHOOL);
        b_school.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchSchool();
            }
        });

        // Bouton gerant le déploiement de la fenêtre professeurs
        JButton b_teacher = new JButton(UIConstants.BUTTON_TEACHER);
        b_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchTeacher();
            }
        });

        // Bouton gerant le déploiement de la fenêtre étudiants
        JButton b_student = new JButton(UIConstants.BUTTON_STUDENT);
        b_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchStudent();
            }
        });

        // Bouton gerant le déploiement de la fenêtre finance
        JButton b_fin = new JButton(UIConstants.BUTTON_FINANCE);
        b_fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centralPanel.switchFinance();
            }
        });

        west.add(b_inst, gbc);
        west.add(b_school, gbc);
        west.add(b_teacher, gbc);
        west.add(b_student, gbc);
        west.add(b_fin, gbc);

        return west;
    }

    public JPanel stat() {
        JPanel south = new JPanel();

        FlowLayout f = new FlowLayout();
        f.setHgap(90);
        south.setLayout(f);

        final JButton optGame = new JButton(UIConstants.LABEL_OPT_GAME);
        optGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optGame();
            }
        });

        south.add(new JLabel(UIConstants.LABEL_RESSOURCE));
        south.add(statResources);
        south.add(new JLabel(UIConstants.LABEL_TIME));
        south.add(statTime);
        south.add(new JLabel(UIConstants.LABEL_STAT));
        south.add(optGame);
        return south;
    }

    public JPanel img() {
        JPanel center = new JPanel();
        Image img;
        img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        ImageIcon newIcon = new ImageIcon(img);

        JLabel j = new JLabel(newIcon);
        center.add(j);

        return center;
    }

    public JPanel title() {
        JPanel north = new JPanel();
        JLabel title = new JLabel(UIConstants.TITLE_GAME);
        title.setFont(new Font("Verdana", 1, 20));
        north.add(title);

        return north;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    
    public void setTime(Date date){
        statTime.setText(date.toString());
    }
    
    public void setResources(long money){
        statResources.setText(money + "");
    }

    public void optGame() {
        JDialog opts = new JDialog(gameWindow, UIConstants.LABEL_OPT_GAME, true);
        opts.setSize(UIConstants.DEFAULT_OPT_DIM);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel opt = new JPanel();
        opts.add(opt, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacement des boutons
        opt.setLayout(new GridBagLayout());

        JButton save = new JButton(UIConstants.BUTTON_SAVE);
        JButton load = new JButton(UIConstants.BUTTON_LOAD);
        //JButton time = new JButton("time");
        JButton exit = new JButton(UIConstants.BUTTON_EXIT);

        opt.add(save, gbc);
        opt.add(load, gbc);
        opt.add(exit, gbc);

        // Listener des boutons
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fonction save
                if (!isSaved()) setSaved(true);
            }
        });

        load.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fonction load
            }
        }));

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        opts.setVisible(true);
    }

    public GameDataListener getGameDataListener() {
        return gameDataListener;
    }
}
