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

            String filePath = FileHandler.chooseFilePath(this);
            System.out.println(filePath);
            JSONObject objec = FileHandler.readJSONFile(filePath);
            String name = (String) objec.get("name");
            System.out.println(name);

            //System.out.println(FileHandler.chooseFilePath(this));




            /*
            // New File Chooser
            fileChooser = new JFileChooser();

            // Add a filter for JSON files
            fileChooser.setFileFilter(new FileFilter() {
                public String getDescription() {
                    return "JSON Files (*.json)";
                }
             
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".json");
                    }
                }
            });

            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Store the selected file
                File file = fileChooser.getSelectedFile();
                
                System.out.println("Opening: " + file.getName() + file.getAbsolutePath() + file.getPath() + ".");
            } else {
                System.out.println("Open command cancelled by user.");
            }
/* 
            FileDialog d = new FileDialog(this, "Open File", LOAD);
            d.setFile(".json");
            d.setFilenameFilter(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".json");
                }
            });
            d.setVisible(true);
            */
            
        }
    }
}
