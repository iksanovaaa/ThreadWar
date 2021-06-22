import javax.swing.*;
import java.awt.*;

public class Buddy{
    boolean alive;
    Image Img;
    int X;
    int Y;

    public Buddy(){
        this.X = gameBoard.PANEL_WIDTH/2 - 25;
        this.Y = gameBoard.PANEL_HEIGHT - 50;
        this.alive = true;
        this.Img = new ImageIcon("buddy.png").getImage();
    }
}
