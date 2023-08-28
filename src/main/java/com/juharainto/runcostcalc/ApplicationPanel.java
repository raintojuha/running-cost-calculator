package com.juharainto.runcostcalc;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;


public class ApplicationPanel extends JPanel {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 200;

    static JLabel info;
    static ImageIcon icon;
    String vehicleInfo;


    ApplicationPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(new Color(100, 100, 100));

        info = new JLabel();

        updateVehicleInfoOnScreen();     

        info.setHorizontalTextPosition(JLabel.CENTER);
        info.setVerticalTextPosition(JLabel.BOTTOM);

        this.add(info);
    }

    public static void updateVehicleInfoOnScreen() {
        JSONObject vehicle = FileHandler.getVehicleDataByIndex(ApplicationFrame.CURRENT_VEHICLE_INDEX);

        String vehicleInfo = "<html>\n"
                    + "     <body style='text-align: center;'>\n"
                    + "         <h3>"+ vehicle.get("name") +"</h3>\n"
                    + "         <p><b>Fuel: </b>"+ capitalizeWord(vehicle.get("fuel").toString()) +"</p>\n"
                    + "     </body>\n"
                    + "</html>";

        switch (vehicle.get("type").toString()){
            case "car":
                icon = new ImageIcon("car-100px.png");;
                break;
            case "van":
                icon = new ImageIcon("van-100px.png");
                break;
            case "motorcycle":
                icon = new ImageIcon("motorcycle-100px.png");
                break;
            default:
                icon = new ImageIcon("car-100px.png");
                break;
        }
        
        // When called this method sets new values for the text and icon
        // Funny enough, this works. I'm not sure if this is the correct way.
        info.setIcon(icon);
        info.setText(vehicleInfo);

    }

    public static String capitalizeWord(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
