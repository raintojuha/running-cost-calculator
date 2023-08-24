package com.juharainto.runcostcalc;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ApplicationFrame extends JFrame {
    
    ApplicationFrame() {
        this.add(new ApplicationPanel());
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        //this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }
}
