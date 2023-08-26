package com.juharainto.runcostcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
     * @param parent JFrame for dialog
     * @return (String) Absolute file path of user selected file. Null if action is cancelled.
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

    public static void writeJSONToFile(JSONObject data, String path) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))){
            out.write(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getVehicleName(int id) {
        // Get vehicle data from JSON file
        JSONArray vehicles = (JSONArray) readJSONFile(App.WORKING_FILE).get("vehicles");

        for(int i = 0; i < vehicles.size(); i++) {
            // Current vehicle
            JSONObject current = (JSONObject) vehicles.get(i);
            // If current vehicle id matches search term
            if(current.get("id").toString().equals(String.valueOf(id))) {
                return current.get("name").toString();
            }
        }
        return null;
    }

    /**
     * Returns all vehicle data in a JSON object
     * @param index Vehicle index in JSON Array
     * @return JSONObject with all vehicle data
     */
    public static JSONObject getVehicleDataByIndex(int index) {
        // Get vehicle data from JSON file
        JSONArray vehicles = (JSONArray) readJSONFile(App.WORKING_FILE).get("vehicles");
        JSONObject vehicle = (JSONObject) vehicles.get(index);
        return vehicle;
    } // WHAT if the index is out of bounds? Does this need a catch?

    /**
     * Returns an array of all vehicle names
     * @return String array of names
     */
    public static String[] listVehicleNames() {
        // Create an empty list for names
        ArrayList<String> names = new ArrayList<String>();

        // Get vehicle data from JSON file
        JSONArray vehicles = (JSONArray) readJSONFile(App.WORKING_FILE).get("vehicles");

        // Add all vehicle names to list
        for(int i = 0; i < vehicles.size(); i++) {
            JSONObject current = (JSONObject) vehicles.get(i);
            names.add(current.get("name").toString());
        }

        // Create an array for names
        String[] str = new String[names.size()];
 
        // Add vehicle names to array
        for (int i = 0; i < names.size(); i++) {
            str[i] = names.get(i);
        }

        return str;
    }
}
