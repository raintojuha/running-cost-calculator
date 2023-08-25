package com.juharainto.runcostcalc;

/**
 * 
 *
 */
public class App 
{
    public static final String WORKING_FILE = "project.json";
    public static void main( String[] args )
    {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Calculator");
        System.setProperty("apple.awt.application.name", "Calculator");
        //System.setProperty("apple.awt.fileDialogForDirectories", "true");
        //new ApplicationFrame();

        ApplicationFrame frame = new ApplicationFrame();
    }
}

// You could calculate
// Average price per fuel type (diesel, 98, 95)
// Average price per station (ABC, Neste)