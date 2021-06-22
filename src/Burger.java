import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Burger extends Thread{
    int X;
    int Y;
    Image Img = new ImageIcon("hamburger.png").getImage();
    boolean IsDead;
    public static List<Burger> burgersList = new ArrayList<>();

    public Burger(){
        this.X = 0 - this.Img.getWidth(null);
        this.Y = 0;
        this.IsDead = false;
        burgersList.add(this);
        this.start();
    }

    public void run(){
        Random rnd = new Random();
        this.Y = (short)rnd.nextInt(5) * this.Img.getWidth(null);
        this.X = (this.Y / this.Img.getWidth(null)) % 2 == 0 ? (short)- this.Img.getWidth(null) : (short)(gameBoard.PANEL_WIDTH);
        short dir = this.X == (gameBoard.PANEL_WIDTH) ? (short)-1 : (short)1;
        while (((dir == 1 && this.X != gameBoard.PANEL_WIDTH + this.Img.getWidth(null)) ||
                (dir == -1 && this.X != 0 - this.Img.getWidth(null))) && this.IsDead == false) {
            this.X += dir;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        if (gameBoard.buddy.alive){
            if (this.IsDead == false){
                Main.miss.incrementAndGet();
            }
            else {
                Main.hit.incrementAndGet();
            }
        }
        Main.Score();
        burgersList.remove(this);
        this.interrupt();
    }

}
