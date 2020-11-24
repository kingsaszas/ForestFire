package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class FireFunction {

    DataManager dm;
    ArrayList<NeighbourTrees> neighboursList = new ArrayList<>();

    int[][] tabPixels = new int[330][600];

    public FireFunction(DataManager dm) {
        this.dm = dm;
    }

    void addToListNeighbours(int[][] tab, int x, int y, int xmin, int ymin, int xmax, int ymax) {

        int xlimit = xmin + xmax;
        int ylimit = ymin + ymax;

        for (int hi = ymin; hi < ylimit; hi++) {
            for (int wi = xmin; wi < xlimit; wi++) {
                System.out.println("x: " + (wi + x) + "\ty: " + (hi + y) + "\tcolor: " + tab[hi + y][wi + x]);

                if (wi == 0 && hi == 0) {
                    neighboursList.add(new NeighbourTrees(wi + x, hi + y, dm.black));
                    tab[y + hi][x + wi] = dm.black;
                    dm.blackpoints.add(new Points(x+wi,y+hi));
                    System.out.println("set black");
                } else
                    neighboursList.add(new NeighbourTrees(wi + x, hi + y, tab[hi + y][wi + x]));
            }
        }
    }


    void startFire(int xmin, int ymin, int xmax, int ymax) {

        BufferedImage imageToChange = dm.image;

        //colors from picture are saveing to array
        for (int hi = 0; hi < dm.image.getHeight(); hi++) {
            for (int wi = 0; wi < dm.image.getWidth(); wi++) {

                int colorMaybeRed = new Color(dm.image.getRGB(wi, hi)).getRGB();

                if (colorMaybeRed == dm.red) {
                    tabPixels[hi][wi] = dm.red;
                } else if (colorMaybeRed == dm.blue) {
                    tabPixels[hi][wi] = dm.blue;
                } else if (colorMaybeRed == dm.black) {
                    tabPixels[hi][wi] = dm.black;
                } else if (colorMaybeRed == dm.greenDark) {
                    tabPixels[hi][wi] = dm.greenDark;
                } else if (colorMaybeRed == dm.greenLight) {
                    tabPixels[hi][wi] = dm.greenLight;
                } else if (colorMaybeRed == dm.yellow) {
                    tabPixels[hi][wi] = dm.yellow;
                } else if (colorMaybeRed == dm.brown) {
                    tabPixels[hi][wi] = dm.brown;
                }
            }
        }

        //if we find red pixel we add its neighbours to the list in the addToListNeighbours function
        for (int hi = 0; hi < imageToChange.getHeight(); hi++) {
            for (int wi = 0; wi < imageToChange.getWidth(); wi++) {

                int checkX = wi + xmin;
                int checkY = hi + ymin;

                if (checkX > -1 && checkX < dm.image.getWidth()) {
                    if (checkY > -1 && checkY < dm.image.getHeight()) {

                        if (tabPixels[hi][wi] == dm.red || tabPixels[hi][wi] == dm.yellow) {
                            System.out.println("\t\tRED FOUND: x:" + wi + "\ty: " + hi);
                            addToListNeighbours(tabPixels, wi, hi, xmin, ymin, xmax, ymax);

                        }
                    }
                }
            }
        }

        //we create new image, where we will load new pixels' colors
        BufferedImage newimage = new BufferedImage(imageToChange.getWidth(), imageToChange.getHeight(), BufferedImage.TYPE_INT_RGB);

        //we load colors from array to the new image
        for (int hi = 0; hi < imageToChange.getHeight(); hi++) {
            for (int wi = 0; wi < imageToChange.getWidth(); wi++) {
                if (tabPixels[hi][wi] == dm.red) {
                    newimage.setRGB(wi, hi, dm.red);
                } else if (tabPixels[hi][wi] == dm.black) {
                    newimage.setRGB(wi, hi, dm.black);
                } else if (tabPixels[hi][wi] == dm.blue) {
                    newimage.setRGB(wi, hi, dm.blue);
                } else if (tabPixels[hi][wi] == dm.greenDark) {
                    newimage.setRGB(wi, hi, dm.greenDark);
                } else if (tabPixels[hi][wi] == dm.greenLight) {
                    newimage.setRGB(wi, hi, dm.greenLight);
                } else if (tabPixels[hi][wi] == dm.yellow) {
                    newimage.setRGB(wi, hi, dm.yellow);
                } else if (tabPixels[hi][wi] == dm.brown) {
                    newimage.setRGB(wi, hi, dm.brown);
                }
            }
        }

        // and load colors of found red point neighbours
        for (NeighbourTrees nt : neighboursList) {
            Random rand = new Random();
            int randVal = rand.nextInt(3);
            if (nt.color == dm.red) {
                newimage.setRGB(nt.x, nt.y, dm.red);
            } else if (nt.color == dm.black) {
                newimage.setRGB(nt.x, nt.y, dm.black);
            } else if (nt.color == dm.blue) {
                newimage.setRGB(nt.x, nt.y, dm.blue);
            } else if (nt.color == dm.greenDark) {
                if(randVal == 0 || randVal == 1)
                    newimage.setRGB(nt.x, nt.y, dm.red);
                else
                    newimage.setRGB(nt.x,nt.y,dm.yellow);
            } else if (nt.color == dm.greenLight) {
                if(randVal == 0 || randVal == 1)
                    newimage.setRGB(nt.x, nt.y, dm.red);
                else
                    newimage.setRGB(nt.x,nt.y,dm.yellow);
            } else if (nt.color == dm.yellow) {
                newimage.setRGB(nt.x, nt.y, dm.yellow);
            } else if (nt.color == dm.brown) {
                newimage.setRGB(nt.x, nt.y, dm.brown);
            }
        }

        // clearing list of neighbours and load newimage to the basic image
        neighboursList.clear();
        dm.image = newimage;

    }

    void putOutTheFire() {

        //if we find red or yellow point we change them to the brown color
        for (int hi = 0; hi < dm.image.getHeight(); hi++) {
            for (int wi = 0; wi < dm.image.getWidth(); wi++) {
                int color = new Color(dm.image.getRGB(wi, hi)).getRGB();

                Random rand = new Random();
                int los = rand.nextInt(3);

                if (los == 1) {
                    if (color == dm.red || color == dm.yellow) {
                        dm.image.setRGB(wi, hi, dm.brown);
                        tabPixels[hi][wi] = dm.brown;
                    }
                }
            }
        }

    }

    void repairForest() {

        //black points are changing to the green
        int size = dm.blackpoints.size();
        Random rand = new Random();
        Points p = dm.blackpoints.get(rand.nextInt(size));
        if(rand.nextInt(2) == 1) {
            dm.image.setRGB(p.x, p.y, dm.greenLight);
            tabPixels[p.y][p.x] = dm.greenLight;
        } else {
            dm.image.setRGB(p.x, p.y, dm.greenDark);
            tabPixels[p.y][p.x] = dm.greenLight;
        }

    }
}
