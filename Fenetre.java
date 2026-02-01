package windows;

import java.awt.Color;
import dessin.*;
import listen.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
    JPanel fond=new JPanel();
    public static int L=400;
    public static int l=500;
    Serpent serpent;
    JLabel over=new JLabel();

    public Fenetre(){
        this.setTitle("Snake");
        this.setSize(this.l, this.L);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fond.setBackground(Color.LIGHT_GRAY);
        this.setContentPane(fond);
        this.setVisible(true);
        serpent=new Serpent(getGraphics(), 3);
        Listener ecoute=new Listener(serpent);
        this.addKeyListener(ecoute);
        over.setBounds(Fenetre.l/2-50, 0, 200, 200);
    }

    public void start(){
        serpent.jouer();
        gameOver();
    }

    public void gameOver(){
        over.setText("GAME OVER");
        fond.add(over);
        fond.repaint();
    }

}