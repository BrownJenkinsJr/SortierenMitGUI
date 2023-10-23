import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Diagramm extends Canvas
{    
    private SortierenGUI gui;

    public Diagramm(SortierenGUI pGui)
    {
        gui = pGui;
    }

    public void markiere(int pStelle, Color pFarbe)
    {
        int[] zahlen = gui.getZahlen();
        Graphics g = getGraphics();

        if (zahlen != null)
        {
            int w = this.getWidth() / zahlen.length;
            int h = this.getHeight();

            g.setColor(pFarbe);
            g.fillRect(pStelle*w, h-zahlen[pStelle], w, h);
        }
        g.dispose();
    }

    public void zeichne()
    {
        Graphics g = getGraphics();
        if (g != null)
        {
            g.clearRect(0,0,getWidth(), getHeight());
            g.setColor(Color.DARK_GRAY);

            int[] zahlen = gui.getZahlen();
            if (zahlen != null)
            {
                int w = this.getWidth() / zahlen.length;
                int h = this.getHeight();

                for (int i = 0; i < zahlen.length; i++)
                {
                    g.fillRect(i*w, h-zahlen[i], w, h);
                }
            }
            g.dispose();
        }
    }

    public void paint(Graphics g)
    {
        zeichne();
    }
}
