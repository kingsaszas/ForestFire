package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DataManager
{
    BufferedImage image;

    int binValue = 180;

    //main Colors
    int greenDark = new Color(0, 128, 0).getRGB();
    int greenLight = new Color(0, 188, 0).getRGB();
    int blue = new Color(0,88,222).getRGB();
    int red = new Color(155, 0, 0).getRGB();
    int black = new Color(30,25, 30).getRGB();
    int yellow = new Color(250,225,50).getRGB();
    int brown  = new Color(139,69,19).getRGB();

    int humidityValue = 0;
    String directionOfWind = null;

    ArrayList<Points> blackpoints;

    public DataManager() {
        blackpoints = new ArrayList<>();
    }

}
