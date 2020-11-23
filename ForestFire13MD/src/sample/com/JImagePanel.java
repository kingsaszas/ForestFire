package sample.com;

import javax.swing.*;
import java.awt.*;

public class JImagePanel extends JPanel
{
    DataManager dm;

    public JImagePanel(DataManager dm)
    {
        this.dm = dm;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(dm.image, 0, 0, this);
        repaint();
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }
}
