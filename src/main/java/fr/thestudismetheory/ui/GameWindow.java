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

    public GameWindow(){
        WindowConstants window = new WindowConstants(UIConstants.TITLE_GAME);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setContentPane(Content());
        window.pack();
    }

    JPanel Content(){
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());


        JPanel west = new JPanel();
        content.add(west, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);
        west.setLayout(new GridBagLayout());

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

        JPanel south = new JPanel();
        content.add(south, BorderLayout.SOUTH);

        FlowLayout f = new FlowLayout();
        f.setHgap(90);
        south.setLayout(f);

        south.add(new JLabel("Ressources"));
        south.add(new JLabel("Temps"));
        south.add(new JLabel("Statistiques"));
        south.add(new JButton("Options du jeu"));

        JPanel center = new JPanel();
        content.add(center, BorderLayout.CENTER);
        ImageIcon icon = new ImageIcon(UNIV_IMG);
        JLabel j = new JLabel(icon);
        center.add(j);

        return content;
    }
}
