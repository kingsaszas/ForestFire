package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TestFunction {

    DataManager dm;
    ArrayList<NeighbourTrees> neighboursList = new ArrayList<>();

    public TestFunction(DataManager dm) {
        this.dm = dm;
    }

    void addToListNeighbours(int[][] tab, int x, int y) {

        for (int hi = -1; hi <= 1; hi++) {
            for (int wi = -1; wi <= 1; wi++) {
                System.out.println("x: " + (wi + x) + "\ty: " + (hi + y) + "\tcolor: " + tab[hi + y][wi + x]);

                int checkX = wi + x;
                int checkY = hi + y;

                //sprawdzamy, czy nie wychodzi po za tablice
                if (checkX < 0)
                    checkX = 0;
                if (checkY < 0)
                    checkY = 0;
                if (checkX > dm.image.getWidth())
                    checkX = dm.image.getWidth();
                if (checkY > dm.image.getHeight())
                    checkY = dm.image.getHeight();

                if (wi == 0 && hi == 0) {
                    neighboursList.add(new NeighbourTrees(checkX, checkY, dm.black));
                    tab[checkY][checkX] = dm.black;
                    System.out.println("set black");
                } else
                    neighboursList.add(new NeighbourTrees(checkX, checkY, tab[checkY][checkY]));
                //if (wi != 0 && hi != 0) // wtedy mi się taka kratka robiła
                //neighboursList.add(new NeighbourTrees(wi + x, hi + y, tab[hi + y][wi + x]));
            }
        }
    }


    void functionWithoutParameters() {

        BufferedImage imageToChange = dm.image;

        //zapisuje sobie kolory z aktualnego obrazka do tabeli, by potem powronywac kolory sasiadow itp z imagetochange - kopia oryginalnego obrazka
        int[][] tabPixels = new int[dm.image.getHeight()][dm.image.getWidth()];

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
                }
            }
        }

        //szukam czerwonego punktu i dodaje do listy sasiadow wszystkich
        for (int hi = 1; hi < imageToChange.getHeight() - 1; hi++) {
            for (int wi = 1; wi < imageToChange.getWidth() - 1; wi++) {

                if (tabPixels[hi][wi] == dm.red) {
                    System.out.println("\t\tRED FOUND: x:" + wi + "\ty: " + hi);
                    addToListNeighbours(tabPixels, wi, hi);

                }
            }
        }

        //do nowego obrazka zapisze sobie kolory przeszukane
        BufferedImage newimage = new BufferedImage(imageToChange.getWidth(), imageToChange.getHeight(), BufferedImage.TYPE_INT_RGB);

        //ladowanie punktow ze zmienionej tablicy do obrazka na początku stałymi kolorami
        for (int hi = 1; hi < imageToChange.getHeight() - 1; hi++) {
            for (int wi = 1; wi < imageToChange.getWidth() - 1; wi++) {
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
            }
        }

        // robimy miejsce dla sasiadow w nastepnej turze
        neighboursList.clear();

        dm.image = newimage;

    }
}
