package com.juharainto.runcostcalc;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

public class ApplicationPanel extends JPanel {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 200;

    ApplicationPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(new Color(100, 100, 100));
    }
    
}
