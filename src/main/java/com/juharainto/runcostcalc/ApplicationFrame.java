package com.juharainto.runcostcalc;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.json.simple.*;


public class ApplicationFrame extends JFrame implements ActionListener{
    
    // JFileChooser modes
    public static final int LOAD = 0;
    public static final int SAVE = 1;

    public static JSONObject WORKING_PROJECT = new JSONObject();

    // Initialize empty JMenu components
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openFileMenuItem;
    JMenu editMenu;
    JMenu helpMenu;

    JFileChooser fileChooser;
    
    ApplicationFrame() {
        this.add(new ApplicationPanel());
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        //this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout());

        // Create an empty menu bar
        menuBar = new JMenuBar();
        
        // Create a menu for File options
        fileMenu = new JMenu("File");
        openFileMenuItem = new JMenuItem("Open File...");
        openFileMenuItem.addActionListener(this);
        fileMenu.add(openFileMenuItem);

        // Create a menu for Edit options
        editMenu = new JMenu("Edit");

        // Create a menu for Help ooptioons
        helpMenu = new JMenu("Help");

        // Add menus to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        // Add menubar to frame
        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If "Open File..." is triggered
        if(e.getSource()==openFileMenuItem){
            System.out.println("Open File... called");
            // Wait for user input and get selected file path
            String filePath = FileHandler.chooseFilePath(this);
            System.out.println(filePath);
            // Read JSON at file path
            if(filePath != null){
                WORKING_PROJECT = FileHandler.readJSONFile(filePath);
                String name = (String) WORKING_PROJECT.get("name");
                System.out.println(name);
            }   
        }
    }
}
