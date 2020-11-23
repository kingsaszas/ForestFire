/*
package sample.com;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Functions {
    DataManager dm;

    public Functions(DataManager dm) {
        this.dm = dm;
    }

    */
/*
    void startFire5080(int xx, int yy, int xmax, int ymax) {

        System.out.println("fire 5080");
        //BufferedImage newimage5080 = dm.image;  //new BufferedImage(dm.image.getWidth(), dm.image.getHeight(), BufferedImage.TYPE_INT_RGB);     //raz wykonuje, zamienilam na czysty obrazek
        //= dm.image;

        for (int hi = 0; hi < dm.image.getHeight(); hi++) {
            for (int wi = 0; wi < dm.image.getWidth(); wi++) {
                //dla kazdego punktu bede "tworzyc" kopie obrazka i potem aktualizowac obrazek wyswietlany
                BufferedImage newimage5080 = dm.image;

                int counter = 0;

                int colorMaybeRed = new Color(dm.image.getRGB(wi, hi)).getRGB();

                if (dm.red == colorMaybeRed) {        //jesli znajdzie czerwony kolor na obrazku to robi tak jak na mojej kartce

                    System.out.println("++++++ RED POINT ++++++\tx:" + wi + "\ty: " + hi);

                    for (int y = yy; y <= ymax - 2; y++) {
                        int checkX = 0;
                        int checkY = 0;
                        for (int x = xx; x <= xmax - 2; x++) {
                            counter++;
                            checkX = wi + x;
                            checkY = hi + y;

                            System.out.println(counter+ ":\t BADANY PKT \t checkX: " + checkX + "\tcheckY: " + checkY);

                            //sprawdzamy, czy nie wychodzi po za tablice
                            if (checkX < 0)
                                checkX = 0;
                            if (checkY < 0)
                                checkY = 0;
                            if (checkX > dm.image.getWidth())
                                checkX = dm.image.getWidth();
                            if (checkY > dm.image.getHeight())
                                checkY = dm.image.getHeight();

                            //jesli nie wychodzi poza obrazek, to zapisujemy kolor sasiada badanego
                            int colorChecking = new Color(dm.image.getRGB(checkX, checkY)).getRGB();

                            //sprawdzamy, czy jest zielony, jesli tak to bedzie czerwony dm.red
                            if (colorChecking == dm.greenLight || colorChecking == dm.greenDark) {
                                //dm.image.setRGB(checkX, checkY, dm.red);
                                newimage5080.setRGB(checkX, checkY, dm.red);
                                System.out.println("zielony sasiad na czerwony");
                            */
/*} else if (colorChecking == dm.greenDark) {
                                //dm.image.setRGB(checkX, checkY, dm.red);
                                newimage5080.setRGB(checkX, checkY, dm.red);
                                System.out.println("zielony sasiad na czerwony");*//*


                                //jesli jest niebieski sasiad to to bedzie dalej niebieski, tak samo dalej czerwony sasiad i dalej czarny sasiad
                            } else if (colorChecking == dm.blue) {
                                //dm.image.setRGB(checkX, checkY, dm.blue);
                                newimage5080.setRGB(checkX, checkY, dm.blue);
                                System.out.println("niebieski sasiad pozostaje niebieski");
                            } else if (colorChecking == dm.red) {
                                //dm.image.setRGB(checkX, checkY, dm.red);
                                newimage5080.setRGB(checkX, checkY, dm.red);
                                System.out.println("czerwony sasiad pozostaje czerwony");
                            } else if (colorChecking == dm.black) {
                                //dm.image.setRGB(checkX, checkY, dm.black);
                                newimage5080.setRGB(checkX, checkY, dm.black);
                                System.out.println("czarny sasiad pozostaje czarny");
                            }
                        }


                        //tutaj powinnam aktualizować pozar lasu
                    }   //dotad sprawdza punkty

                    // jak juz wszystko pozamienia, to czerwony na czarny sie zrobi
                    newimage5080.setRGB(wi, hi, dm.black);
                    //dm.image.setRGB(wi, hi, dm.black);

                    //tutaj powinny sie te nowe kolory zaktualizowac przed rozpoczeciem kolejnego szukania czerwonego punktu




                    //albo tutaj po przejsciu fora powinnam zaktualizowac obrazek
                    //dm.image = newimage5080;


                } //else {
                    // jesli nie jest czerwony to do nowego obrazka ladujemy pierwotny kolor
                    //newimage5080.setRGB(wi, hi, colorMaybeRed);//newimage5080.setRGB(wi, hi, colorMaybeRed);
                //}

                //albo tutaj po przejsciu fora powinnam zaktualizowac obrazek, dla kazdego punktu aktualizuje
                //dm.image = newimage5080;

            }
        }

        //jak przejdzie caly obrazek
        //dm.image = newimage5080;
    }

    void startFire80(int x, int y, int max, int xwind, int ywind) {
        System.out.println("fire 80");

        */
/*BufferedImage newimage80 = new BufferedImage(dm.image.getWidth(), dm.image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int hi = 0; hi < dm.image.getHeight(); hi++) {
            for (int wi = 0; wi < dm.image.getWidth(); wi++) {

                Color c = new Color(dm.image.getRGB(wi, hi));
                int colo = c.getRGB();

                if(dm.red == colo) {

                }
            }
        }

        dm.image = newimage80;*//*

    }




}

*/