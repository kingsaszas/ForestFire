package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;

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

    int humidityValue = 0;
    String directionOfWind = null;

    //boolean clickedstart = true;          //tylko jedno ognisko sobie ustawiam


    public DataManager() {
        //
    }

}
