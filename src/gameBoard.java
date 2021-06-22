import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gameBoard extends JPanel implements KeyListener, ActionListener {
    final static int PANEL_WIDTH = 500;
    final static int PANEL_HEIGHT = 500;
    Timer timer;
    public static Buddy buddy;
    static int hit = 0, miss = 0;
    Thread brgrs;

    gameBoard(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);


        timer = new Timer(1, this);
        timer.start();
        buddy = new Buddy();
        brgrs = new Thread(new Runnable() {
            @Override
            public void run() {
                Burgers();
            }
        });
        brgrs.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
                if (buddy.X > 0)
                    buddy.X -= 5;
                break;
            case 'd':
                if (buddy.X < gameBoard.PANEL_WIDTH - buddy.Img.getWidth(null))
                    buddy.X += 5;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                if (buddy.X > 0)
                    buddy.X -= 5;
                break;
            case 39:
                if (buddy.X < gameBoard.PANEL_WIDTH - buddy.Img.getWidth(null))
                    buddy.X += 5;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 32:
                Bullet bullet = new Bullet();
                break;
        }
    }

    public synchronized void paint(Graphics g) {
        super.paint(g); // paint background

        Graphics2D g2D = (Graphics2D) g;
        if (buddy.alive){
            g2D.drawImage(buddy.Img, buddy.X, buddy.Y, null);

            for (var b: Bullet.bulletsList
            ) {
                g2D.drawImage(b.Img, b.X, b.Y, null);
            }

            for (var b: Burger.burgersList
            ) {
                g2D.drawImage(b.Img, b.X, b.Y, null);
                int n = Bullet.bulletsList.size();
                for (var bul: Bullet.bulletsList
                ) {
                    if (Math.abs(bul.X - b.X) < 40 && Math.abs(bul.Y - b.Y) < 40){
                        bul.Hit(b);
                    }
                }
            }
        }
    }

    public static void ThreadKiller(){
        for (var burger: Burger.burgersList
        ) {
            burger.IsDead = true;
        }
        for (var bullet: Bullet.bulletsList
        ) {
            bullet.hit = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    static int Random(int n0, int n1)
    {
        Random rnd = new Random();
        if (n0 == 0 && n1 == 1) return rnd.nextInt() % 2;
        return rnd.nextInt() % (n1 - n0) + n0;
    }

    static void Burgers(){
        JOptionPane.showMessageDialog(null, "Старт!", "Thread War", JOptionPane.INFORMATION_MESSAGE);
        int n = 0;
        while(buddy.alive){
            if (Random(0, 100) < (hit + miss) / 25 + 20){
                Burger b = new Burger();
                n+=50;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
