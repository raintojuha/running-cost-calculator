package com.juharainto.runcostcalc;

import java.awt.Dimension;

import javax.swing.JPanel;

public class ApplicationPanel extends JPanel {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;

    ApplicationPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    }
    
}
