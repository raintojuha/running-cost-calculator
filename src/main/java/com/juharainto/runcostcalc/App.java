package com.juharainto.runcostcalc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Calculator");
        System.setProperty("apple.awt.application.name", "Calculator");
        //new ApplicationFrame();
        //System.out.println( "Hello World!" );

        JSONParser parser = new JSONParser();

        try {
            // Open file and parse JSON data
            Object obj = parser.parse(new FileReader("profile.json"));

            // Store JSON data in JSON Object
            JSONObject jsonObject = (JSONObject) obj;
  
            // GET name of the profile
            String name = (String) jsonObject.get("name");
            System.out.println("Profiili: "+name);

            JSONArray vehicles = (JSONArray) jsonObject.get("vehicles");
            Iterator i = vehicles.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                String vehname = (String)slide.get("name");
                

                //System.out.println(i.next());
                
                System.out.println(vehname);
            }




            } catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

// You could calculate
// Average price per fuel type (diesel, 98, 95)
// Average price per station (ABC, Neste)