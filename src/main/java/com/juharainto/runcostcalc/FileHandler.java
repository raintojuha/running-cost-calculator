package com.juharainto.runcostcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileHandler {

    // Initialize File Chooser
    static JFileChooser fc = new JFileChooser();

    /**
     * 
     * @param parent
     * @return
     */
    public static String chooseFilePath(JFrame parent) {
        // Add a filter for JSON files
            fc.setFileFilter(new FileFilter() {
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

        int returnVal = fc.showOpenDialog(parent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Store the selected file
            File file = fc.getSelectedFile();
                
            System.out.println("Opening: " + file.getName() + file.getAbsolutePath() + file.getPath() + ".");

            return file.getAbsolutePath();
        } else {
            System.out.println("Open command cancelled by user.");
            return null;
        }
    }

    /**
     * 
     * @param filePath
     * @return
     */
    public static JSONObject readJSONFile(String filePath) {
        JSONParser parser = new JSONParser();
        
        try{
            Object obj = parser.parse(new FileReader(filePath));

            // Store JSON data in JSON Object
            JSONObject jsonObject = (JSONObject) obj;

            return jsonObject;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
