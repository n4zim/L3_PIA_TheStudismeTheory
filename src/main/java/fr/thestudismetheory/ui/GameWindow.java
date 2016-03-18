package fr.thestudismetheory.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import fr.thestudismetheory.Resources;
import fr.thestudismetheory.data.Student;
import fr.thestudismetheory.data.Teacher;

import static fr.thestudismetheory.Resources.UNIV_IMG;
import static fr.thestudismetheory.Resources.getImage;


/**
 * Fenetre principale du jeu
 * Created by Maeva on 11/03/2016.
 */
public class GameWindow {

    protected InstitutionWindow institutionWindow = null;
    protected SchoolWindow schoolWindow = null;
    protected TeacherWindow teacherWindow = null;
    protected StudentWindow studentWindow = null;
    protected FinanceWindow financeWindow = null;

    private boolean saved = false;
    private JFrame gameWindow;

    public GameWindow(){
        WindowConstants window = new WindowConstants(UIConstants.TITLE_GAME);
        this.gameWindow = window;
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setContentPane(Content());
        window.pack();
    }

    JPanel Content(){
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        // Creation du panel contenant les boutons de navigation du jeu
        JPanel west = new JPanel();
        content.add(west, BorderLayout.WEST);

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
                if(institutionWindow == null)
                    institutionWindow = new InstitutionWindow();
                else
                    institutionWindow.setVisible(true);
            }
        });

        // Bouton gerant le déploiement de la fenêtre écoles
        JButton b_school = new JButton(UIConstants.BUTTON_SCHOOL);
        b_school.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(schoolWindow == null)
                    schoolWindow = new SchoolWindow();
                else
                    schoolWindow.setVisible(true);
            }
        });

        // Bouton gerant le déploiement de la fenêtre professeurs
        JButton b_teacher = new JButton(UIConstants.BUTTON_TEACHER);
        b_teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherWindow == null)
                    teacherWindow = new TeacherWindow();
                else
                    teacherWindow.setVisible(true);
            }
        });

        // Bouton gerant le déploiement de la fenêtre étudiants
        JButton b_student = new JButton(UIConstants.BUTTON_STUDENT);
        b_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentWindow == null)
                    studentWindow = new StudentWindow();
                else
                    studentWindow.setVisible(true);
            }
        });

        // Bouton gerant le déploiement de la fenêtre finance
        JButton b_fin = new JButton(UIConstants.BUTTON_FINANCE);
        b_fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(financeWindow == null)
                    financeWindow = new FinanceWindow();
                else
                    financeWindow.setVisible(true);
            }
        });


        west.add(b_inst, gbc);
        west.add(b_school, gbc);
        west.add(b_teacher, gbc);
        west.add(b_student, gbc);
        west.add(b_fin, gbc);

        // Panel des statistiques générales du jeu
        JPanel south = new JPanel();
        content.add(south, BorderLayout.SOUTH);

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
        south.add(new JLabel(UIConstants.LABEL_TIME));
        south.add(new JLabel(UIConstants.LABEL_STAT));
        south.add(optGame);

        // Panel image
        JPanel center = new JPanel();
        content.add(center, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon(UNIV_IMG);
        JLabel j = new JLabel(icon);
        center.add(j);

        return content;
    }

    public void setSaved(boolean saved) { this.saved = saved; }

    public boolean isSaved() { return saved; }

    public void optGame(){
        JDialog opts = new JDialog(gameWindow,UIConstants.LABEL_OPT_GAME, true );
        opts.setSize(UIConstants.DEFAULT_OPT_DIM);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel opt = new JPanel();
        opts.add(opt, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15); // Taille et espacemet des boutons
        opt.setLayout(new GridBagLayout());

        JButton save = new JButton(UIConstants.BUTTON_SAVE);
        JButton load = new JButton(UIConstants.BUTTON_LOAD);
        JButton time = new JButton("time");
        JButton exit = new JButton(UIConstants.BUTTON_EXIT);


        opt.add(save, gbc);
        opt.add(load, gbc);
        opt.add(exit, gbc);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Fonction save
                if(!isSaved()) setSaved(true);
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



}