import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 12.03.2014
 * @Bjoern Palmert 
 */

public class SortierenGUI extends JFrame {
    // Anfang Attribute
    private JButton btnReset = new JButton();
    private JButton btnSelection = new JButton();
    private JButton btnBubble = new JButton();
    private JButton btnGnome = new JButton();
    private int[] zahlen;
    private Diagramm diag;
    // Ende Attribute

    public SortierenGUI() { 
        // Frame-Initialisierung
        super("Sortierverfahren");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int frameWidth = 600; 
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        btnReset.setBounds(48, 16, 75, 30);
        btnReset.setText("Reset");
        btnReset.setMargin(new Insets(2, 2, 2, 2));
        btnReset.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    btnReset_ActionPerformed(evt);
                }
            });
        btnReset.setFont(new Font("Dialog", Font.BOLD, 20));
        cp.add(btnReset);
        btnSelection.setBounds(8, 72, 147, 30);
        btnSelection.setText("Selectionsort");
        btnSelection.setMargin(new Insets(2, 2, 2, 2));
        btnSelection.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    btnSelection_ActionPerformed(evt);
                }
            });
        btnSelection.setFont(new Font("Dialog", Font.BOLD, 20));
        cp.add(btnSelection);
        btnBubble.setBounds(8, 128, 147, 30);
        btnBubble.setText("Bubblesort");
        btnBubble.setMargin(new Insets(2, 2, 2, 2));
        btnBubble.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    btnBubble_ActionPerformed(evt);
                }
            });
        btnBubble.setFont(new Font("Dialog", Font.BOLD, 20));
        cp.add(btnBubble);
        btnGnome.setBounds(8, 184, 147, 30);
        btnGnome.setText("Gnomesort");
        btnGnome.setMargin(new Insets(2, 2, 2, 2));
        btnGnome.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    btnGnome_ActionPerformed(evt);
                }
            });
        btnGnome.setFont(new Font("Dialog", Font.BOLD, 20));
        cp.add(btnGnome);

        diag = new Diagramm(this);
        diag.setBounds(176, 8, 400, 250);
        cp.add(diag);
        // Ende Komponenten

        reset(20);
        setVisible(true);
    } // end of public SortierenGUI2

    // Anfang Methoden
    public void reset(int pAnz)
    {
        zahlen = new int[pAnz];

        for (int i = 0; i < zahlen.length; i++)
        {
            zahlen[i] = (int)(Math.random() * 250);
        }

        diag.zeichne();
    }

    public int[] getZahlen()
    {
        return zahlen;
    }

    public void tauschen(int pStelle1, int pStelle2)
    {
        int h = zahlen[pStelle1];
        zahlen[pStelle1] = zahlen[pStelle2];
        zahlen[pStelle2] = h;
    }

    public int findeMin(int pStelleStart, int pStelleEnde)
    {
        int min = pStelleStart;

        for (int i = pStelleStart + 1; i <= pStelleEnde; i++)
        {
            if (zahlen[i] < zahlen[min]) min = i;
        }

        return min;
    }

    public void btnReset_ActionPerformed(ActionEvent evt) {
        reset(20);
    } // end of btnReset_ActionPerformed

    public void btnSelection_ActionPerformed(ActionEvent evt) 
    {
        int min;
        for (int i = 0; i < zahlen.length; i++)
        {
            min = findeMin(i, zahlen.length-1);
            diag.markiere(i, Color.GREEN);
            diag.markiere(min, Color.RED);
            try {Thread.sleep(500); } catch(Exception e) {e.printStackTrace();}
            tauschen(i, min);            
            diag.zeichne();
            diag.markiere(i, Color.BLUE);
            try {Thread.sleep(500); } catch(Exception e) {e.printStackTrace();}
            diag.zeichne();
        }

    }
    
    public void btnBubble_ActionPerformed(ActionEvent evt) 
    {
        for (int j = zahlen.length - 1; j > 0; j--)
        {
            for (int i = 0; i < j; i++)
            {
                diag.zeichne();
                diag.markiere(i, Color.GREEN);
                diag.markiere(i+1, Color.GREEN);
                try {Thread.sleep(200); } catch(Exception e) {e.printStackTrace();}

                if (zahlen[i] > zahlen[i+1]) 
                {
                    tauschen(i, i+1);
                    diag.zeichne();
                    diag.markiere(i, Color.RED);
                    diag.markiere(i+1, Color.RED);
                }
                try {Thread.sleep(200); } catch(Exception e) {e.printStackTrace();}  
            }
        }
    } // end of btnBubble_ActionPerformed

    public void btnGnome_ActionPerformed(ActionEvent evt) 
    {
        int pos = 0;

        while (pos < zahlen.length - 1)
        {
            diag.zeichne();
            diag.markiere(pos, Color.GREEN);
            diag.markiere(pos+1, Color.GREEN); 
            try {Thread.sleep(250); } catch(Exception e) {e.printStackTrace();}

            if (zahlen[pos] > zahlen[pos + 1])
            {
                tauschen(pos, pos+1);
                if (pos != 0) pos--;
            }
            else
            {
                pos++;
            }
        } // end of btnGnome_ActionPerformed
    }
    // Ende Methoden

    public static void main(String[] args) {
        new SortierenGUI();
    } // end of main

} // end of class SortierenGUI2
