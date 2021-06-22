import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends Thread{
    final int MAX_NUMBER = 3;
    int X;
    int Y;
    boolean hit;
    Image Img;
    public static List<Bullet> bulletsList = new ArrayList<>();

    public Bullet(){
        if (bulletsList.size() < MAX_NUMBER) {
            this.X = gameBoard.buddy.X;
            this.Y = gameBoard.buddy.Y - gameBoard.buddy.Img.getHeight(null);
            this.Img = new ImageIcon("cookie.png").getImage();
            this.hit = false;
            bulletsList.add(this);
            this.start();
        }
    }

    public void run(){
        while(this.Y > 0 - this.Img.getHeight(null) && hit != true){
            this.Y -= 2;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        bulletsList.remove(this);
        this.interrupt();
    }

    public void Hit(Burger burger){
        burger.IsDead = true;
        this.hit = true;
    }
}
