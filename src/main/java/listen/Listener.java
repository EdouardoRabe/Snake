package listen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import dessin.*;
public class Listener implements KeyListener{
    Serpent s;
    public Listener(Serpent serpent){
        this.s=serpent;
    }
    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_UP && s.direction!=3){
            s.direction=1;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && s.direction!=4){
            s.direction=2;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && s.direction!=1){
            s.direction=3;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && s.direction!=2){
            s.direction=4;
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}
