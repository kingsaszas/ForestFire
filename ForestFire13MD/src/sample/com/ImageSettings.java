package sample.com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ImageSettings
{
    DataManager dm;

    public ImageSettings(DataManager dm)
    {
        this.dm = dm;
    }

    BufferedImage loadPicture()
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Mapa_MD_no_terrain_low_res_dark_Gray.bmp"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return img;
    }

    void setPicture(int binValue)
    {
        BufferedImage lakeimg = loadPicture();
        BufferedImage newimage = new BufferedImage(lakeimg.getWidth(),lakeimg.getHeight(),BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < lakeimg.getHeight(); i++) {
            for (int j = 0; j < lakeimg.getWidth(); j++) {
                Color c = new Color(lakeimg.getRGB(j, i));

                Random random  = new Random();
                int val = random.nextInt(2);

                if (c.getRed() <= binValue && c.getGreen() <= binValue && c.getBlue() <= binValue)
                    newimage.setRGB(j, i, dm.blue);
                else {
                    if(val == 0)
                        newimage.setRGB(j, i, dm.greenLight);
                    else
                        newimage.setRGB(j, i, dm.greenDark);
                }
            }
        }

        dm.image = newimage;
    }

    void setFire(int x, int y) {
        int col = new Color(dm.image.getRGB(x,y)).getRGB();

        if (x < dm.image.getWidth() && y < dm.image.getHeight()) {
            if (col != dm.blue) {
                //dm.clickedstart = false;
                dm.image.setRGB(x, y, dm.red);
            }
        }

        //System.out.println(dm.clickedstart);

    }

}
