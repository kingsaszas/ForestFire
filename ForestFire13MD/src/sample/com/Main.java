package sample.com;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private JButton startFireButton, sendHelpButton, clearButton, pauseButton, plantButton;
    private JCheckBox northBox, eastBox, southBox, westBox;
    private ButtonGroup checkBoxGroup;
    private JSlider humiditySlider;
    private JLabel windLabel, humidityLabel;

    private JImagePanel jImagePanel;
    private JPanel mainPanel, optionsPanel;

    DataManager dm;

    public Main(String title) {
        super(title);
        this.dm = new DataManager();
        ImageSettings is = new ImageSettings(dm);
        FireFunction ff = new FireFunction(dm);

        is.setPicture(dm.binValue);

        //COLORS:
        /*System.out.println("RED: "+ dm.red);
        System.out.println("BLUE: "+ dm.blue);
        System.out.println("GREEN LIGHT: "+ dm.greenLight);
        System.out.println("GREEN DARK: "+ dm.greenDark);
        System.out.println("BLACK: "+ dm.black);*/


        //buttons
        startFireButton = new JButton();
        startFireButton.setText("start forest fire");
        sendHelpButton = new JButton();
        sendHelpButton.setText("put out the fire");
        clearButton = new JButton("stop and clear");
        pauseButton = new JButton("pause");
        plantButton = new JButton("plant new trees");

        //checkboxes & slider
        northBox = new JCheckBox("north");
        eastBox = new JCheckBox("east");
        southBox = new JCheckBox("south");
        westBox = new JCheckBox("west");

        checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(northBox);
        checkBoxGroup.add(eastBox);
        checkBoxGroup.add(southBox);
        checkBoxGroup.add(westBox);

        humiditySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        humiditySlider.setMajorTickSpacing(25);
        humiditySlider.setMinorTickSpacing(5);
        humiditySlider.setPaintTicks(true);
        humiditySlider.setPaintLabels(true);

        //labels
        windLabel = new JLabel("direction of wind");
        windLabel.setSize(80, 15);
        humidityLabel = new JLabel("humidity");
        humidityLabel.setSize(80, 15);

        //panels
        optionsPanel = new JPanel();
        optionsPanel.setBorder(new TitledBorder(BorderFactory.createTitledBorder("options")));
        GridLayout gl = new GridLayout(12, 1);
        optionsPanel.setLayout(gl);
        optionsPanel.setSize(80, 440);
        optionsPanel.add(windLabel);
        optionsPanel.add(northBox);
        optionsPanel.add(eastBox);
        optionsPanel.add(southBox);
        optionsPanel.add(westBox);
        optionsPanel.add(humidityLabel);
        optionsPanel.add(humiditySlider);
        optionsPanel.add(startFireButton);
        optionsPanel.add(sendHelpButton);
        optionsPanel.add(pauseButton);
        optionsPanel.add(clearButton);
        optionsPanel.add(plantButton);


        jImagePanel = new JImagePanel(dm);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.EAST, optionsPanel);
        mainPanel.add(BorderLayout.CENTER, jImagePanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 100);
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(780, 500));
        this.setResizable(false);
        this.setVisible(true);


        //slider and checkboxes actions
        humiditySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = (int) source.getValue();
                System.out.println("humidity: " + value);
                dm.humidityValue = value;
            }
        });

        northBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("north");
                dm.directionOfWind = "north";
            }
        });

        eastBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("east");
                dm.directionOfWind = "east";
            }
        });

        southBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("south");
                dm.directionOfWind = "south";
            }
        });

        westBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("west");
                dm.directionOfWind = "west";
            }
        });


        //buttons actions
        startFireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        if (dm.humidityValue <= 50) {
                            switch (dm.directionOfWind) {
                                case "north":
                                    ff.startFire(-2, -1, 5, 5);
                                    break;
                                case "south":
                                    ff.startFire(-2, -3, 5, 5);
                                    break;
                                case "east":
                                    ff.startFire(-3, -2, 5, 5);
                                    break;
                                case "west":
                                    ff.startFire(-1, -2, 5, 5);
                                    break;
                            }
                        } else if (dm.humidityValue > 50 && dm.humidityValue < 80)
                            switch (dm.directionOfWind) {
                                case "north":
                                    ff.startFire(-1, -1, 3, 5);
                                    break;
                                case "south":
                                    ff.startFire(-1, -3, 3, 5);
                                    break;
                                case "east":
                                    ff.startFire(-3, -1, 5, 3);
                                    break;
                                case "west":
                                    ff.startFire(-1, -1, 5, 3);
                                    break;
                            }
                        else if (dm.humidityValue >= 80)             //to to dziwne w sumie
                            switch (dm.directionOfWind) {
                                case "north":
                                    ff.startFire(-1, 0, 3, 2);
                                    break;
                                case "south":
                                    ff.startFire(-1, -1, 3, 2);
                                    break;
                                case "east":
                                    ff.startFire(-1, -1, 2, 3);
                                    break;
                                case "west":
                                    ff.startFire(0, -1, 2, 3);
                                    break;
                            }
                        jImagePanel.repaint();
                    }

                }, 0, 300, TimeUnit.MILLISECONDS);

                pauseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        executorService.shutdown();
                    }
                });

            }

        });

        sendHelpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ff.putOutTheFire();
                jImagePanel.repaint();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dm.blackpoints.clear();
                ff.neighboursList.clear();
                dm.directionOfWind = null;
                dm.humidityValue = 0;
                is.setPicture(dm.binValue);
                jImagePanel.repaint();
            }
        });

        plantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        ff.repairForest();
                        jImagePanel.repaint();
                    }
                }, 0, 100, TimeUnit.MILLISECONDS);


                pauseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        executorService.shutdown();
                    }
                });
            }
        });


        jImagePanel.addMouseListener((MouseAdapter) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int x = e.getX();
                int y = e.getY();

                System.out.println("X: " + x + "\tY: " + y);

                is.setFire(x, y);

                jImagePanel.repaint();
            }
        });

    }

    public static void main(String[] args) {
        Main main = new Main("Forest Fire");
    }
}
