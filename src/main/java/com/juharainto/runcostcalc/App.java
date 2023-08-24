package com.juharainto.runcostcalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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


        File file = new File("profile.json");
        if(file.exists()){
            System.out.println("File excists");
        } else {
            System.out.println("No file");
        }

        try{
            FileReader reader = new FileReader("profile.json");
            int data = reader.read();
            while(data != -1) {
                System.out.println((char)data);
                data = reader.read();
            }
            reader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}

// You could calculate
// Average price per fuel type (diesel, 98, 95)
// Average price per station (ABC, Neste)