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

        //zapisuje sobie kolory z aktualnego obrazka do tabeli, by potem powronywac kolory sasiadow itp z imagetochange - kopia oryginalnego obrazka
        //int[][] tabPixels = new int[dm.image.getHeight()][dm.image.getWidth()];

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
                }
            }
        }

        //szukam czerwonego punktu i dodaje do listy sasiadow wszystkich
        for (int hi = 0; hi < imageToChange.getHeight(); hi++) {
            for (int wi = 0; wi < imageToChange.getWidth(); wi++) {

                int checkX = wi + xmin;
                int checkY = hi + ymin;

                if (checkX > -1 && checkX < dm.image.getWidth()) {
                    if (checkY > -1 && checkY < dm.image.getHeight()) {

                        if (tabPixels[hi][wi] == dm.red) {
                            System.out.println("\t\tRED FOUND: x:" + wi + "\ty: " + hi);
                            addToListNeighbours(tabPixels, wi, hi, xmin, ymin, xmax, ymax);

                        }
                    }
                }
            }
        }

        //do nowego obrazka zapisze sobie kolory przeszukane
        BufferedImage newimage = new BufferedImage(imageToChange.getWidth(), imageToChange.getHeight(), BufferedImage.TYPE_INT_RGB);

        //ladowanie punktow ze zmienionej tablicy do obrazka na początku stałymi kolorami
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
                }
            }
        }

        // a teraz kolorujemy sąsiadow
        for (NeighbourTrees nt : neighboursList) {
            if (nt.color == dm.red) {
                newimage.setRGB(nt.x, nt.y, dm.red);
            } else if (nt.color == dm.black) {
                newimage.setRGB(nt.x, nt.y, dm.black);
            } else if (nt.color == dm.blue) {
                newimage.setRGB(nt.x, nt.y, dm.blue);
            } else if (nt.color == dm.greenDark) {
                newimage.setRGB(nt.x, nt.y, dm.red);
            } else if (nt.color == dm.greenLight) {
                newimage.setRGB(nt.x, nt.y, dm.red);
            } else if (nt.color == dm.yellow) {
                newimage.setRGB(nt.x, nt.y, dm.yellow);
            }
        }

        // robimy miejsce dla sasiadow w nastepnej turze
        neighboursList.clear();

        dm.image = newimage;

    }

    void putOutTheFire() {
        //jesli znajdzie czerwony punkt, to zamienia go na zolty.

        //żółty do listy i z listy będe losować potem które drzewa ożyją

        for (int hi = 0; hi < dm.image.getHeight(); hi++) {
            for (int wi = 0; wi < dm.image.getWidth(); wi++) {
                int color = new Color(dm.image.getRGB(wi, hi)).getRGB();

                Random rand = new Random();
                int los = rand.nextInt(3);

                if (los == 1) {
                    if (color == dm.red) {
                        dm.image.setRGB(wi, hi, dm.yellow);
                        tabPixels[hi][wi] = dm.yellow;
                    }
                }
            }
        }


    }

    void repairForest() {

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
