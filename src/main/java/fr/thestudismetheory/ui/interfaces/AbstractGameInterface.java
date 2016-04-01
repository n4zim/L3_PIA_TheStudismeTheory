/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.ui.interfaces;

import fr.thestudismetheory.data.strings.UIConstants;

import javax.swing.*;

/**
 * @author vincent
 */
abstract public class AbstractGameInterface extends JPanel {

    public AbstractGameInterface() {
        setPreferredSize(UIConstants.DEFAULT_WIN_DIM);
    }

    abstract public String getTitle();

    abstract public String getId();
}
