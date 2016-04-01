/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.data.global.GameData;
import fr.thestudismetheory.data.strings.UIConstants;
import fr.thestudismetheory.handler.GameHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author vincent
 */
public class SelectGameInterface extends AbstractGameInterface {
    final private GameHandler gameHandler;
    
    final private JButton startBtn = new JButton(UIConstants.BUTTON_NEW_GAME);
    final private JTextField gameName = new JTextField();
    
    final private JPanel gamesList = new JPanel();

    public SelectGameInterface(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        add(newGamePanel());
        add(selectGamePanel());
        
        actions();
    }

    public void setGames(List<GameData> games) {
        gamesList.removeAll();
        
        for(GameData gd : games){
            gamesList.add(new JButton(gd.getId()));
        }
        
        gamesList.repaint();
    }
    
    private JPanel newGamePanel(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), UIConstants.TITLE_NEW_GAME));
        
        gameName.setPreferredSize(new Dimension(200, 25));
        panel.add(gameName);
        panel.add(startBtn);
        
        return panel;
    }
    
    private JPanel selectGamePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), UIConstants.TITLE_SELECT_GAME));
        
        panel.add(new JScrollPane(gamesList));
        
        return panel;
    }
    
    private void actions(){
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameHandler.newGame(gameName.getText());
            }
        });
    }

    @Override
    public String getTitle() {
        return UIConstants.TITLE_GAME;
    }

    @Override
    public String getId() {
        return "SELECT_GAME";
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
//
//        Image img = Resources.UNIV_IMG.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
//        g.drawImage(img, 0, 0, null);
//    }


}
