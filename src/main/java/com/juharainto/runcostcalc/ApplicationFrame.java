package com.juharainto.runcostcalc;



import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
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

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public static JSONObject WORKING_PROJECT = new JSONObject();
    public static File WORKING_FILE = new File("project.json");

    // Initialize empty JMenu components
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openFileMenuItem;
    JMenu editMenu;
    JMenu helpMenu;
    JComboBox vehicleList;

    JFileChooser fileChooser;
    
    ApplicationFrame() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.add(new ApplicationPanel());
        this.pack();
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

        if(!WORKING_FILE.exists()) {
            // Wha to do if no project file is in memory
            System.out.println("No project open");
        }

        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        String[] names = FileHandler.listVehicleNames();
        System.out.println(names);

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        vehicleList = new JComboBox(names);
        this.add(vehicleList);
        vehicleList.setSelectedIndex(0);
        vehicleList.addActionListener(this);
        this.setVisible(true);     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** 
         * "Open File..." is triggered
        */ 
        if(e.getSource() == openFileMenuItem){
            System.out.println("Open File... called");
            // Wait for user input and get selected file path
            String filePath = FileHandler.chooseFilePath(this);
            System.out.println(filePath);
            // Read JSON at file path
            if(filePath != null){
                JSONObject obje = FileHandler.readJSONFile(filePath);
                String name = (String) WORKING_PROJECT.get("name");
                System.out.println(name);
                FileHandler.writeJSONToFile(obje, "project.json");
            }   
        }

        /** 
         * Selected vehicle is changed
        */
        if(e.getSource() == vehicleList) {
            FileHandler.getVehicleName(1);
        }
    }
}
