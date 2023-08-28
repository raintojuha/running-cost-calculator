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

    public static int CURRENT_VEHICLE_INDEX = 0;

    // Initialize empty JMenu components
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openFileMenuItem;
    JMenu editMenu;
    JMenu helpMenu;
    JComboBox<String> vehicleList;
    

    JFileChooser fileChooser;
    
    ApplicationFrame() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
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

        // Get vehicle names and populate the JComboBox
        //String[] names = FileHandler.listVehicleNames();
        //vehicleList = new JComboBox<String>(names);
        String[] names = FileHandler.listVehicleNames();
        vehicleList = new JComboBox<String>(names);
        vehicleList.setSelectedIndex(0);
        vehicleList.addActionListener(this);
        this.add(vehicleList);

        // Add components to frame
        //his.add(vehicleList);
        this.add(new VehicleInfoPanel());
        
        // Select first JComboBox element. Add action listener
        //vehicleList.setSelectedIndex(0);
        //vehicleList.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /** 
         * "Open File..." is triggered
        */ 
        if(e.getSource() == openFileMenuItem){
            openProjectFile();
        }

        /** 
         * Selected vehicle is changed
        */
        if(e.getSource() == vehicleList) {
            if( ! "cmdIgnore".equals(e.getActionCommand())) {
                // Update current vehicle index
                CURRENT_VEHICLE_INDEX = vehicleList.getSelectedIndex();
                // Update information on screen
                VehicleInfoPanel.updateVehicleInfoOnScreen();
            }

        }
    }

    /**
     * 
     */
    public void openProjectFile() {
        System.out.println("Open File... called");
        // Wait for user input and get selected file path
        String filePath = FileHandler.chooseFilePath(this);
        // Read JSON at file path
        if(filePath != null){
            // Get JSON obect from selected file
            JSONObject obje = FileHandler.readJSONFile(filePath);
            // Overwrite project.json at App root
            FileHandler.writeJSONToFile(obje, "project.json");

            // When project file is loaded
            updateVehicleDropDown();
            
        }
    }

    /**
     * 
     */
    public void updateVehicleDropDown() {
        // Get vehicle names and populate the JComboBox
        String[] names = FileHandler.listVehicleNames();


        /**
         * Disable Action Listener for JCombooBox
         * 
         * This is done because the removeAllItems action wouold
         * trigger the Action Listener and that would in turn trigger
         * updateVehicleInfoOnScreen, which then would try to fetch the
         * currently selected index of the ComboBox. The ComboBox being empty
         * would return an index of -1 and this would cause an OutOfBounds exception.
         */
        String oldCommand = vehicleList.getActionCommand();
        vehicleList.setActionCommand("cmdIgnore");

        // Empty JComboBox
        vehicleList.removeAllItems();

        // Add new vehicles to JComoboBox
        for(int i = 0; i < names.length; i++){
            System.out.println(names[i]);
            vehicleList.addItem(names[i]);
        }

        // Enable Action Listener
        vehicleList.setActionCommand(oldCommand);

        // Select first JComboBox element
        vehicleList.setSelectedIndex(0);
    }
}
