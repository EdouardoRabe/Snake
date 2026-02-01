package dessin;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

import dessin.Noeud;
import dessin.Pomme;
import windows.Fenetre;
public class Serpent extends JPanel{
    int L;
    Graphics g;
    int nbPommmes=3;
    boolean play=true;
    Vector<Noeud> corps=new Vector<Noeud>();
    Vector<Pomme> pommes=new Vector<Pomme>();
    public int direction=4;

    public Serpent(Graphics g, int longueur){
        this.g=g;
        this.L=longueur;
    }

    public void CreateApple(){
        int randX, randY;
        int largFen=Fenetre.l;
        int longFen=Fenetre.L;
        Boolean create=true;
        while (pommes.size()<this.nbPommmes) {
            create=true;
            Random rand=new Random();
            randX=rand.nextInt(largFen-40)+20;
            randY=rand.nextInt(longFen-60)+30;
            for(int i=0; i<corps.size(); i++){
                Noeud teste=corps.elementAt(i);
                if(randX==teste.X && randY==teste.Y){
                    create=false;
                }
            }
            if(create==true){
                pommes.addElement(new Pomme(randX, randY, Color.red));
            }
        }
    }

    public void CreateSerpent(){
        for(int i=0; i<this.L; i++){
            if(i==0){
                corps.addElement(new Noeud((Fenetre.l/2)+(i*10), Fenetre.L/2, Color.GREEN));
            }
            else{
                corps.addElement(new Noeud((Fenetre.l/2)+(i*10), Fenetre.L/2, Color.BLACK));
            }
        }
    }

    public void dessinPommes(){
        for(int i=0; i<pommes.size(); i++){
            Pomme concerned=pommes.elementAt(i);
            g.setColor(concerned.couleur);
            g.fillOval(concerned.X, concerned.Y, 10, 10);
        }
    }

    public void dessinSerpent(){
        for(int i=0; i<corps.size(); i++){
            Noeud concerned=corps.elementAt(i);
            g.setColor(concerned.couleur);
            g.fillOval(concerned.X, concerned.Y, 10, 10);
        }
        score();
    }
    
    public void jouer(){
        CreateSerpent();
        while(this.play==true){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, Fenetre.l, Fenetre.l);
            CreateApple();
            dessinPommes();
            dessinSerpent();
            try{
                Thread.sleep(100);
            }catch(Exception ex){
                Thread.currentThread().interrupt();
            }
            move();
            Collision();
        }
    }

    public void move(){
        int px, py;
        for(int i=corps.size()-1; i>0; i--){
            Noeud temp=corps.elementAt(i-1);
            px=temp.X;
            py=temp.Y;
            temp=corps.elementAt(i);
            temp.X=px;
            temp.Y=py;
        }
        Noeud nouveauTete=corps.elementAt(0);
        if(direction==1){
            nouveauTete.Y-=10;
        }
        if(direction==2){
            nouveauTete.X+=10;
        }
        if(direction==3){
            nouveauTete.Y+=10;
        }
        if(direction==4){
            nouveauTete.X-=10;
        }
    }

    public void Collision(){
        for(int i=0; i<pommes.size(); i++){
            Pomme pomme=pommes.elementAt(i);
            Noeud tete=corps.elementAt(0);
            Rectangle rect=new Rectangle(pomme.X-10, pomme.Y-10, 20, 20);
            if(rect.contains(tete.X, tete.Y)){
                pommes.remove(i);
                corps.addElement(new Noeud(0, 0, Color.BLACK));
            }
        }
        for(int i=1; i<corps.size(); i++){
            Noeud tete=corps.elementAt(0);
            Noeud autre=corps.elementAt(i);
            if(tete.X==autre.X && tete.Y==autre.Y){
                play=false;
            }
        }
        Noeud tete=corps.elementAt(0);
        if(tete.X<10){
            play=false;
        }
        if(tete.Y<30){
            play=false;
        }
        if(tete.Y>Fenetre.L-20){
            play=false;
        }
        if(tete.X>Fenetre.l-20){
            play=false;
        }
    }

    public void score(){
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(corps.size()-L), 30, Fenetre.L-40);
    }
}
